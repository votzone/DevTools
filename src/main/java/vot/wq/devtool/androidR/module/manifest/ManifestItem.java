package vot.wq.devtool.androidR.module.manifest;

import vot.wq.devtool.androidR.module.AndroidValue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 分为 1 头 xml 开头 和manifest 开头 2 尾 manifest 结尾
 * 3 纯文本
 * 4 识别的权限
 */
public class ManifestItem extends AndroidValue {

    static Pattern pattern = Pattern.compile("<uses-permission android:name=\"(.*)\"/>");

    //
    static Pattern pattern1 = Pattern.compile("<?xml version=\"(.*)\" encoding=\"(.*)\" standalone=\"(.*)\"?><manifest xmlns:android=\"(.*)\" package=\"(.*)\">");
    private String txt = "";
    private int type = 0;
    public ManifestItem() {
        super("");
        closed = true;
    }

    public int getType(){
        return type;
    }

    @Override
    public void setValue(String line) {
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()){
            this.name = "uses-permission";
            attrs.put("android:name",matcher.group(1));
            type =4;
            return;
        }

        this.txt = line;
        matcher = pattern1.matcher(line);
        if(matcher.find()){
            type = 1;
            return;
        }

        if("</manifest>".equals(line.trim())){
            type = 2;
            return;
        }
        type =3;
    }

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj)){
            return true;
        }
        if(type != 4){
            return false;
        }
        if(obj instanceof ManifestItem){
            ManifestItem other = (ManifestItem) obj;
            return attrs.get("android:name").equals(other.attrs.get("android:name"));
        }
        return false;
    }


    @Override
    public String toString() {
        if(type !=4){
            return txt;
        }
        return super.toString();
    }
}
