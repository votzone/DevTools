package vot.wq.devtool.restf;

import com.overzealous.remark.Options;
import com.overzealous.remark.Remark;
import vot.wq.devtool.util.RestfulUtil;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrabBlog {



    String url,outPath;



    public GrabBlog(String url, String outPath){
        this.url = url;
        this.outPath = outPath;
    }


    public void grab(){
        String html = RestfulUtil.requestHtml(RestfulUtil.getDefaultHttpGet(url));
        try {
            String txt = RestfulUtil.findTextByXpath("", html);
            Remark remark = new Remark(Options.github());
            remark.convert(txt);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

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
                InputStream is = GrabBlog.class.getResourceAsStream("/blogxpath");
                try {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    while ((line = br.readLine()) != null) {
                        String results[]=line.split("===");

                        XpathMap xpathMap = new XpathMap(results[0].trim(),results[1].trim(),results[2].trim());
                        xpathMaps.put(results[0],xpathMap);
                    }
                }catch (IOException e){
                    throw new RuntimeException("no accounts");
                }
            }
        }
    }
}
