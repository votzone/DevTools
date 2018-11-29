package vot.wq.androidhacker.dex.module;

public class ProtoItem extends BaseDexModule {

    String shorty;
    String returnType;
    String [] params;

    StringList stringList;
    TypeList typeList;

    public ProtoItem(byte[] buf, int off, StringList stringList, TypeList typeList) {
        super(buf, off);
        this.stringList = stringList;
        this.typeList = typeList;
        shorty = stringList.array[readU4()];
        returnType = typeList.array[readU4()];

        int paramsOff = readU4();
        if(paramsOff != 0){
            this.crtOff = paramsOff;
            params = new String[readU4()];
            for (int i=0; i<params.length; i++){
                params[i]= typeList.array[readU2()];
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("shorty : ").append(shorty).append(", \treturnType : ").append(returnType);
        if(params !=null){
            stringBuilder.append(", \tparams : [");
            for (String param: params){
                stringBuilder.append(param).append(" , ");
            }
            stringBuilder.append("]");
        }
        return stringBuilder.toString();
    }
}
