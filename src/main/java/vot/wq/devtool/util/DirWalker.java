package vot.wq.devtool.util;

import java.io.File;
import java.io.IOException;

public abstract class DirWalker {

    abstract public void dealOneFile(File file) throws IOException ;
    protected void walkInDir(String dir) throws IOException {
        File p = new File(dir);
        if(p.isFile()){
            dealOneFile(p);
        }else {
            for (File file : p.listFiles()) {
                if (file.isDirectory()) {
                    walkInDir(file.getAbsolutePath());
                }
                dealOneFile(file);
            }
        }
    }
}
