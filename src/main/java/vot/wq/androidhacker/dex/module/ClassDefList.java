package vot.wq.androidhacker.dex.module;

public class ClassDefList extends BaseDexModule{
    ClassDefItem [] classDefList;

    public ClassDefList(byte[] buf, int size, int off, StringList stringList,
                        TypeList typeList, FieldList fieldList, MethodList methodList) {
        super(buf, off);

        classDefList = new ClassDefItem[size];
        for (int i=0;i<size;i++){
            classDefList[i] = new ClassDefItem(buf, off, typeList, stringList, fieldList, methodList);
            off += 32;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i =0; i<classDefList.length; i++){
            stringBuilder.append(classDefList[i].toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
