package vot.wq.androidhacker.dex;

import com.google.common.io.ByteStreams;
import vot.wq.androidhacker.dex.module.*;
import vot.wq.devtool.util.FileUtil;

import java.io.*;

public class DexDumper {

    String dexPath;

    byte[] dexBuf;

    DexHeader dexHeader;
    StringList stringList;
    TypeList typeList;
    ProtoList protoList;
    FieldList fieldList;
    MethodList methodList;

    public DexDumper(String dexPath){

        this.dexPath = dexPath;
    }

    public void loadDex() throws IOException {
        // load dex file to memory
        InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(this.dexPath)));
        dexBuf = ByteStreams.toByteArray(inputStream);
        inputStream.close();

        loadDexHeader();

        stringList = new StringList(dexBuf,dexHeader.getStringIdsSize(),dexHeader.getStringIdsOff());

        typeList = new TypeList(dexBuf, dexHeader.getTypeIdsSize(),dexHeader.getTypeIdsOff(), stringList);

        protoList = new ProtoList(dexBuf, dexHeader.getProtoIdsSize(), dexHeader.getProtoIdsOff(), stringList, typeList);

        fieldList = new FieldList(dexBuf,dexHeader.getFieldIdsSize(),dexHeader.getFieldIdsOff(), stringList, typeList);


        methodList = new MethodList(dexBuf, dexHeader.getMethodIdsSize(), dexHeader.getFieldIdsOff(), stringList, typeList, protoList);
    }

    public void dump(){
        if(dexHeader !=null) {
            System.out.println("Dex Header:");
            System.out.println(dexHeader.toString());
        }

        if(stringList !=null){
            System.out.println("\n\nString List:");
            System.out.println(stringList.toString());
        }

        if(typeList !=null){
            System.out.println("\n\nType List:");
            System.out.println(typeList.toString());
        }

        if(protoList !=null){
            System.out.println("\n\nProto List:");
            System.out.println(protoList.toString());
        }

        if(fieldList !=null){
            System.out.println("\n\nField List:");
            System.out.println(fieldList.toString());
        }

        if(methodList !=null){
            System.out.println("\n\nMethod List:");
            System.out.println(methodList.toString());
        }


    }

    public void loadDexHeader(){
        dexHeader = new DexHeader(dexBuf, 0);
    }
}
