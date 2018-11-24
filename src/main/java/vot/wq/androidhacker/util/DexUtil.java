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

    public static int readSleb(byte [] buf, int off){
        int result = 0;

        result = buf[off] &0x7f;
        if(buf[off] < 0){
            result |= (buf[off+1] &0x7f) << 7;

            if(buf[off+1] <0){
                result |= (buf[off+2] &0x7f) << 14;

                if(buf[off+2] <0){
                    result |= (buf[off+3] &0x7f) << 21;

                    if(buf[off+3] <0){
                        result |= (buf[off+4] &0x7f) << 28;
                    }else {
                        result = result << 4 >>4;
                    }
                }else {
                    // third
                    result = result <<11 >>11;
                }
            }else {
                // secend
                result = result <<18 >>18;
            }
        }else {
            // first
            result = result <<25 >>25;
        }

        return result;
    }
}
