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

    private void dealOne(File oneSmali){
        String fileName = oneSmali.getName();
        if(fileName.startsWith("R$")) {
            L.e("dealOne" + oneSmali.getAbsolutePath());
        }
    }


    private void walkInDirs(File dir){
        for (File file: dir.listFiles()){
            if(file.isDirectory()){
                walkInDirs(file);
            }else {
                dealOne(file);
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
