package cn.bugskiller.spiderstream.task;

import cn.bugskiller.spiderstream.entity.Chapter;
import cn.bugskiller.spiderstream.utils.PageUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取顶点小说网（https://www.x23us.com）某部小说的所有章节
 *
 * @author Tiakon
 * 2018/4/19 18:16
 */
public class X23usChapterTask extends ChapterTask {
    @Override
    public List<Chapter> getChaptersByURL(String urlStr) throws IOException {
        String result = PageUtils.crawlerPage(urlStr);
        Document document = Jsoup.parse(result);
        Elements elements = document.select("#at a");
        List<Chapter> chapters = new ArrayList<>();
        for (Element a : elements) {
            Chapter chapter = new Chapter();
            chapter.setTitle(a.text());
            chapter.setUrl(urlStr + a.attr("href"));
            chapters.add(chapter);
        }
        return chapters;
    }
}
