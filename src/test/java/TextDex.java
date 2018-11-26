import org.junit.Test;
import vot.wq.androidhacker.dex.DexDumper;
import vot.wq.androidhacker.dex.module.BaseDexModule;
import vot.wq.androidhacker.util.DexUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TextDex {

    @Test
    public void testShift(){
        byte b = -0x1;
        System.out.println(b < 0);
    }


    @Test
    public void testReadSleb(){
        byte[] bytes={-0x71,-0x2,0x4f,0x4,0x5};
        BaseDexModule bdm = new BaseDexModule(bytes,0);
        int result = bdm.readSleb(0);
        System.out.println(Integer.toHexString(result));
    }

    @Test
    public void testDexMagic() {
        byte[] magic = "dex\n035\u0000".getBytes();

        DexUtil.printBytes(magic);
    }

    @Test
    public void testDumpDex() throws IOException {
        String dexPath ="/Users/chunleiyan/workspace/androidhacker/dex/Hello.dex";
        DexDumper dumper = new DexDumper(dexPath);
        dumper.loadDex();
        dumper.dump();

    }

    @Test
    public void testString(){
        String test = "<init>";
        System.out.println(test.getBytes().length);
    }
}
