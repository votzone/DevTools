package vot.wq.devtool.androidR.module;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdValue extends AndroidValue {

    static Pattern pattern = Pattern.compile("<item type=\"id\" name=\"(.*)\" />");
    static Pattern pattern2 = Pattern.compile("<item type=\"id\" name=\"(.*)\">(.*)</item>");
    public IdValue() {
        super("item");
        attrs.put("type","id");
    }

    public void setValue(String line){
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) {
            attrs.put("name",matcher.group(1));
            closed = true;
            return;
        }
        matcher = pattern2.matcher(line);
        if(matcher.find()) {
            attrs.put("name",matcher.group(1));
            values.add(matcher.group(2));
            closed = true;
            return;
        }
        closed = false;

    }
}
