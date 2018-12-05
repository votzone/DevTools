package vot.wq.androidhacker.util;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Adler32;

public class DexUtil {

    public static int readU1(byte[] buf, int off){
        return buf[off] & 0xff;
    }

    public static int readU2(byte[] buf, int off){
        return (buf[off]&0xff) |
                ((buf[off+1] &0xff)<< 8);
    }


    public static int readU4(byte[] buf, int off){
        return (buf[off]&0xff) |
                ((buf[off+1] &0xff)<< 8) |
                ((buf[off+2] &0xff)<< 16) |
                ((buf[off+4] &0xff)<< 24);
    }

    public static String byte2String(byte [] bytes){
        StringBuffer sbuffer = new StringBuffer();
        if(bytes !=null){
            for (byte b: bytes){
                sbuffer.append(Integer.toHexString(Byte.toUnsignedInt(b))).append(" ");
            }
        }
        sbuffer.trimToSize();
        return sbuffer.toString();

    }

    public static void printBytes(byte[] buf){
        if(buf!=null) {
            for (byte b:buf){
                System.out.print(Integer.toHexString(Byte.toUnsignedInt(b))+" ");
            }
            System.out.println();
        }
    }

    public static byte[] getDexSha1(byte[] bytes, int offset, int len){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(bytes, offset, len);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getCheckSum(byte[] bytes, int offset, int len){
        Adler32 checksum = new Adler32();
        checksum.update(bytes, offset, len);
        return checksum.getValue();
    }
    /**
     * @Description 计算二进制数据
     * @return String
     * */
    public static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    public static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }




}
