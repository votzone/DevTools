package vot.wq.devtool.androidR.module;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttrValue extends AndroidValue {

    static Pattern pattern1 = Pattern.compile("<attr name=\"(.*)\" format=\"(.*)\" />");
    static Pattern pattern2 = Pattern.compile("<attr name=\"(.*)\">");
    static Pattern pattern3 = Pattern.compile("<.* name=\".*\" value=\".*\" />");


    public AttrValue() {
        super("attr");
        closed = false;
    }

    @Override
    public void setValue(String line) {
        line = line.trim();
        if(line.endsWith("/>") || line.endsWith("</attr>")){
            closed = true;
        }

        Matcher matcher = pattern1.matcher(line);
        if(matcher.find()){
            attrs.put("name",matcher.group(1));
            attrs.put("format",matcher.group(2));
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
