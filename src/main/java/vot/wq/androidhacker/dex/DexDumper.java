package vot.wq.androidhacker.dex;

import com.google.common.io.ByteStreams;
import vot.wq.devtool.util.FileUtil;

import java.io.*;

public class DexDumper {

    String dexPath;

    byte[] dexBuf;

    public DexDumper(String dexPath){

        this.dexPath = dexPath;
    }

    public void loadDex() throws IOException {
        // load dex file to memory
        InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(this.dexPath)));
        dexBuf = ByteStreams.toByteArray(inputStream);
        inputStream.close();

        loadDexHeader();
    }

    public void loadDexHeader(){

    }
}
