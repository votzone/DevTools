package vot.wq.androidhacker.dex.module;

public class DexClassData extends BaseDexModule {
    int staticFieldsSize;
    int instanceFieldsSize;
    int directMethodsSize;
    int virtualMethodsSize;

    DexField [] staticFields;
    DexField [] instanceFields;
    DexMethod [] directMethods;
    DexMethod[] virtualMethods;

    public DexClassData(byte[] buf, int off, FieldList fieldList, MethodList methodList) {
        super(buf, off);
        staticFieldsSize = readUleb();
        instanceFieldsSize = readUleb();
        directMethodsSize = readUleb();
        virtualMethodsSize = readUleb();

        if(staticFieldsSize >0){
            staticFields = new DexField[staticFieldsSize];
            for (int i=0;i<staticFieldsSize;i++){
                staticFields[i] = new DexField();
                staticFields[i].fieldItem = fieldList.fieldList[readUleb()];
                staticFields[i].accessFlag = readUleb();
            }
        }

        if(instanceFieldsSize >0){
            instanceFields = new DexField[instanceFieldsSize];
            for (int i=0;i<instanceFieldsSize;i++){
                instanceFields[i] = new DexField();
                instanceFields[i].fieldItem = fieldList.fieldList[readUleb()];
                instanceFields[i].accessFlag = readUleb();
            }
        }


        if(directMethodsSize >0){
            directMethods = new DexMethod[directMethodsSize];
            for (int i=0;i<directMethodsSize;i++){
                directMethods[i] = new DexMethod();
                directMethods[i].methodItem = methodList.methodList[readUleb()];
                directMethods[i].accessFalgs = readUleb();
                directMethods[i].codeOff = readUleb();
            }
        }

        if(virtualMethodsSize >0){
            virtualMethods = new DexMethod[virtualMethodsSize];
            for (int i=0;i<virtualMethodsSize;i++){
                virtualMethods[i] = new DexMethod();
                virtualMethods[i].methodItem = methodList.methodList[readUleb()];
                virtualMethods[i].accessFalgs = readUleb();
                virtualMethods[i].codeOff = readUleb();
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if(staticFieldsSize <=0){
            stringBuilder.append("no static Fields\n\n");
        }else {
            stringBuilder.append("static fields: \n");
            for (int i =0;i< staticFields.length;i++){
                stringBuilder.append("\taccessFlags:\t0x").append(Integer.toHexString(staticFields[i].accessFlag)).append("\n")
                        .append("\tfieldItem:\t").append(staticFields[i].fieldItem.toString()).append("\n");
            }
        }

        if(instanceFieldsSize <=0){
            stringBuilder.append("no instance Fields\n\n");
        }else {
            stringBuilder.append("instance fields: \n");
            for (int i =0;i< instanceFields.length;i++){
                stringBuilder.append("\taccessFlags:\t0x").append(Integer.toHexString(instanceFields[i].accessFlag)).append("\n")
                        .append("\tfieldItem:\t").append(instanceFields[i].fieldItem.toString()).append("\n");
            }
        }

        if(directMethodsSize <=0){
            stringBuilder.append("no direct Methods\n\n");
        }else {
            stringBuilder.append("direct Methods: \n");
            for (int i =0;i< directMethods.length;i++){
                stringBuilder.append("\taccessFlags:\t0x").append(Integer.toHexString(directMethods[i].accessFalgs)).append("\n")
                        .append("\tmethodItem:\t").append(directMethods[i].methodItem.toString()).append("\n")
                .append("\tcodeOff:\t0x").append(Integer.toHexString(directMethods[i].codeOff)).append("\n");
            }
        }

        if(virtualMethodsSize <=0){
            stringBuilder.append("no virtual Methods\n\n");
        }else {
            stringBuilder.append("virtual Methods: \n");
            for (int i =0;i< virtualMethods.length;i++){
                stringBuilder.append("\taccessFlags:\t0x").append(Integer.toHexString(virtualMethods[i].accessFalgs)).append("\n")
                        .append("\tmethodItem:\t").append(virtualMethods[i].methodItem.toString()).append("\n")
                        .append("\tcodeOff:\t0x").append(Integer.toHexString(virtualMethods[i].codeOff)).append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
