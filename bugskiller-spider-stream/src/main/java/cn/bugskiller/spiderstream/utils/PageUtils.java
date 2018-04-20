package cn.bugskiller.spiderstream.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author Tiakon
 * 2018/4/19 17:25
 */
public class PageUtils {

    /**
     * 根据 url 得到字符串格式的HTML页面。
     */
    public static String crawlerPage(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse httpResponse = httpClient.execute(get);
        /**
         *TODO By Tiakon
         *  自动识别网页编码  
         **/
        String result = EntityUtils.toString(httpResponse.getEntity(),"gbk");
        if (result == null || "".equals(result)) {
            throw new RuntimeException(" crawlerPage Method result is null !!");
        }
        httpClient.close();
        return result;
    }
}
