package vot.wq.devtool.unity2ios;

import vot.wq.devtool.util.DirWalker;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CombinAsset extends DirWalker {
    private static Map<String,String> combined = new HashMap<>();
    private static byte[] buf = new byte[1024];
    public void dealOneFile(File file) throws IOException {

        if(combined.containsKey(file.getName())){
            return;
        }
        String filename = file.getAbsolutePath();
        if(!filename.contains(".")){
            return;
        }
        String ext = filename.substring(filename.lastIndexOf(".")+1);
        if(!ext.equals("split0")){
            return;
        }
        System.out.println(file.getName());
        String fileOut =filename.substring(0,filename.lastIndexOf("."));
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(fileOut)));
        int sufx =0;
        File sFile = new File(filename.substring(0,filename.length()-1)+sufx);
        while (sFile.exists()){
            combined.put(sFile.getName(),fileOut);
            DataInputStream dis = new DataInputStream(new FileInputStream(sFile));
            int len = dis.read(buf);
            while (len>0){
                dos.write(buf,0,len);
                len= dis.read(buf);
            }
            dis.close();
            sufx ++;
            sFile = new File(filename.substring(0,filename.length()-1)+sufx);
        }
        dos.close();
    }

    public static void main(String args[]) throws IOException {
        String filename  ="D:\\mykiller\\projects\\火柴人2-白包-新ui-翻译调整\\Project\\assets\\bin\\test2";
        new CombinAsset().walkInDir(filename);
    }
}
