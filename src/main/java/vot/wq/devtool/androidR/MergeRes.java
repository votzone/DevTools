package vot.wq.devtool.androidR;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MergeRes {
    private File nMergeDir;
    private File originalDir;

    public MergeRes(String nMergeDir, String originalDir){
        this(new File(nMergeDir), new File(originalDir));
    }

    public MergeRes(File nMergeDir, File originalDir){

        if((nMergeDir.isDirectory() &&originalDir.isDirectory())){
            if(nMergeDir.getName().equals("res")){
                this.nMergeDir = nMergeDir;
            }else {
                for (File subDir: nMergeDir.listFiles()){
                    if(subDir.getName().equals("res")){
                        this.nMergeDir = subDir;
                    }
                }
            }

            if(originalDir.getName().equals("res")){
                this.originalDir = originalDir;
            }else {
                for (File subDir: originalDir.listFiles()){
                    if(subDir.getName().equals("res")){
                        this.originalDir = subDir;
                    }
                }
            }
        }

        if(this.nMergeDir ==null || this.originalDir ==null){
            throw new RuntimeException("wrong params");
        }

    }

    public void merge() throws IOException {

        for (File subDir: nMergeDir.listFiles()){
            if(!subDir.isDirectory()){
                return;
            }

            File subOri = new File(originalDir,subDir.getName());
            if(!subOri.exists()){
                subOri.mkdirs();
            }

            if(subDir.getName().startsWith("values")){
                MergeValues mergeValues = new MergeValues(subDir, subOri);
                mergeValues.merge();
            }else {
                for (File file: subDir.listFiles()){
                    if(file.isDirectory()){
                        return;
                    }
                    File subOriFile = new File(subOri,file.getName());
                    if(!subOriFile.exists()){
                        Files.copy(file.toPath(), subOriFile.toPath());
                    }
                }
            }
        }
    }
}
