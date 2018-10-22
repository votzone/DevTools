package vot.wq.devtool.androidR;

import vot.wq.devtool.L;
import vot.wq.devtool.img.GlobalUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MergePublicXml {
    private String nMergeXmlPath;
    private String originalPath;

    private Map<String, Map<String,String>> ids;

    public MergePublicXml(String mergeXmlPath, String originalPath){
        this.nMergeXmlPath = mergeXmlPath;
        this.originalPath = originalPath;
        ids = new HashMap<>();
    }

    private void loadIds(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(this.nMergeXmlPath)));
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

    /**
     * 将originalPath中已有的id删除
     */
    private void optimizeIds(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(this.originalPath)));
            String line = null;
            while ((line = br.readLine())!=null){
                optimizeOne(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void optimizeOne(String line){
//        Pattern pattern = Pattern.compile("<public type=\"(.*)\" name=\"(.*)\" id=\"(.*)\" />");
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) {
            String type = matcher.group(1);
            String value = matcher.group(2);
            String id = matcher.group(3);
            System.out.println(type +"\t"+ value +"\t"+ id);
            Map<String,String> typeMaps = ids.get(type);
            if (typeMaps != null){
                if(typeMaps.containsKey(value)){
                    typeMaps.remove(value);

                    if(typeMaps.isEmpty()){
                        ids.remove(type);
                    }
                }
            }
        }
    }

    static Pattern pattern = Pattern.compile("<public type=\"(.*)\" name=\"(.*)\" id=\"(.*)\" />");

    private void obtainIds(String line){
//        Pattern pattern = Pattern.compile("<public type=\"(.*)\" name=\"(.*)\" id=\"(.*)\" />");
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

    private void realMerge() throws IOException {
        File oriFile= new File(originalPath);
        File outFile = new File(originalPath+"_");
        if(outFile.exists()){
            outFile.delete();
        }
        BufferedWriter bw= new BufferedWriter(new FileWriter(outFile));
        BufferedReader br = new BufferedReader( new FileReader(oriFile));
        String line = null;
        String last_type = null;
        int max = -1;
        int type_id = 0x7f010000;
        while ((line = br.readLine())!=null){
            Matcher matcher = pattern.matcher(line);
            if(matcher.find()) {
                String type = matcher.group(1);
                String name = matcher.group(2);
                String id = matcher.group(3);
                if(last_type!=null && !last_type.equals(type) &&ids.containsKey(last_type)){
                    insertIds(last_type,max+1,bw);
                    ids.remove(last_type);
                    // 完成之后归零
                    max = -1;
                }
                last_type = type;
                int idvalue = Integer.parseInt(id.substring(2),16);
                if(idvalue>max){
                    max = idvalue;
                }
                while (idvalue >= type_id){
                    type_id += 0x10000;
                }
            }

            if(line.trim().equals("</resources>")){
                // 处理结尾的insert
                insertIds(last_type,max+1,bw);
                ids.remove(last_type);

                // ori中没有的直接插入
                if(!ids.isEmpty()){
                    for (String type:ids.keySet()){
                        insertIds(type, type_id, bw);
                        type_id+=0x10000;
                    }
                }
            }
            bw.write(line+"\n");
        }
        br.close();
        bw.close();

    }

    private void insertIds(String type, int start, BufferedWriter bw) throws IOException {
        String format = "    <public type=\"%s\" name=\"%s\" id=\"0x%s\" />";
        Map<String,String> typeIds = ids.get(type);
        if(typeIds !=null && !typeIds.isEmpty()){
            for (String name: typeIds.keySet()){
                String line = String.format(format,type,name, Integer.toHexString(start++))+"\n";
                bw.write(line);
            }
        }
    }

    public void merge(){
        loadIds();
        optimizeIds();
        try {
            realMerge();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        String mergXmlPath = "E:\\ajm\\BAVersion\\ScriptTools\\mergeid.xml";
        String originalXmlPath = "E:\\ajm\\BAVersion\\ScriptTools\\oriid2.xml";
        MergePublicXml mergePublicXml = new MergePublicXml(mergXmlPath, originalXmlPath);
        mergePublicXml.merge();
    }
}
