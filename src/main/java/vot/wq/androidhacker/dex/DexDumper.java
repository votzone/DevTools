package vot.wq.androidhacker.dex;

import com.google.common.io.ByteStreams;
import vot.wq.androidhacker.dex.module.DexHeader;
import vot.wq.androidhacker.dex.module.StringList;
import vot.wq.devtool.util.FileUtil;

import java.io.*;

public class DexDumper {

    String dexPath;

    byte[] dexBuf;

    DexHeader dexHeader;
    StringList stringList;

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

    }

    public void dump(){
        if(dexHeader !=null) {
            System.out.println(dexHeader.toString());
        }

        if(stringList !=null){
            System.out.println(stringList.toString());
        }
    }

    public void loadDexHeader(){
        dexHeader = new DexHeader(dexBuf, 0);
    }
}
