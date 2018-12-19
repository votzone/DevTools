package vot.wq.devtool.androidR.module;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DimenValue extends AndroidValue{

    static Pattern pattern1 = Pattern.compile("<dimen name=\"(.*)\">(.*)</dimen>");
    static Pattern pattern2 = Pattern.compile("<item type=\"dimen\" name=\"(.*)\">(.*)</item>");

    public DimenValue() {
        // default dimen type pattern1
        super("dimen");
    }

    @Override
    public void setValue(String line) {
        Matcher matcher = pattern1.matcher(line);
        if(matcher.find()) {
            attrs.put("name",matcher.group(1));
            values.add(matcher.group(2));
            closed = true;
            return;
        }
        matcher = pattern2.matcher(line);
        if(matcher.find()){
            // reset dimmen for pattern2;
            name = "item";
            attrs.put("type","dimen");
            // add matched  attrs and values
            attrs.put("name",matcher.group(1));
            values.add(matcher.group(2));
            closed = true;
            return;
        }
        closed = false;
    }

}
