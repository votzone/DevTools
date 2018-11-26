package vot.wq.androidhacker.util;

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

}
