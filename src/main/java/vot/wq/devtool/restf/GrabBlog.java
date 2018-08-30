package vot.wq.devtool.restf;

import com.overzealous.remark.Options;
import com.overzealous.remark.Remark;
import org.apache.http.util.TextUtils;
import vot.wq.devtool.util.FileUtil;
import vot.wq.devtool.util.RestfulUtil;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrabBlog {



    String url,outPath;
    String title,content;


    public GrabBlog(String url, String outPath){
        this.url = url;
        this.outPath = outPath;
    }

    public XpathMap getxpath(){
        for (String key :XpathMap.xpathMaps.keySet()){
            if(url.startsWith(key)){
                return XpathMap.xpathMaps.get(key);
            }
        }
        return XpathMap.xpathMaps.get("default");
    }

    public void grab(){
        String html = RestfulUtil.requestHtml(RestfulUtil.getDefaultHttpGet(url));
        XpathMap xpathMap = getxpath();
        assert xpathMap !=null;

        try {
            title = RestfulUtil.findTextByXpath(xpathMap.titleXpath, html).trim();
            content = RestfulUtil.findHtmlByXpath(xpathMap.contentXpath, html);
            Options options = Options.github();
            options.autoLinks = true;
            options.inlineLinks = true;
            Remark remark = new Remark(options);
            content = remark.convert(content);

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }




    public void save(){
        File save2 = new File(outPath,FileUtil.filenameFilter(title)+".md");
        FileUtil.save2File(save2,"#"+title+"\n\n"+content);
    }


    static class XpathMap{
        String host;
        String titleXpath;
        String contentXpath;

        public XpathMap(String host, String titleXpath, String contentXpath){
            this.host = host;
            this.contentXpath = contentXpath;
            this.titleXpath = titleXpath;
        }

        public static Map<String,XpathMap> xpathMaps = new HashMap<>();

        // 静态代码块
        static {
            if(xpathMaps.size()<=0){

                XpathMap def = new XpathMap("default","/html/head/title","/html/body");
                xpathMaps.put("default",def);

                InputStream is = GrabBlog.class.getResourceAsStream("/blogxpath");
                try {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    while ((line = br.readLine()) != null) {
                        String results[]=line.split("===");

                        XpathMap xpathMap = new XpathMap(results[0].trim(),results[2].trim(),results[1].trim());
                        xpathMaps.put(results[0],xpathMap);
                    }
                }catch (IOException e){
                    throw new RuntimeException("no accounts");
                }
            }
        }
    }
}
