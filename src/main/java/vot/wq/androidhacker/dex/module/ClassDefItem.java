package vot.wq.androidhacker.dex.module;

public class ClassDefItem extends BaseDexModule {

    String classId;
    int accessFlag;
    String superClassId;
    int interfaceOff;
    String sourceFileName;
    int annotationsOff;
    int classDataOff;
    int staticValuesOff;

    public ClassDefItem(byte[] buf, int off) {
        super(buf, off);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("classId : ").append(classId);
        return stringBuilder.toString();
    }
}
