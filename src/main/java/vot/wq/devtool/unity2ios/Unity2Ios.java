package vot.wq.devtool.unity2ios;

import vot.wq.devtool.util.DirWalker;

import java.io.*;

public class Unity2Ios extends DirWalker {

    static byte[] buf = new byte[1024];

    public void dealOneFile(File file) throws IOException {
        if(file.isDirectory()){
            return;
        }
        if(file.getName().endsWith("dll")){
            return;
        }
        String filename = file.getName();
        if(filename.contains(".")){
            String ext = filename.substring(filename.lastIndexOf(".")+1);
            if(ext.startsWith("split")&&!ext.equals("split0")){
                return;
            }
            if(ext.equals("resource")){return;}
            if(ext.equals("xml")){return;}
            if(ext.equals("png")){return;}
        }
        DataInputStream fis = new DataInputStream( new FileInputStream(file));
        File outfile = new File(file.getAbsolutePath()+"_");
        DataOutputStream fos = new DataOutputStream(new FileOutputStream(outfile));

//        读取前0x14个byte，直接写入；
//        接下来是个字符串，以0x00结尾
//        字符串结束之后是平台标记
        byte [] tmp = new byte[0x14];
        fis.read(tmp);
        fos.write(tmp);
        byte b;
        while ((b = fis.readByte())!=0){
            fos.writeByte(b);
        }
        fos.writeByte(b);
        int value = fis.readInt();
        if(value == 0x0d000000) {
            fos.writeInt(0x09000000);
        }else {
            fos.writeInt(value);
        }
        int len = fis.read(buf);
        while (len>0) {
            fos.write(buf, 0, len);
            len = fis.read(buf);
        }
        fis.close();
        fos.close();
        file.delete();
        outfile.renameTo(file);
    }


    public static void main(String args[]) throws IOException {
        String dirpath = "D:\\用户目录\\我的文档\\Tencent Files\\944844760\\FileRecv\\test\\assets\\bin\\test2";
        new Unity2Ios().walkInDir(dirpath);
    }
}
