import java.io.File;

public class TestJavaCode {

    @org.junit.Test
    public void filePathTest(){
        File file = new File("/Users/chunleiyan/workspace/androidhacker/dex/cless.dex");
        System.out.println(file.getPath());
    }
}
