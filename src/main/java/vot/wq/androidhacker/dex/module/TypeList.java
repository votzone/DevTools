package vot.wq.androidhacker.dex.module;

public class TypeList extends BaseDexModule {


    StringList stringList;

    int offset;
    String array[];

    public TypeList(byte[] buf, int size, int off, StringList stringList) {
        super(buf, off);
        this.offset = off;
        this.stringList = stringList;
        array = new String[size];
        loadTypeList();
    }

    private void loadTypeList(){
        for(int i=0 ; i<array.length ; i++){
            array[i] = stringList.getArray()[readU4()];
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
