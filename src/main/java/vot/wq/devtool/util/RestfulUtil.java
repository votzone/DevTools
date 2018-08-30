package vot.wq.devtool.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.nodes.Element;
import org.seimicrawler.xpath.JXDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import vot.wq.devtool.L;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.util.List;

public class RestfulUtil {

    public static Boolean lock =false;
    private static RestfulUtil instance;
    private HttpClient httpClient;
    private RestfulUtil(){
        httpClient = HttpClients.createDefault();
    }
    public static RestfulUtil getInstance(){
        if(instance == null)
            synchronized (lock){
                if(instance == null){
                    instance = new RestfulUtil();
                }
            }
        return instance;
    }

    public static HttpGet getDefaultHttpGet(String url){
        HttpGet httpGet = new HttpGet(url);
        return httpGet;
    }

    public static boolean forceClose(){
        return sleeptimes > 5;
    }

    private static int sleeptimes = 1;
    public static void sleepLonger(){
        sleeptimes += 1;
    }
    public static void reSetSleepTime(){
        sleeptimes = 1;
    }

    private static int maxSleepTime(){
        return 5000 * sleeptimes;
    }

    /**
     * 2 -5 秒随机，并根据sleeptimes 翻倍
     * @return
     */
    private static long getSleeptime(){
        long sleeptime = (long)(Math.random() *3000) + 2000;
        sleeptime *=sleeptimes;
        return sleeptime;
    }
    public static void sleep(){
        try {
            Thread.sleep(getSleeptime());
            L.d("Sleeping " + String.valueOf(getSleeptime())+"ms");
        } catch (InterruptedException e) {
            L.e(e.getMessage());
        }
        L.d("Weak Up ");
    }

    // 两次抓取之间间隔大于 maxSleepTime，则不用sleep
    private static long last_request_time = 0;
    private static void preSetHttpGet(HttpGet httpGet){
        if(System.currentTimeMillis() - last_request_time < maxSleepTime()){
            sleep();
        }
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(maxSleepTime())
                .setConnectTimeout(maxSleepTime())
                .build();
        httpGet.setConfig(requestConfig);
        last_request_time = System.currentTimeMillis();
    }


    public static HttpEntity requestEntity(HttpGet httpGet){
        preSetHttpGet(httpGet);
        try {
            HttpResponse response = getInstance().httpClient.execute(httpGet);
            if(response !=null){
                int stattusCode = response.getStatusLine().getStatusCode();
                if(stattusCode >=200 && stattusCode<300){
                    return response.getEntity();
                }
            }
        } catch (IOException e) {
            L.e(httpGet.getURI().toString());
            L.e(e.getClass().getSimpleName());
        }
        return null;
    }

    public static String requestHtml(HttpGet httpGet){
        // 设置超时
        preSetHttpGet(httpGet);
        try{
            HttpResponse response = getInstance().httpClient.execute(httpGet);
            if(response !=null){
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode == 200){
                    String responseStr = EntityUtils.toString(response.getEntity());
                    return responseStr;
                }
            }
        }catch (Exception e){
            L.e(httpGet.getURI().toString());
            L.e(e.getClass().getSimpleName());
        }
        return null;
    }

    public static JSONObject requestJson(HttpGet httpGet) {
        // 设置超时
        preSetHttpGet(httpGet);
        try {
            HttpResponse response = getInstance().httpClient.execute(httpGet);
            if(response !=null){
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode == 200){
                    String responStr = EntityUtils.toString(response.getEntity());
                    return JSONObject.parseObject(responStr);
                }
            }
        }catch (Exception e){
            L.e(httpGet.getURI().toString());
            L.e(e.getClass().getSimpleName());
        }

        return null;
    }


    public static String findHtmlByXpath(String xpath, String text) {

        JXDocument jxDocument = JXDocument.create(text);
        List<Object> rs = jxDocument.sel(xpath);
        for (Object o:rs){
//            if (o instanceof Element){
//                int index = ((Element) o).siblingIndex();
//                System.out.println(index);
//            }
            return o.toString();
        }
        return "";
    }

    public static String findTextByXpath(String xpath, String text) throws XPathExpressionException, ParserConfigurationException {
        HtmlCleaner hc = new HtmlCleaner();
        TagNode tn = hc.clean(text);
        Document dom = new DomSerializer(new CleanerProperties()).createDOM(tn);
        XPath xPath = XPathFactory.newInstance().newXPath();

        String result = (String) xPath.evaluate(xpath, dom, XPathConstants.STRING);
        return result;
    }
}
