package cn.bugskiller.spiderstream.task;

import cn.bugskiller.spiderstream.entity.Chapter;
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
 * 获取蜡笔小说网（https://www.lbxs.com）某部小说的所有章节
 *
 * @author Tiakon
 * 2018/4/19 18:16
 */
public class LbxsChapterTask extends ChapterTask {

    @Override
    public List<Chapter> getChaptersByURL(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        String host = url.getHost();
        String result = PageUtils.crawlerPage(urlStr);
        Document document = Jsoup.parse(result);
        //        获取a标签
        Elements elements = document.select("#list a");
        List<Chapter> chapters = new ArrayList<>();
        for (Element a : elements) {
            Chapter chapter = new Chapter();
            chapter.setTitle(a.text());
            chapter.setUrl("https://" + host + a.attr("href"));
            chapters.add(chapter);
        }
        return chapters;
    }
}
