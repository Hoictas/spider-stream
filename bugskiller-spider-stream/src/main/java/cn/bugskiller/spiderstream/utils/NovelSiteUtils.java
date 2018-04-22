package cn.bugskiller.spiderstream.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 工具类
 *
 * @author Tiakon
 * 2018/4/19 17:25
 */
final public class NovelSiteUtils {

    /**
     * 根据 url 得到字符串格式的HTML页面，页面默认使用 UTF-8 的方式编码。
     *
     * @param urlStr 小说的url地址
     * @return String 字符串类型的网页源代码
     * @throws IOException 没有抓取到页面的异常！
     */
    public static String crawlerPage(String urlStr) throws IOException {
        return NovelSiteUtils.crawlerPage(urlStr, "UTF-8");
    }

    /**
     * 根据 url 得到字符串格式的HTML页面。
     *
     * @param urlStr  小说的url地址
     * @param charset 网页的编码类型
     * @return String 字符串类型的网页源代码
     * @throws RuntimeException 没有抓取到页面的异常！
     */
    public static String crawlerPage(String urlStr, String charset) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(urlStr);
        CloseableHttpResponse httpResponse = httpClient.execute(get);
        String result = EntityUtils.toString(httpResponse.getEntity(), charset);
        if (result == null || "".equals(result)) {
            throw new RuntimeException(" 没有抓取到页面！");
        }
        httpClient.close();
        return result;
    }
}
