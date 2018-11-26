package vot.wq.androidhacker.dex;

import com.google.common.io.ByteStreams;
import vot.wq.androidhacker.dex.module.DexHeader;
import vot.wq.androidhacker.dex.module.StringList;
import vot.wq.androidhacker.dex.module.TypeList;
import vot.wq.devtool.util.FileUtil;

import java.io.*;

public class DexDumper {

    String dexPath;

    byte[] dexBuf;

    DexHeader dexHeader;
    StringList stringList;
    TypeList typeList;

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
    }

    public void loadDexHeader(){
        dexHeader = new DexHeader(dexBuf, 0);
    }
}
