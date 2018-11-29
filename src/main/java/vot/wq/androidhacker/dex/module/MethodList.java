package vot.wq.androidhacker.dex.module;

public class MethodList extends BaseDexModule {

    MethodItem [] methodList;

    public MethodList(byte[] buf,int size, int off, StringList stringList, TypeList typeList, ProtoList protoList) {
        super(buf, off);
        methodList= new MethodItem[size];
        for (int i=0;i<size;i++){
            methodList[i]= new MethodItem(buf,off,stringList, typeList,protoList);
            off+= 8;
        }
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i =0; i<methodList.length; i++){
            stringBuilder.append(methodList[i].toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
