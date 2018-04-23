package cn.bugskiller.spiderstream.test;

import cn.bugskiller.spiderstream.entity.Chapter;
import cn.bugskiller.spiderstream.entity.ChapterContent;
import cn.bugskiller.spiderstream.factory.NovelSiteFactory;
import cn.bugskiller.spiderstream.handler.ChapterHandler;
import cn.bugskiller.spiderstream.novels.NovelSiteEnum;
import cn.bugskiller.spiderstream.task.DefultChapterContentTask;
import cn.bugskiller.spiderstream.task.DefultChapterTask;
import cn.bugskiller.spiderstream.utils.NovelSiteUtils;
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
import java.util.Map;
import java.util.Set;

public class JUnitTest {


    @Test
    public void testSelected() {
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//            String urlStr = "http://www.biquge.com.tw/18_18550/";
            String urlStr = "https://www.x23us.com/html/57/57570/";
            HttpGet get = new HttpGet(urlStr);
            CloseableHttpResponse httpResponse = httpClient.execute(get);
            String resultPage = EntityUtils.toString(httpResponse.getEntity(), "gbk");
//            System.out.println(resultPage);
            Document document = Jsoup.parse(resultPage);
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
    public void testDefultChapterContentTask() {
        DefultChapterContentTask defultChapterContentTask = null;
        try {
            defultChapterContentTask = new DefultChapterContentTask();
            defultChapterContentTask.getChapterContentByURL("https://www.bixia.org/185_185433/9600937.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNovelSiteUtils() {
        try {
//            String page = NovelSiteUtils.crawlerPage("https://www.lbxs.com/dudu/54/54200/");
//            String page = NovelSiteUtils.crawlerPage("https://www.x23us.com/html/57/57570/");
            String page = NovelSiteUtils.crawlerPage("http://www.biquge.com.tw/18_18550/");
            //起点
//            String page = NovelSiteUtils.crawlerPage("https://book.qidian.com/info/1011687743");
            //晋江文学城
//            String page = NovelSiteUtils.crawlerPage("http://www.jjwxc.net/onebook.php?novelid=3459704");
//            纵横纵横
//            String page = NovelSiteUtils.crawlerPage("http://book.zongheng.com/showchapter/713091.html");
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
        ChapterHandler chapterTask = new DefultChapterTask();
        try {
//            List<Chapter> chapters = chapterTask.getChaptersByURL("https://www.lbxs.com/dudu/54/54200");
            List<Chapter> chapters = chapterTask.getChaptersByURL("https://www.bixia.org/185_185433/");
//            List<Chapter> chapters = chapterTask.getChaptersByURL("https://www.x23us.com/html/57/57570/");
//            List<Chapter> chapters = chapterTask.getChaptersByURL("http://www.biquge.com.tw/18_18550/");
//            List<Chapter> chapters = chapterTask.getChaptersByURL("http://book.zongheng.com/showchapter/713091.html");
            for (Chapter chapter : chapters) {
                System.out.println(chapter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNovelSiteFactory() {

        Map<NovelSiteEnum, Map<String, String>> novelSitesMap = NovelSiteFactory.NOVEL_SITES_CONTEXT;
//        Set<Map.Entry<NovelSiteEnum, Map<String, String>>> entries = novelSitesMap.entrySet();

//        for (Map.Entry<NovelSiteEnum, Map<String, String>> entry : entries) {
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
//        }
    }

    @Test
    public void testNovelSitesContext() {
        Map<String, String> novelSitesContext = NovelSiteFactory.getNovelSitesContext(NovelSiteEnum.getNovelSiteByUrl("https://www.bixia.org/185_185433/"));
        Set<Map.Entry<String, String>> entries = novelSitesContext.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    @Test
    public void testCssQuery() {
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//            String urlStr = "http://www.biquge.com.tw/18_18550/";
            String urlStr = "https://www.x23us.com/html/57/57570/";
            HttpGet get = new HttpGet(urlStr);
            CloseableHttpResponse httpResponse = httpClient.execute(get);
            String result = EntityUtils.toString(httpResponse.getEntity(), "gbk");
            Document document = Jsoup.parse(result);
            document.setBaseUri(urlStr);
//            Elements elements = document.select("#list a");
            Elements elements = document.select("table a");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testChapterContent() {
        ChapterContent chapterContent = new ChapterContent();
        chapterContent.setContent("无数道目光落在叶伏天的身上，看着那璀璨无比的命魂，金翅大鹏鸟。");
        System.out.println(chapterContent);
    }

}
