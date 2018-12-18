package vot.wq.devtool.androidR.module;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntegerValue extends AndroidValue {
    static Pattern pattern = Pattern.compile("<integer name=\"(.*)\">(.*)</integer>");
    public IntegerValue() {
        super("integer");
    }

    @Override
    public void setValue(String line) {
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) {
            attrs.put("name",matcher.group(1));
            values.add(matcher.group(2));
        }
    }
}
