package vot.wq.androidhacker.dex.module;

public class FieldItem extends BaseDexModule {

    String className;
    String typeName;
    String name;

    public FieldItem(byte[] buf, int off, StringList stringList, TypeList typeList) {
        super(buf, off);

        className = typeList.array[readU2()];
        typeName = typeList.array[readU2()];
        name = stringList.array[readU4()];

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("className : ").append(className)
                .append(", \ttypeName : ").append(typeName)
                .append(", \tname : ").append(name);
        return stringBuilder.toString();
    }
}
