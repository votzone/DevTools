package vot.wq.devtool.androidR.module;

import java.util.ArrayList;
import java.util.HashMap;

public class AndroidValue {
    public static final String LinePfx1 = "    ";
    public static final String LinePfx2 = "        ";
    private String name;
    private HashMap<String,String> attrs;
    private ArrayList<String> values;
    int depth = 1;

    public AndroidValue(String name){
        this.name = name;
        attrs = new HashMap<>();
        values = new ArrayList<>();
    }

    public String toString(int depth){
        this.depth = depth;
        return toString();
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        int crtDept = depth;
        stringBuilder.append(appendPfx(crtDept));
        stringBuilder.append("<").append(name);
        for (String key : attrs.keySet()){
            stringBuilder.append(" ").append(key).append("=").append(attrs.get(key));
        }
        if(values.size() <= 0){
            stringBuilder.append(" />");
        }else {
            // todo 需要加 values
        }

        return stringBuilder.toString();
    }


    private static String appendPfx(int depth){
        if(depth ==0){
            return "";
        }
        if(depth ==1){
            return LinePfx1;
        }
        if(depth ==2){
            return LinePfx2;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<depth;i++){
            stringBuilder.append(LinePfx1);
        }
        return stringBuilder.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, String> getAttrs() {
        return attrs;
    }

    public void setAttrs(HashMap<String, String> attrs) {
        this.attrs = attrs;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
    }

}
