package cn.bugskiller.spiderstream.task;

import cn.bugskiller.spiderstream.commons.AbstractCrawlerPage;
import cn.bugskiller.spiderstream.entity.Chapter;
import cn.bugskiller.spiderstream.factory.NovelSiteFactory;
import cn.bugskiller.spiderstream.handler.ChapterHandler;
import cn.bugskiller.spiderstream.novels.NovelSiteEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 解析网站小说的目录
 *
 * @author Tiakon
 * 2018/4/19 16:38
 */
 public class AbstractChapterTask extends AbstractCrawlerPage implements ChapterHandler {

    /**
     * 根据小说的 url 解析小说章节
     *
     * @param urlStr 小说的url地址
     * @return List 小说的全部章节
     */
    @Override
    public List<Chapter> getChaptersByURL(String urlStr) throws IOException {

        //  获取网站信息
        Map<String, String> novelSitesContext = NovelSiteFactory.getNovelSitesContext(NovelSiteEnum.getNovelSiteByUrl(urlStr));

        //  得到小说章节解析器
        String cssQuery = novelSitesContext.get("chatpers-selector");

        //  得到网页编码规则
        String charset = novelSitesContext.get("charset");

        String resultPage = super.crawlerPage(urlStr, charset);
        Document document = Jsoup.parse(resultPage);
        document.setBaseUri(urlStr);
        Elements elements = document.select(cssQuery);
        List<Chapter> chapters = new ArrayList<>();
        for (Element element : elements) {
            Chapter chapter = new Chapter();
            chapter.setTitle(element.text());
            element = element.attr("href", element.absUrl("href"));
            chapter.setUrl(element.attr("href"));
            chapters.add(chapter);
        }
        return chapters;
    }
}
