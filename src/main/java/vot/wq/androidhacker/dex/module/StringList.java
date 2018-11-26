package vot.wq.androidhacker.dex.module;

public class StringList extends BaseDexModule{

    int offset;
    String[] array;

    public StringList(byte[] buf, int size, int off) {
        super(buf, off);
        this.offset = off;
        array = new String[size];
        loadStringList();
    }

    public String[] getArray(){
        return array;
    }
    private void loadStringList(){
        for(int i=0;i<array.length;i++){
            int stringOff = readU4();
            int currentOffset = this.crtOff;
            // read string from stringOff
            this.crtOff = stringOff;
            array[i] = readString();
            this.crtOff = currentOffset;
        }
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        int idx = 0;
        for(String str: array){
            stringBuffer.append(idx++).append(" : ").append(str).append("\n");
        }
        return stringBuffer.toString();
    }
}
