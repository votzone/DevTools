package vot.wq.androidhacker.dex.module;

public class FieldList extends BaseDexModule{


    FieldItem[] fieldList;
    public FieldList(byte[] buf, int size, int off, StringList stringList, TypeList typeList) {
        super(buf, off);

        fieldList = new FieldItem[size];
        for (int i =0;i< size;i++){
            fieldList[i]= new FieldItem(buf,off,stringList, typeList);
            off += 8;
        }

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i =0; i<fieldList.length; i++){
            stringBuilder.append(fieldList[i].toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
