package cn.bugskiller.spiderstream.task;

import cn.bugskiller.spiderstream.commons.AbstractCrawlerPage;
import cn.bugskiller.spiderstream.entity.ChapterContent;
import cn.bugskiller.spiderstream.factory.NovelSiteFactory;
import cn.bugskiller.spiderstream.handler.ChapterContentHandler;
import cn.bugskiller.spiderstream.novels.NovelSiteEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Map;

/**
 * @author Tiakon
 * 2018/4/23 13:02
 */
public class AbstractChapterContentTask extends AbstractCrawlerPage implements ChapterContentHandler {
    @Override
    public ChapterContent getChapterContentByURL(String urlStr) throws IOException {

        //  获取网站配置规则
        Map<String, String> novelSitesContext = NovelSiteFactory.getNovelSitesContext(NovelSiteEnum.getNovelSiteByUrl(urlStr));

        //网站页面的编码
        String charset = novelSitesContext.get("charset");

        //章节标题
        String chatperNameSelector = novelSitesContext.get("chatper-name-selector");

        //章节内容
        String chatperContentSelector = novelSitesContext.get("chatper-content-selector");

        //上一章
        String chatperButtonPrevSelector = novelSitesContext.get("chatper-button-prev-selector");

        //章节列表
        String chatperButtonChatpersSelector = novelSitesContext.get("chatper-button-chatpers-selector");

        //下一章
        String chatperButtonNextSelector = novelSitesContext.get("chatper-button-next-selector");

        //抓取小说内容所在的页面
        String resultPage = super.crawlerPage(urlStr, charset);
        Document document = Jsoup.parse(resultPage);
        document.setBaseUri(urlStr);

        Elements titleElements = document.select(chatperNameSelector);

        Elements contentElements = document.select(chatperContentSelector);

        ChapterContent chapterContent = new ChapterContent();

        Element prevElement;
        Element chatpersURLElement;
        Element nextElement;
        /**
         *TODO By Tiakon
         *  根据css选择器的不同来解析
         **/
        if (!chatperButtonPrevSelector.contains(",")) {

            Elements prevElements = document.select(chatperButtonPrevSelector);
            Elements chatpersURLElements = document.select(chatperButtonChatpersSelector);
            Elements nextElements = document.select(chatperButtonNextSelector);

            chatpersURLElement = chatpersURLElements.get(0);
            chatpersURLElement = chatpersURLElement.attr("href", chatpersURLElement.absUrl("href"));

            prevElement = prevElements.get(0);
            prevElement = prevElement.attr("href", prevElement.absUrl("href"));

            nextElement = nextElements.get(0);
            nextElement = nextElement.attr("href", nextElement.absUrl("href"));

        } else {
            String[] chatperButtons = chatperButtonPrevSelector.split("[,]");
            Elements elements = document.select(chatperButtons[0]);
            int indexsPrev = Integer.parseInt(chatperButtons[1]);
            int indexsChatpers = Integer.parseInt(chatperButtonChatpersSelector.split("[,]")[1]);
            int indexsNext = Integer.parseInt(chatperButtonNextSelector.split("[,]")[1]);

            prevElement = elements.get(indexsPrev);
            prevElement = prevElement.attr("href", prevElement.absUrl("href"));

            chatpersURLElement = elements.get(indexsChatpers);
            chatpersURLElement = chatpersURLElement.attr("href", chatpersURLElement.absUrl("href"));

            nextElement = elements.get(indexsNext);
            nextElement = nextElement.attr("href", nextElement.absUrl("href"));
        }

        chapterContent.setTitle(titleElements.first().text());
        chapterContent.setContent(contentElements.first().html());
        chapterContent.setChaptersURL(chatpersURLElement.attr("href"));
        chapterContent.setPrev(prevElement.attr("href"));
        chapterContent.setNext(nextElement.attr("href"));

        return chapterContent;
    }
}
