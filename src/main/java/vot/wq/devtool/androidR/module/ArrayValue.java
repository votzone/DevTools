package vot.wq.devtool.androidR.module;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrayValue extends AndroidValue {
    static Pattern pattern2 = Pattern.compile("<array name=\"(.*)\">");
    static Pattern pattern3 = Pattern.compile("<item>.*</item>");

    public ArrayValue() {
        super("array");
        closed = false;
    }

    @Override
    public void setValue(String line) {
        line = line.trim();
        if(line.endsWith("/>") || line.endsWith("</array>")){
            closed = true;
        }

        Matcher matcher = pattern2.matcher(line);
        if(matcher.find()){
            attrs.put("name",matcher.group(1));
            return;
        }

        matcher = pattern3.matcher(line);
        if(matcher.matches()){
            values.add(line);
        }
    }
}
