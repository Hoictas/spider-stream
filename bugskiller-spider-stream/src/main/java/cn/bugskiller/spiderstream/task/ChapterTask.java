package cn.bugskiller.spiderstream.task;

import cn.bugskiller.spiderstream.entity.Chapter;
import cn.bugskiller.spiderstream.handler.ChaperHandler;
import cn.bugskiller.spiderstream.utils.PageUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tiakon
 * 2018/4/19 16:38
 */
public class ChapterTask implements ChaperHandler {
    @Override
    public List<Chapter> getChaptersByURL(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        String host = url.getHost();
        String result = PageUtils.crawlerPage(urlStr);
        Document document = Jsoup.parse(result);
        Elements elements = document.select("#list a");
        List<Chapter> chapters = new ArrayList<>();
        for (Element element : elements) {
            Chapter chapter = new Chapter();
            chapter.setTitle(element.text());
            chapter.setUrl("https://" + host + element.attr("href"));
            chapters.add(chapter);
        }
        return chapters;
    }
}
