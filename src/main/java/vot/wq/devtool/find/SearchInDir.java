package vot.wq.devtool.find;


import vot.wq.devtool.L;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SearchInDir {
    String dir;
    String keyword;
    String charset ;

    List<String> result;

    // 缓存大小
    byte [] buf = new byte[1024];
    // 要查找的字节数组
    byte [] buf2 = null;

    public SearchInDir(String dir, String keyword,String charset){
        this.dir = dir;
        this.keyword = keyword;
        this.charset = charset;
        buf2 = keywordBytes();

        if(buf.length < buf2.length*2){
            // 要查找的字符长度大于缓存大小
            int len = (buf2.length*2/1024+1)*1024;
            buf = new byte[len];
        }

        result = new ArrayList<>();
    }

    private byte[] keywordBytes(){
        try {
            return keyword.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Wrong Charset");
        }
    }

    private boolean isFindInBuf(){

        for (int i=0;i<buf.length;i++){
            int j;
            for(j =0;j<buf2.length && i<buf.length-buf2.length;j++){
                if(buf[i+j] !=  buf2[j]){
                    break;
                }
            }
            if(j == buf2.length){
                return true;
            }
        }

        return false;
    }


    private void searchFile(File file){
        L.d("Searching In File "+file.getAbsolutePath());
        FileInputStream fileInputStream = null;
        try {
            fileInputStream= new FileInputStream(file);
            while (fileInputStream.read(buf,buf2.length-1,buf.length-buf2.length+1)>0){
                if(isFindInBuf()){
                    result.add(file.getAbsolutePath());
                    break;
                }else {
                    // 将后半段（buf2.length-1）复制到前半段（buf[0]-buf[buf2.length -1]）
                    for(int i=0;i<buf2.length -1;i++){
                        buf[0+i]=buf[buf.length -buf2.length+1+i];
                    }
                }
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchDir(File dir){
        L.d("Searching In Dir "+dir.getAbsolutePath());
        for (File file:dir.listFiles()){
            if(file.isDirectory()){
                searchDir(file);
            }else {
                searchFile(file);
            }
        }
    }


    public void search(){
        File root = new File(dir);
        searchDir(root);
    }

    public void printResult(){
        L.ti("","搜索结果:");
        for (String file: result){
            L.ti("","\t文件 : "+file);
        }
    }
}
