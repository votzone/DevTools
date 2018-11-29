package vot.wq.androidhacker.dex.module;

public class MethodItem extends BaseDexModule {


    String className;
    ProtoItem protoItem;
    String name;


    public MethodItem(byte[] buf, int off, StringList stringList, TypeList typeList, ProtoList protoList) {
        super(buf, off);

        className = typeList.array[readU2()];
        protoItem = protoList.protoList[readU2()];
        name = stringList.array[readU4()];
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("className : ").append(className)
                .append(", \tprotoItem : ").append(protoItem.toString())
                .append(", \tname : ").append(name);
        return stringBuilder.toString();
    }
}
