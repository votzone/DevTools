package vot.wq.devtool.androidR;

import vot.wq.devtool.L;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResetSmaliIds {
    private String smaliDir;
    private String difPaths;

    private Map<String,Map<String,String>> ids;

    public ResetSmaliIds(String smaliDir, String difPaths){
        this.smaliDir = smaliDir;
        this.difPaths = difPaths;
        ids = new HashMap<>();
    }

    private void dealOne(File oneSmali) throws IOException {
        String fileName = oneSmali.getName();
        if(fileName.startsWith("R$")) {
            String subname = fileName.substring(2,fileName.length()-6);
            L.e(subname);
            Map<String,String> typeIds = ids.get(subname);
            if(typeIds ==null){
                return;
            }
            File outFile = new File(oneSmali.getAbsolutePath()+"_");
            if(outFile.exists()){
                outFile.delete();
            }
            L.e("dealOne" + oneSmali.getAbsolutePath());
            BufferedWriter bw= new BufferedWriter(new FileWriter(outFile));
            BufferedReader br = new BufferedReader( new FileReader(oneSmali));
            String line = null;
            boolean flagUpdate = false;
            while ((line = br.readLine())!=null){
                boolean skip = false;
                String value = obtainValue(line);
                if(value !=null){
                    String id = typeIds.get(value);
                    if(id!=null){
                        skip = true;
                        bw.write(buildLine(value,id));
                    }
                }
                if(!skip){
                    bw.write(line+"\n");
                }else {
                    flagUpdate = true;
                }
            }
            bw.close();
            br.close();
            if(flagUpdate) {
                oneSmali.delete();
                outFile.renameTo(oneSmali);
            }else {
                outFile.delete();
            }
        }
    }


    private void walkInDirs(File dir){
        for (File file: dir.listFiles()){
            if(file.isDirectory()){
                walkInDirs(file);
            }else {
                try {
                    dealOne(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void reset(){
        File dir = new File(smaliDir);
        readDiffs();
        walkInDirs(dir);
    }

    public void readDiffs(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(this.difPaths)));
            String line = null;
            while ((line = br.readLine())!=null){
                obtainIds(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String buildLine(String value,String id){
        String format = ".field public static final %s:I = %s\n";
        return String.format(format,value,id);
    }

    private String obtainValue(String line){
        Pattern pattern = Pattern.compile(".field public static final (.*):I = 0x");
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private void obtainIds(String line){
        Pattern pattern = Pattern.compile("<public type=\"(.*)\" name=\"(.*)\" id=\"(.*)\" />");
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) {
            String type = matcher.group(1);
            String value = matcher.group(2);
            String id = matcher.group(3);
            System.out.println(type +"\t"+ value +"\t"+ id);
            Map<String,String> typeMaps = ids.get(type);
            if (typeMaps == null){
                typeMaps = new HashMap<>();
                ids.put(type,typeMaps);
            }
            typeMaps.put(value,id);
        }
    }

    public static void main(String []args){
        String s = "    <public type=\"id\" name=\"linear_buttons\" id=\"0x7f0b007a\" />\n";
// 把要匹配的字符串写成正则表达式，然后要提取的字符使用括号括起来
// 在这里，我们要提取最后一个数字，正则规则就是“一个数字加上大于等于0个非数字再加上结束符”

    }
}
