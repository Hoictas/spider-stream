package cn.bugskiller.spiderstream.test;

import cn.bugskiller.spiderstream.entity.Chapter;
import cn.bugskiller.spiderstream.handler.ChaperHandler;
import cn.bugskiller.spiderstream.task.success.BiqugeChapterTask;
import cn.bugskiller.spiderstream.utils.PageUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskTest {


    @Test
    public void testSelected() {
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//            String urlStr = "http://www.biquge.com.tw/18_18550/";
            String urlStr = "https://www.x23us.com/html/57/57570/";
            HttpGet get = new HttpGet(urlStr);
            CloseableHttpResponse httpResponse = httpClient.execute(get);
            String result = EntityUtils.toString(httpResponse.getEntity(), "gbk");
//            System.out.println(result);
            Document document = Jsoup.parse(result);
            //将HTML里标签里的a href 属性的相对地址替换成对应的绝对地址
            document.setBaseUri(urlStr);
//            Elements elements = document.select("#list a");
            Elements elements = document.select("table a");
            List<Chapter> chapters = new ArrayList<>();
            for (Element element : elements) {
                System.out.println(element);
                Chapter chapter = new Chapter();
                chapter.setTitle(element.text());
                element = element.attr("href", element.absUrl("href"));
                System.out.println(element);
                chapter.setUrl(element.attr("href"));
                chapters.add(chapter);
            }
//            System.out.println(chapters.get(10));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testPageUtils() {
        try {
//            String page = PageUtils.crawlerPage("https://www.lbxs.com/dudu/54/54200/");
//            String page = PageUtils.crawlerPage("https://www.x23us.com/html/57/57570/");
            String page = PageUtils.crawlerPage("http://www.biquge.com.tw/18_18550/");
            //起点
//            String page = PageUtils.crawlerPage("https://book.qidian.com/info/1011687743");
            //晋江文学城
//            String page = PageUtils.crawlerPage("http://www.jjwxc.net/onebook.php?novelid=3459704");
//            纵横纵横
//            String page = PageUtils.crawlerPage("http://book.zongheng.com/showchapter/713091.html");
            System.out.println(page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testChapterTask() {
//        ChaperHandler chapterTask = new LbxsChapterTask();
//        ChaperHandler chapterTask = new X23usChapterTask();
//        ChaperHandler chapterTask = new JjwxcChapterTask();
//        ChaperHandler chapterTask = new ZonghengChapterTask();
        ChaperHandler chapterTask = new BiqugeChapterTask();
        try {
//            List<Chapter> chapters = chapterTask.getChaptersByURL("https://www.lbxs.com/dudu/54/54200");
//            List<Chapter> chapters = chapterTask.getChaptersByURL("https://www.x23us.com/html/57/57570/");
            List<Chapter> chapters = chapterTask.getChaptersByURL("http://www.biquge.com.tw/18_18550/");
//            List<Chapter> chapters = chapterTask.getChaptersByURL("http://book.zongheng.com/showchapter/713091.html");
            for (Chapter chapter : chapters) {
                System.out.println(chapter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
