package vot.wq.devtool.androidR.module;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StyleValue extends AndroidValue {
    static Pattern pattern1 = Pattern.compile("<style name=\"(.*)\" parent=\"(.*)\" />");
    static Pattern pattern11 = Pattern.compile("<style name=\"(.*)\" parent=\"(.*)\">");
    static Pattern pattern2 = Pattern.compile("<style name=\"(.*)\">");
    static Pattern pattern3 = Pattern.compile("<item.*</item>");

    public StyleValue() {
        super("style");
        closed = false;
    }

    @Override
    public void setValue(String line) {
        line = line.trim();
        if(line.endsWith("/>") || line.endsWith("</style>")){
            closed = true;
        }

        Matcher matcher = pattern1.matcher(line);
        if(matcher.find()){
            attrs.put("name",matcher.group(1));
            attrs.put("parent",matcher.group(2));
            return;
        }
        matcher = pattern11.matcher(line);
        if(matcher.find()){
            attrs.put("name",matcher.group(1));
            attrs.put("parent",matcher.group(2));
            return;
        }

        matcher = pattern2.matcher(line);
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
