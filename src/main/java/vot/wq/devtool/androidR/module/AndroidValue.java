package vot.wq.devtool.androidR.module;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

public abstract class AndroidValue {
    public static final String LinePfx1 = "    ";
    public static final String LinePfx2 = "        ";
    protected String name;
    protected HashMap<String,String> attrs;
    protected ArrayList<String> values;
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

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        int crtDept = depth;
        stringBuilder.append(appendPfx(crtDept));
        stringBuilder.append("<").append(name);

        ArrayList<String> keys = new ArrayList<>();
        for (String key : attrs.keySet()){
            keys.add(key);
        }
        keys.sort(Comparator.reverseOrder());

        for (String key : keys){
            stringBuilder.append(" ").append(key).append("=\"").append(attrs.get(key)).append("\"");
        }
        if(values.size() <= 0){
            stringBuilder.append(" />");
        }else {
            stringBuilder.append(">");
            // todo 需要加 values
            boolean mutiLine = false;
            String first = values.get(0).trim();
            mutiLine = first.startsWith("<") && first.endsWith(">");
            if(mutiLine){
                stringBuilder.append("\n");
                crtDept ++;
                for(String val: values){
                    stringBuilder.append(appendPfx(crtDept)).append(val).append("\n");
                }
                stringBuilder.append("</").append(name).append(">");
                crtDept--;
            }else {
                stringBuilder.append(first).append("</").append(name).append(">");
            }
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

    abstract public void setValue(String line);
    public boolean equals(AndroidValue other){
        if(attrs.containsKey("name") && other.attrs.containsKey("name")){
            return attrs.get("name").equals(other.attrs.get("name"));
        }
        return false;
    }

    protected boolean closed = false;
    public boolean closed(){
        return closed;
    }
}
