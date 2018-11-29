package vot.wq.androidhacker.dex.module;

public class ProtoList extends BaseDexModule {

    ProtoItem [] protoList;

    public ProtoList(byte[] buf, int size, int off, StringList stringList, TypeList typeList) {
        super(buf, off);
        protoList = new ProtoItem[size];
        for(int i=0; i<protoList.length; i++){
            protoList[i] = new ProtoItem(buf, off, stringList, typeList);
            off += 12;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i =0; i<protoList.length; i++){
            stringBuilder.append(protoList[i].toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
