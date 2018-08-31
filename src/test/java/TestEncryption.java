
import org.junit.Test;
import vot.wq.devtool.encryption.alg.DES;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class TestEncryption {

    @Test
    public void testDesc() throws UnsupportedEncodingException {
        DES.test();
    }

    @Test
    public void testBase(){
        byte[] result = Base64.getDecoder().decode("U2FsdGVkX1/zko6XOdRXuPqUrp/Ec7vYzlZkQQlg8zs=");

        System.out.println(new String(result));
    }
}
