package vot.wq.devtool.filespliter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileSpliter {

    File inputFile;

    public static final int size = 1024*1024;
    public FileSpliter(String input){
        inputFile = new File(input);
    }

    public void split(){
        try {
            splitUnityFile_byRaf(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void splitUnityFile_byRaf(File ufile) throws IOException {
        System.out.println(ufile.getAbsolutePath());
        RandomAccessFile raf = new RandomAccessFile(ufile,"rw");
        long file_length = raf.length();
        long position = 0;
        long left_file_length = file_length;
        int split_index = 0;

        for (; position <= file_length ;) {
            left_file_length = file_length - position;
            byte[] bytes = null;
            if (left_file_length > size){
                bytes = new byte[size];

            }else {
                bytes = new byte[(int) left_file_length];
            }
            raf.read(bytes);
            position += size;

            FileOutputStream fout = new FileOutputStream(ufile + ".split" + split_index);
            fout.write(bytes);
            fout.flush();
            fout.close();
            split_index ++;
        }

        raf.close();


    }
}
