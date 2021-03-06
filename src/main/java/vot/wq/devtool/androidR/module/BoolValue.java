package vot.wq.devtool.androidR.module;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoolValue extends AndroidValue {

    static Pattern pattern = Pattern.compile("<bool name=\"(.*)\">(.*)</bool>");

    public BoolValue() {
        super("bool");
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
