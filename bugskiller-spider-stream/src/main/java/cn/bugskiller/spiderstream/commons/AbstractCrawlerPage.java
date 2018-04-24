package cn.bugskiller.spiderstream.commons;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author Tiakon
 * 2018/4/23 12:56
 */
abstract public class AbstractCrawlerPage {

    /**
     * 根据 url 得到字符串格式的HTML页面，页面默认使用 UTF-8 的方式编码。
     *
     * @param urlStr 小说的url地址
     * @return String 字符串类型的网页源代码
     * @throws IOException 没有抓取到页面的异常！
     */
    public String crawlerPage(String urlStr) throws IOException {
        return crawlerPage(urlStr, "UTF-8");
    }

    /**
     * 根据 url 得到字符串格式的HTML页面。
     *
     * @param urlStr  小说的url地址
     * @param charset 网页的编码类型
     * @return String 字符串类型的网页源代码
     * @throws RuntimeException 没有抓取到页面的异常！
     */
    public String crawlerPage(String urlStr, String charset) {

        String result = null;

        //try-with-resources
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
             CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet(urlStr))) {

            //这些资源将按照他们被创建顺序的逆序自动被关闭
            result = EntityUtils.toString(httpResponse.getEntity(), charset);

            if (result == null || "".equals(result)) {
                throw new RuntimeException("没有抓取到页面！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }
}
