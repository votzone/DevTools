package vot.wq.androidhacker.dex.module;

import org.jsoup.Connection;
import vot.wq.androidhacker.util.DexUtil;

public class BaseDexModule {
    protected byte[] buf;
    protected int crtOff;
    protected int baseOff;

    public BaseDexModule(byte[] buf, int off){
        this.buf = buf;
        this.crtOff = off;
        this.baseOff = off;
    }

    public int readU1(){
        return buf[crtOff++] & 0xff;
    }
    public int readU1(int off){
        crtOff = baseOff +off;
        return readU1();
    }

    public  int readU2(){
        return (buf[crtOff++]&0xff) |
                ((buf[crtOff++] &0xff)<< 8);
    }

    public  int readU2(int off){
        crtOff = baseOff +off;
        return readU2();
    }


    public int readU4(){
        return (buf[crtOff++]&0xff) |
                ((buf[crtOff++] &0xff)<< 8) |
                ((buf[crtOff++] &0xff)<< 16) |
                ((buf[crtOff++] &0xff)<< 24);
    }
    public int readU4(int off){
        crtOff = baseOff +off;
        return readU4();
    }

    public int readSleb(int off){
        crtOff = baseOff+off;
        return readSleb();
    }
    public int readSleb(){
        int result = 0;
        int current = buf[crtOff++];
        result = current &0x7f;
        if(current <0){
            current = buf[crtOff++];
            result |= (current &0x7f) << 7;
            if(current <0){
                current = buf[crtOff++];
                result |= (current &0x7f) << 14;
                if(current <0 ){
                    current = buf[crtOff++];
                    result |= (current &0x7f) << 21;
                    if(current <0){
                        current = buf[crtOff++];
                        result |= (current &0x7f) << 28;
                        // fifth no need shifting
                    }else {
                        // fourth
                        result = result << 4 >>4; }
                }else {
                    // third

                    result = result <<11 >>11; }
            }else {
                // secend
                result = result <<18 >>18; }
        }else {
            // first
            result = result <<25 >>25; }
        return result;
    }

    public int readUleb(int off){
        crtOff = baseOff+off;
        return readUleb();
    }
    public int readUleb(){
        int result = 0;
        int current = buf[crtOff++];
        // first
        result = current &0x7f;
        if(current <0){
            // secend
            current = buf[crtOff++];
            result |= (current &0x7f) << 7;
            if(current <0){
                // third
                current = buf[crtOff++];
                result |= (current &0x7f) << 14;
                if(current <0 ){
                    // fourth
                    current = buf[crtOff++];
                    result |= (current &0x7f) << 21;
                    if(current <0){
                        current = buf[crtOff++];
                        result |= (current &0x7f) << 28;
                        // fifth no need shifting
                    }
                }
            }
        }
        return result;
    }

    public int readUlebp(int off){
        return readUleb(off)+1;
    }
    public int readUlebp(){
        return readUleb()+1;
    }

    public void readBytes(byte[] out){
        if(out !=null){
            for (int i=0;i<out.length;i++){
                out[i] = buf[crtOff++];
            }
        }
    }

    public String readString(){
        byte[] bytes = new byte[readUleb()];
        readBytes(bytes);
        return new String(bytes);
    }
}
