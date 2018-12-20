package vot.wq.devtool.androidR.module.value;

import vot.wq.devtool.androidR.module.AndroidValue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DrawableValue extends AndroidValue {

    static Pattern pattern = Pattern.compile("<item type=\"drawable\" name=\"(.*)\">(.*)</item>");

    public DrawableValue() {
        super("item");
        attrs.put("type","drawable");
    }

    @Override
    public void setValue(String line) {
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) {
            attrs.put("name",matcher.group(1));
            values.add(matcher.group(2));
            closed = true;
        }else {
            closed = false;
        }
    }

}
