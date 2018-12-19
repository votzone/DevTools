package vot.wq.devtool.androidR.module;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdValue extends AndroidValue {

    static Pattern pattern = Pattern.compile("<item type=\"id\" name=\"(.*)\" />");
    public IdValue() {
        super("item");
        attrs.put("type","id");
    }

    public void setValue(String line){
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) {
            attrs.put("name",matcher.group(1));
            closed = true;
        }else {
            closed = false;
        }
    }
}
