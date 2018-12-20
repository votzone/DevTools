package vot.wq.devtool.androidR;

import vot.wq.devtool.androidR.module.manifest.ManifestItem;

import java.io.*;
import java.util.ArrayList;

public class ManifestSimplify {

    private File manifestFile;

    public ManifestSimplify(String file){
        this(new File(file));
    }

    public ManifestSimplify(File file){
        if(file.isFile() && file.exists()){
            manifestFile = file;
        }else {
            throw new RuntimeException("wrong params");
        }
    }

    public void simply() throws IOException {
        ArrayList<ManifestItem> oriValues = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(manifestFile),"utf-8"));

        File tmpFile =new File (manifestFile.getAbsolutePath()+"_");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(tmpFile),"utf-8"));

        String line ;
        ManifestItem androidValue = new ManifestItem();
        while ((line = bufferedReader.readLine())!=null){
            androidValue.setValue(line);
            if(androidValue.closed()){
                if(!oriValues.contains(androidValue)) {
                    oriValues.add(androidValue);
                }

                // 直接写头部
                if(androidValue.getType() == 1){
                    bufferedWriter.write(androidValue.toString());
                    bufferedWriter.write("\n");
                }

                androidValue = new ManifestItem();


            }
        }
        bufferedReader.close();

        // write 2 file
        // 先写 权限
        for (ManifestItem bv: oriValues){
            if(bv.getType() ==4) {
                bufferedWriter.write(bv.toString());
                bufferedWriter.write("\n");
            }
        }
        // 再写 txt
        for (ManifestItem bv: oriValues){
            if(bv.getType() !=4 && bv.getType() !=1) {
                bufferedWriter.write(bv.toString());
                bufferedWriter.write("\n");
            }
        }
        bufferedWriter.close();
        manifestFile.delete();
        tmpFile.renameTo(manifestFile);
    }

}
