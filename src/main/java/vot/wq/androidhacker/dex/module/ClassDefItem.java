package vot.wq.androidhacker.dex.module;

public class ClassDefItem extends BaseDexModule {

    String classId;
    int accessFlag;
    String superClassId;
    int interfaceOff;
    String [] interfaceList;
    String sourceFileName;

    // todo 指向注解目录结构，根据类型不同会有注解类，注解方法，注解字段与注解参数，无注解则为0
    int annotationsOff;
    int classDataOff;
    DexClassData dexClassData;
    int staticValuesOff;
    // todo DexEncodedArray 结构，记录了类的静态结构

    StringList stringList;
    TypeList typeList;

    public ClassDefItem(byte[] buf, int off, TypeList typeList, StringList stringList) {
        super(buf, off);
        this.stringList = stringList;
        this.typeList = typeList;
        classId = typeList.array[readU4()];
        accessFlag = readU4();
        superClassId = typeList.array[readU4()];
        interfaceOff = readU4();
        int sourceFileNameIdx= readU4();
        if(sourceFileNameIdx >0) {
            sourceFileName = stringList.array[sourceFileNameIdx];
        }else {
            sourceFileName = "";
        }

        annotationsOff = readU4();
        classDataOff = readU4();
        staticValuesOff = readU4();

        loadSubModels();
    }

    private void loadSubModels(){
        // interfaceOff
        if(interfaceOff !=0){
            this.crtOff = interfaceOff;
            interfaceList = new String[readU4()];
            for (int i=0;i<interfaceList.length;i++){
                interfaceList[i]= typeList.array[readU2()];
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("classId : ").append(classId).append("\n")
                .append("\taccessFlag : 0x").append(Integer.toHexString(accessFlag)).append("\n")
                .append("\tsuperClassId : ").append(superClassId).append("\n")
                .append("\tinterfaceOff : 0x").append(Integer.toHexString(interfaceOff)).append("\n");
                if(interfaceList!=null){
                    stringBuilder.append("\t\tInterfaces [");
                    for (String int_f: interfaceList){
                        stringBuilder.append(int_f).append(",");
                    }
                    stringBuilder.append("]");
                }
        stringBuilder.append("\tsourceFileName : ").append(sourceFileName).append("\n")
                .append("\tannotationsOff : 0x").append(Integer.toHexString(annotationsOff)).append("\n")
                .append("\tclassDataOff : 0x").append(Integer.toHexString(classDataOff)).append("\n")
                .append("\tstaticValuesOff : 0x").append(Integer.toHexString(staticValuesOff)).append("\n\n");
        return stringBuilder.toString();
    }
}
