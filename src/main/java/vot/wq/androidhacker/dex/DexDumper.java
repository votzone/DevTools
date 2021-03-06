package vot.wq.androidhacker.dex;

import com.google.common.io.ByteStreams;
import vot.wq.androidhacker.dex.module.*;
import vot.wq.androidhacker.util.DexUtil;
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
    ClassDefList classDefList;

    public DexDumper(String dexPath){

        this.dexPath = dexPath;
    }

    public void loadDex() throws IOException {
        // load dex file to memory
        InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(this.dexPath)));
        dexBuf = ByteStreams.toByteArray(inputStream);
        inputStream.close();

        dexHeader = new DexHeader(dexBuf, 0);

        stringList = new StringList(dexBuf,dexHeader.getStringIdsSize(),dexHeader.getStringIdsOff());

        typeList = new TypeList(dexBuf, dexHeader.getTypeIdsSize(),dexHeader.getTypeIdsOff(), stringList);

        protoList = new ProtoList(dexBuf, dexHeader.getProtoIdsSize(), dexHeader.getProtoIdsOff(), stringList, typeList);

        fieldList = new FieldList(dexBuf,dexHeader.getFieldIdsSize(),dexHeader.getFieldIdsOff(), stringList, typeList);

        methodList = new MethodList(dexBuf, dexHeader.getMethodIdsSize(), dexHeader.getMethodIdsOff(), stringList, typeList, protoList);

        classDefList = new ClassDefList( dexBuf, dexHeader.getClassDefsSize(), dexHeader.getClassDefsOff(),
                stringList, typeList, fieldList, methodList);

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

        if(classDefList !=null){
            System.out.println("\n\nClass Def List:");
            System.out.println(classDefList.toString());
        }

        System.out.println("\n\nSHA-1 Digest:");
        System.out.println(DexUtil.bufferToHex(DexUtil.getDexSha1(dexBuf, 0x20, dexBuf.length - 0x20)));

        System.out.println("\n\nChecksum:");
        System.out.println(Long.toHexString(DexUtil.getCheckSum(dexBuf, 0xc, dexBuf.length - 0xc)));

    }

}
