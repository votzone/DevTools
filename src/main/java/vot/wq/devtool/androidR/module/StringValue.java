package vot.wq.devtool.androidR.module;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValue extends AndroidValue {
    static Pattern pattern = Pattern.compile("<string name=\"(.*)\">(.*)</string>");
    static Pattern pattern2 = Pattern.compile("<string name=\"(.*)\">(.*)");
    static Pattern pattern3 = Pattern.compile("(.*)</string>");

    public StringValue() {
        super("string");
    }

    @Override
    public void setValue(String line) {
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) {
            attrs.put("name",matcher.group(1));
            values.add(matcher.group(2));
            closed = true;
            return;
        }
        matcher = pattern2.matcher(line);
        if(matcher.find()){
            attrs.put("name", matcher.group(1));
            values.add(matcher.group(2));
            closed = false;
            return;
        }

        String value = line;
        matcher = pattern3.matcher(line);
        if(matcher.find()){
            value = matcher.group(1);
            closed = true;
        }else {
            closed = false;
        }

        if(attrs.containsKey("name")){
            StringBuilder pfx = new StringBuilder();
            pfx.append(values.get(0)).append(new_line_flag)
                    .append(value);
            values.clear();
            values.add(pfx.toString());
        }

    }

    public static final String new_line_flag = "/n";
}
