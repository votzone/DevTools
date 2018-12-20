package vot.wq.devtool.androidR;

import vot.wq.devtool.L;
import vot.wq.devtool.androidR.module.*;
import vot.wq.devtool.androidR.module.value.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.ArrayList;

public class MergeValues {
    private String nMergeDir;
    private String originalDir;

    public MergeValues(String nMergeDir, String originalDir){
        this.nMergeDir = nMergeDir ;
        this.originalDir = originalDir;
    }

    public MergeValues(File nMergeDir, File originalDir){
        this.nMergeDir = nMergeDir.getAbsolutePath() ;
        this.originalDir = originalDir.getAbsolutePath();
    }

    public void merge() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        File dirMerge = new File(nMergeDir);
        if(dirMerge.exists() &&dirMerge.isDirectory()) {
            for (File valueFile : dirMerge.listFiles()) {
                File oriFile = new File(this.originalDir, valueFile.getName());
                if(oriFile.exists()){
                    if(valueFile.getName().endsWith("arrays.xml")){
                        mergeAndroidValue(valueFile,oriFile, ArrayValue.class);
                    }else if(valueFile.getName().endsWith("attrs.xml")){
                        mergeAndroidValue(valueFile,oriFile, AttrValue.class);
                    }else if(valueFile.getName().endsWith("bools.xml")){
                        mergeAndroidValue(valueFile,oriFile, BoolValue.class);
                    }else if(valueFile.getName().endsWith("colors.xml")){
                        mergeAndroidValue(valueFile,oriFile, ColorValue.class);
                    }else if(valueFile.getName().endsWith("dimens.xml")){
                        mergeAndroidValue(valueFile,oriFile, DimenValue.class);
                    }else if(valueFile.getName().endsWith("drawables.xml")){
                        mergeAndroidValue(valueFile,oriFile, DrawableValue.class);
                    }else if(valueFile.getName().endsWith("ids.xml")){
                        mergeAndroidValue(valueFile,oriFile, IdValue.class);
                    }else if(valueFile.getName().endsWith("integers.xml")){
                        mergeAndroidValue(valueFile,oriFile, IntegerValue.class);
                    }else if(valueFile.getName().endsWith("public.xml")){
                        mergePublic(valueFile,oriFile);
                    }else if(valueFile.getName().endsWith("strings.xml")){
                        mergeAndroidValue(valueFile,oriFile, StringValue.class);
                    }else if(valueFile.getName().endsWith("styles.xml")){
                        mergeAndroidValue(valueFile,oriFile, StyleValue.class);
                    }else {
                        L.e("not support file type :"+ oriFile.getAbsolutePath());
                    }
                }else {
                    Files.copy(valueFile.toPath(), oriFile.toPath());
                }
            }
        }else {
            throw new RuntimeException("wrong params");
        }
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

    private <T extends AndroidValue> void mergeAndroidValue(File mergeFile, File oriFile, Class<T> tClass)
            throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 1 read all ori values
        ArrayList<T> oriValues = new ArrayList<>();
        ArrayList<T> mergeValues = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(oriFile),"utf-8"));
        String line ;
        T androidValue = tClass.getDeclaredConstructor().newInstance();
        while ((line = bufferedReader.readLine())!=null){
            androidValue.setValue(line);
            if(androidValue.closed()){
                oriValues.add(androidValue);
                androidValue = tClass.getDeclaredConstructor().newInstance();
            }
        }
        bufferedReader.close();
        // 2 read all merge values
        bufferedReader= new BufferedReader(new InputStreamReader(
                new FileInputStream(mergeFile),"utf-8"));
        androidValue = tClass.getDeclaredConstructor().newInstance();// 不需要设置的，以防万一
        while ((line = bufferedReader.readLine())!=null){
            androidValue.setValue(line);
            if(androidValue.closed()){
                if(!findAndroidValue(oriValues, androidValue)){
                    mergeValues.add(androidValue);
                }
                androidValue = tClass.getDeclaredConstructor().newInstance();
            }
        }
        bufferedReader.close();
        // 3 merge to ori and write
        File tmpFile =new File (oriFile.getAbsolutePath()+"_");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(tmpFile),"utf-8"));
        bufferedWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n");
        for (T bv: oriValues){
            bufferedWriter.write(bv.toString());
            bufferedWriter.write("\n");
        }

        for (T bv: mergeValues){
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
        // 3 merge public to ori and write

        ArrayList<PublicValue> merged = new ArrayList<>();

        // oriTypeIndex 排序依据，根据orivalues出现的先后顺序而定
        ArrayList<String> oriTypeIndex = new ArrayList<>();

        // 遍历 orivalues 生成一个有序的type 数组，后续根据每个type来merge
        for (PublicValue bv: oriValues){
            // 根据出现的先后顺序排序
            if(!oriTypeIndex.contains(bv.getType())){
                oriTypeIndex.add(bv.getType()) ;
            }
        }

        // 根据 type 合并 orivalues 和 mergevalues
        int all_max_id = 0;
        for(String crtType: oriTypeIndex){
            int type_max = -1;
            for(PublicValue pv: oriValues){
                if(pv.getType().equals(crtType)) {
                    merged.add(pv);
                    // 查找该type 最大的id
                    type_max = pv.getId() > type_max?pv.getId():type_max;
                    // 查找所有type 最大的id
                    all_max_id = pv.getId() > all_max_id? pv.getId(): all_max_id;
                }
            }

            // 根据 type_max 重置 id后 合并
            for (PublicValue pv: mergeValues){
                if(pv.getType().equals(crtType)){
                    pv.resetId(++type_max);
                    merged.add(pv);
                }
            }
        }

        // 处理 mergevalues 独有的type 类型
        ArrayList<String> mergeTypeIndex = new ArrayList<>();
        for (PublicValue pv: mergeValues){
            if(!oriTypeIndex.contains(pv.getType())&& !mergeTypeIndex.contains(pv.getType())){
                mergeTypeIndex.add(pv.getType());
            }
        }
        for (String megType: mergeTypeIndex){
            all_max_id = (all_max_id >>16 +1)<<16;
            for (PublicValue pv: mergeValues){
                if(pv.getType().equals(megType)){
                    pv.resetId(++all_max_id);
                    merged.add(pv);
                }
            }
        }

        // write 2 file
        File tmpFile =new File (oriFile.getAbsolutePath()+"_");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(tmpFile),"utf-8"));
        bufferedWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n");
        for (PublicValue bv: merged){
            bufferedWriter.write(bv.toString());
            bufferedWriter.write("\n");
        }
        bufferedWriter.write("</resources>\n");
        bufferedWriter.close();

        oriFile.delete();
        tmpFile.renameTo(oriFile);

    }
}
