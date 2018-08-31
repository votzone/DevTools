package vot.wq.devtool.encryption.alg;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Base64;

public class DES {

    public static void test() throws UnsupportedEncodingException {
//待加密内容
        String str = "db64";
//密码，长度要是8的倍数
        String password = "9588028820109132570743325311898426347857298773549468758875018579537757772163084478873699447306034466200616411960574122434059469100235892702736860872901247123456";

        byte[] result = DES.encrypt(str.getBytes("utf-8"),password);
        byte[] b64 = Base64.getEncoder().encode(result);
        String str2 = new String(b64);
        System.out.println("加密后："+str2);
        byte[] s2bytes= str2.getBytes();
        byte[] db64 = Base64.getDecoder().decode(s2bytes);

//直接将如上内容解密
        try {
            byte[] decryResult = DES.decrypt(db64, password);
            System.out.println("解密后："+new String(decryResult));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }


    public static byte[] encrypt(byte[] datasource, String password) {
        try{
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
//创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
//Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
//用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
//现在，获取数据并加密
//正式执行加密操作
            return cipher.doFinal(datasource);
        }catch(Throwable e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * @param src byte[]
     * @param password String
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String password) throws Exception {
// 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
// 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
// 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
// Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
// 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey);
// 真正开始解密操作
        return cipher.doFinal(src);
    }
}
