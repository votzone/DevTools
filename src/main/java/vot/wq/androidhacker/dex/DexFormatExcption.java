package vot.wq.androidhacker.dex;

public class DexFormatExcption extends RuntimeException {

    public DexFormatExcption(){
        super("DexFormatException");
    }

    public DexFormatExcption(String message){
        super(message);
    }
}
