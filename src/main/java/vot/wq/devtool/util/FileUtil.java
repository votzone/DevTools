package vot.wq.devtool.util;

import vot.wq.devtool.L;

import java.io.*;
import java.util.regex.Pattern;

public class FileUtil {
    public static void save2File(String path, String content){
        save2File(new File(path),content);
    }

    public static void save2File(File file, String content){
        File dir = file.getParentFile();
        if(!dir.exists()){
            dir.mkdirs();
        }
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file),"utf-8");
            osw.write(content);
            osw.close();
        } catch (IOException e) {
            L.e(e.getMessage());
        }
    }


    private static Pattern FilePattern = Pattern.compile("[\\\\/:*?\"<>|]");
    public static String filenameFilter(String str) {
        return str==null?null:FilePattern.matcher(str).replaceAll("");
    }
}
