package vot.wq.devtool.androidR.module;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PublicValue extends AndroidValue{

    static Pattern pattern = Pattern.compile("<public type=\"(.*)\" name=\"(.*)\" id=\"(.*)\" />");
    public PublicValue() {
        super("public");
    }

    @Override
    public void setValue(String line) {
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) {
            attrs.put("type",matcher.group(1));
            attrs.put("name",matcher.group(2));
            attrs.put("id",matcher.group(3));
        }
    }

    @Override
    public boolean equals(AndroidValue obj) {
        if(!super.equals(obj)){
            return false;
        }
        if(obj instanceof PublicValue){
            IdValue other = (IdValue)obj;
            if(attrs.containsKey("type") && other.attrs.containsKey("type")){
                return attrs.get("type").equals(other.attrs.get("type"));
            }
        }
        return false;
    }
}
