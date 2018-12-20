package vot.wq.devtool.androidR.module.value;

import vot.wq.devtool.androidR.module.AndroidValue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PublicValue extends AndroidValue {

    private String type,name;
    private int id;

    static Pattern pattern = Pattern.compile("<public type=\"(.*)\" name=\"(.*)\" id=\"(.*)\" />");
    public PublicValue() {
        super("public");
    }

    @Override
    public void setValue(String line) {
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) {
            type = matcher.group(1);
            attrs.put("type", type);
            name = matcher.group(2);
            attrs.put("name", name);
            String sid = matcher.group(3);
            attrs.put("id",sid);
            id = Integer.parseInt(sid.replace("0x",""),16);
            closed = true;
        }else {
            closed = false;
        }
    }

    public void resetId(int id){
        this.id = id;
        String sid = "0x"+Integer.toHexString(id);
        attrs.put("id",sid);
    }

    @Override
    public boolean equals(AndroidValue obj) {
        if(!super.equals(obj)){
            return false;
        }
        if(obj instanceof PublicValue){
            PublicValue other = (PublicValue)obj;
            if(attrs.containsKey("type") && other.attrs.containsKey("type")){
                return attrs.get("type").equals(other.attrs.get("type"));
            }
        }
        return false;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
