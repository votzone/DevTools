package vot.wq.devtool.androidR;

import vot.wq.devtool.L;
import vot.wq.devtool.androidR.module.*;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class MergeValues {
    private String nMergeDir;
    private String originalDir;

    public MergeValues(String nMergeDir, String originalDir){
        this.nMergeDir = nMergeDir ;
        this.originalDir = originalDir;
    }

    public void merge() throws IOException {
        File dirMerge = new File(nMergeDir);
        if(dirMerge.exists() &&dirMerge.isDirectory()) {
            for (File valueFile : dirMerge.listFiles()) {
                File oriFile = new File(this.originalDir, valueFile.getName());
                if(oriFile.exists()){
                    if(valueFile.getName().endsWith("arrays.xml")){
                        mergeArrays(valueFile, oriFile);
                    }else if(valueFile.getName().endsWith("attrs.xml")){
                        mergeAttrs(valueFile,oriFile);
                    }else if(valueFile.getName().endsWith("bools.xml")){
                        mergeBools(valueFile,oriFile);
                    }else if(valueFile.getName().endsWith("colors.xml")){
                        mergeColors(valueFile,oriFile);
                    }else if(valueFile.getName().endsWith("dimens.xml")){
                        mergeDimens(valueFile,oriFile);
                    }else if(valueFile.getName().endsWith("drawables.xml")){
                        mergeDrawables(valueFile,oriFile);
                    }else if(valueFile.getName().endsWith("ids.xml")){
                        mergeIds(valueFile,oriFile);
                    }else if(valueFile.getName().endsWith("integers.xml")){
                        mergeIntegers(valueFile,oriFile);
                    }else if(valueFile.getName().endsWith("public.xml")){
                        mergePublic(valueFile,oriFile);
                    }else if(valueFile.getName().endsWith("strings.xml")){
                        mergeStrings(valueFile,oriFile);
                    }else if(valueFile.getName().endsWith("styles.xml")){
                        mergeStyles(valueFile,oriFile);
                    }else {
                        L.e("not support file type");
                    }
                }else {
                    Files.copy(valueFile.toPath(), oriFile.toPath());
                }
            }
        }else {
            throw new RuntimeException("wrong params");
        }
    }

    private void mergeArrays(File mergeFile, File oriFile){
        // todo 1 read all ori values
        // todo 2 read all merge values
        // todo 3 merge to ori and write
    }
    private void mergeAttrs(File mergeFile, File oriFile){
        // todo 1 read all ori values
        // todo 2 read all merge values
        // todo 3 merge to ori and write
    }

    private void mergeBools(File mergeFile, File oriFile) throws IOException {
        // 1 read all ori values
        ArrayList<BoolValue> oriValues = new ArrayList<>();
        ArrayList<BoolValue> mergeValues = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(oriFile),"utf-8"));
        String line ;
        BoolValue boolValue = new BoolValue();
        while ((line = bufferedReader.readLine())!=null){
            boolValue.setValue(line);
            if(boolValue.closed()){
                oriValues.add(boolValue);
                boolValue = new BoolValue();
            }
        }
        bufferedReader.close();

        // 2 read all merge values
        bufferedReader= new BufferedReader(new InputStreamReader(
                new FileInputStream(mergeFile),"utf-8"));
        boolValue = new BoolValue();// 不需要设置的，以防万一
        while ((line = bufferedReader.readLine())!=null){
            boolValue.setValue(line);
            if(boolValue.closed()){
                if(!findAndroidValue(oriValues, boolValue)){
                    mergeValues.add(boolValue);
                }
                boolValue = new BoolValue();
            }
        }
        bufferedReader.close();

        // 3 merge to ori , write to file, replace
        File tmpFile =new File (oriFile.getAbsolutePath()+"_");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(tmpFile),"utf-8"));
        bufferedWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n");
        for (BoolValue bv: oriValues){
            bufferedWriter.write(bv.toString());
            bufferedWriter.write("\n");
        }

        for (BoolValue bv: mergeValues){
            bufferedWriter.write(bv.toString());
            bufferedWriter.write("\n");
        }
        bufferedWriter.write("</resources>\n");
        bufferedWriter.close();

        oriFile.delete();
        tmpFile.renameTo(oriFile);
    }

    public static boolean findAndroidValue(ArrayList<? extends AndroidValue> list, AndroidValue value){
        if(list != null){
            for (AndroidValue av: list){
                if(av.equals(value)){
                    return true;
                }
            }
        }
        return false;
    }


    private void mergeColors(File mergeFile, File oriFile) throws IOException {
        // 1 read all ori values
        ArrayList<ColorValue> oriValues = new ArrayList<>();
        ArrayList<ColorValue> mergeValues = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(oriFile),"utf-8"));
        String line ;
        ColorValue colorValue = new ColorValue();
        while ((line = bufferedReader.readLine())!=null){
            colorValue.setValue(line);
            if(colorValue.closed()){
                oriValues.add(colorValue);
                colorValue = new ColorValue();
            }
        }
        bufferedReader.close();
        // 2 read all merge values
        bufferedReader= new BufferedReader(new InputStreamReader(
                new FileInputStream(mergeFile),"utf-8"));
        colorValue = new ColorValue();// 不需要设置的，以防万一
        while ((line = bufferedReader.readLine())!=null){
            colorValue.setValue(line);
            if(colorValue.closed()){
                if(!findAndroidValue(oriValues, colorValue)){
                    mergeValues.add(colorValue);
                }
                colorValue = new ColorValue();
            }
        }
        bufferedReader.close();
        // 3 merge to ori and write
        File tmpFile =new File (oriFile.getAbsolutePath()+"_");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(tmpFile),"utf-8"));
        bufferedWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n");
        for (ColorValue bv: oriValues){
            bufferedWriter.write(bv.toString());
            bufferedWriter.write("\n");
        }

        for (ColorValue bv: mergeValues){
            bufferedWriter.write(bv.toString());
            bufferedWriter.write("\n");
        }
        bufferedWriter.write("</resources>\n");
        bufferedWriter.close();

        oriFile.delete();
        tmpFile.renameTo(oriFile);
    }
    private void mergeDimens(File mergeFile, File oriFile) throws IOException {
        // 1 read all ori values
        ArrayList<DimenValue> oriValues = new ArrayList<>();
        ArrayList<DimenValue> mergeValues = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(oriFile),"utf-8"));
        String line ;
        DimenValue dimenValue = new DimenValue();
        while ((line = bufferedReader.readLine())!=null){
            dimenValue.setValue(line);
            if(dimenValue.closed()){
                oriValues.add(dimenValue);
                dimenValue = new DimenValue();
            }
        }
        bufferedReader.close();
        // 2 read all merge values
        bufferedReader= new BufferedReader(new InputStreamReader(
                new FileInputStream(mergeFile),"utf-8"));
        dimenValue = new DimenValue();// 不需要设置的，以防万一
        while ((line = bufferedReader.readLine())!=null){
            dimenValue.setValue(line);
            if(dimenValue.closed()){
                if(!findAndroidValue(oriValues, dimenValue)){
                    mergeValues.add(dimenValue);
                }
                dimenValue = new DimenValue();
            }
        }
        bufferedReader.close();
        // 3 merge to ori and write
        File tmpFile =new File (oriFile.getAbsolutePath()+"_");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(tmpFile),"utf-8"));
        bufferedWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n");

        for (DimenValue bv: oriValues){
            bufferedWriter.write(bv.toString());
            bufferedWriter.write("\n");
        }

        for (DimenValue bv: mergeValues){
            bufferedWriter.write(bv.toString());
            bufferedWriter.write("\n");
        }
        bufferedWriter.write("</resources>\n");
        bufferedWriter.close();

        oriFile.delete();
        tmpFile.renameTo(oriFile);
    }
    private void mergeDrawables(File mergeFile, File oriFile) throws IOException {
        // 1 read all ori values
        ArrayList<DrawableValue> oriValues = new ArrayList<>();
        ArrayList<DrawableValue> mergeValues = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(oriFile),"utf-8"));
        String line ;
        DrawableValue drawableValue = new DrawableValue();
        while ((line = bufferedReader.readLine())!=null){
            drawableValue.setValue(line);
            if(drawableValue.closed()){
                oriValues.add(drawableValue);
                drawableValue = new DrawableValue();
            }
        }
        bufferedReader.close();
        // 2 read all merge values
        bufferedReader= new BufferedReader(new InputStreamReader(
                new FileInputStream(mergeFile),"utf-8"));
        drawableValue = new DrawableValue();// 不需要设置的，以防万一
        while ((line = bufferedReader.readLine())!=null){
            drawableValue.setValue(line);
            if(drawableValue.closed()){
                if(!findAndroidValue(oriValues, drawableValue)){
                    mergeValues.add(drawableValue);
                }
                drawableValue = new DrawableValue();
            }
        }
        bufferedReader.close();
        // 3 merge to ori and write
        File tmpFile =new File (oriFile.getAbsolutePath()+"_");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(tmpFile),"utf-8"));
        bufferedWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n");

        for (DrawableValue bv: oriValues){
            bufferedWriter.write(bv.toString());
            bufferedWriter.write("\n");
        }

        for (DrawableValue bv: mergeValues){
            bufferedWriter.write(bv.toString());
            bufferedWriter.write("\n");
        }
        bufferedWriter.write("</resources>\n");
        bufferedWriter.close();

        oriFile.delete();
        tmpFile.renameTo(oriFile);
    }

    private void mergeIds(File mergeFile, File oriFile) throws IOException {
        // 1 read all ori values
        ArrayList<IdValue> oriValues = new ArrayList<>();
        ArrayList<IdValue> mergeValues = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(oriFile),"utf-8"));
        String line ;
        IdValue idValue = new IdValue();
        while ((line = bufferedReader.readLine())!=null){
            idValue.setValue(line);
            if(idValue.closed()){
                oriValues.add(idValue);
                idValue = new IdValue();
            }
        }
        bufferedReader.close();
        // 2 read all merge values
        bufferedReader= new BufferedReader(new InputStreamReader(
                new FileInputStream(mergeFile),"utf-8"));
        idValue = new IdValue();// 不需要设置的，以防万一
        while ((line = bufferedReader.readLine())!=null){
            idValue.setValue(line);
            if(idValue.closed()){
                if(!findAndroidValue(oriValues, idValue)){
                    mergeValues.add(idValue);
                }
                idValue = new IdValue();
            }
        }
        bufferedReader.close();
        // 3 merge to ori and write
        File tmpFile =new File (oriFile.getAbsolutePath()+"_");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(tmpFile),"utf-8"));
        bufferedWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n");

        for (IdValue bv: oriValues){
            bufferedWriter.write(bv.toString());
            bufferedWriter.write("\n");
        }

        for (IdValue bv: mergeValues){
            bufferedWriter.write(bv.toString());
            bufferedWriter.write("\n");
        }
        bufferedWriter.write("</resources>\n");
        bufferedWriter.close();

        oriFile.delete();
        tmpFile.renameTo(oriFile);
    }
    private void mergeIntegers(File mergeFile, File oriFile) throws IOException {
        // 1 read all ori values
        ArrayList<IntegerValue> oriValues = new ArrayList<>();
        ArrayList<IntegerValue> mergeValues = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(oriFile),"utf-8"));
        String line ;
        IntegerValue integerValue = new IntegerValue();
        while ((line = bufferedReader.readLine())!=null){
            integerValue.setValue(line);
            if(integerValue.closed()){
                oriValues.add(integerValue);
                integerValue = new IntegerValue();
            }
        }
        bufferedReader.close();
        // 2 read all merge values
        bufferedReader= new BufferedReader(new InputStreamReader(
                new FileInputStream(mergeFile),"utf-8"));
        integerValue = new IntegerValue();// 不需要设置的，以防万一
        while ((line = bufferedReader.readLine())!=null){
            integerValue.setValue(line);
            if(integerValue.closed()){
                if(!findAndroidValue(oriValues, integerValue)){
                    mergeValues.add(integerValue);
                }
                integerValue = new IntegerValue();
            }
        }
        bufferedReader.close();
        // 3 merge to ori and write
        File tmpFile =new File (oriFile.getAbsolutePath()+"_");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(tmpFile),"utf-8"));
        bufferedWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n");

        for (IntegerValue bv: oriValues){
            bufferedWriter.write(bv.toString());
            bufferedWriter.write("\n");
        }

        for (IntegerValue bv: mergeValues){
            bufferedWriter.write(bv.toString());
            bufferedWriter.write("\n");
        }
        bufferedWriter.write("</resources>\n");
        bufferedWriter.close();

        oriFile.delete();
        tmpFile.renameTo(oriFile);
    }
    private void mergePublic(File mergeFile, File oriFile) throws IOException {
        // 1 read all ori values
        ArrayList<PublicValue> oriValues = new ArrayList<>();
        ArrayList<PublicValue> mergeValues = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(oriFile),"utf-8"));
        String line ;
        PublicValue publicValue = new PublicValue();
        while ((line = bufferedReader.readLine())!=null){
            publicValue.setValue(line);
            if(publicValue.closed()){
                oriValues.add(publicValue);
                publicValue = new PublicValue();
            }
        }
        bufferedReader.close();
        // 2 read all merge values
        bufferedReader= new BufferedReader(new InputStreamReader(
                new FileInputStream(mergeFile),"utf-8"));
        publicValue = new PublicValue();// 不需要设置的，以防万一
        while ((line = bufferedReader.readLine())!=null){
            publicValue.setValue(line);
            if(publicValue.closed()){
                if(!findAndroidValue(oriValues, publicValue)){
                    mergeValues.add(publicValue);
                }
                publicValue = new PublicValue();
            }
        }
        bufferedReader.close();
        // todo ************************ 3 merge public to ori and write ************************
        File tmpFile =new File (oriFile.getAbsolutePath()+"_");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(tmpFile),"utf-8"));
        bufferedWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n");

        for (PublicValue bv: oriValues){
            bufferedWriter.write(bv.toString());
            bufferedWriter.write("\n");
        }

        for (PublicValue bv: mergeValues){
            bufferedWriter.write(bv.toString());
            bufferedWriter.write("\n");
        }
        bufferedWriter.write("</resources>\n");
        bufferedWriter.close();

        oriFile.delete();
        tmpFile.renameTo(oriFile);

    }

    private void mergeStrings(File mergeFile, File oriFile){
        // todo 1 read all ori values
        // todo 2 read all merge values
        // todo 3 merge to ori and write
    }private void mergeStyles(File mergeFile, File oriFile){
        // todo 1 read all ori values
        // todo 2 read all merge values
        // todo 3 merge to ori and write
    }


}
