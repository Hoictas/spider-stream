package cn.bugskiller.spiderstream.factory;

import cn.bugskiller.spiderstream.novels.NovelSiteEnum;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成可采集网站的信息工厂
 *
 * @author Tiakon
 * 2018/4/22 20:22
 */
final public class NovelSiteFactory {
    private static final Map<NovelSiteEnum, Map<String, String>> NOVEL_SITES_CONTEXT = new HashMap<>(64);

    static {
        initContext();
    }

    private NovelSiteFactory() {
    }

    private static void initContext() {
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(NovelSiteFactory.class.getClassLoader().getResourceAsStream("novel-sites.xml"));
            Element rootElement = document.getRootElement();
            List<Element> sites = rootElement.elements("site");
            for (Element site : sites) {
                Map<String, String> siteMap = new HashMap<>(16);
                List<Element> elements = site.elements();
                for (Element element : elements) {
                    siteMap.put(element.getName(), element.getTextTrim());
                }
                NOVEL_SITES_CONTEXT.put(NovelSiteEnum.getNovelSiteByUrl(siteMap.get("url")), siteMap);
            }
        } catch (DocumentException e) {
            throw new RuntimeException("NOVEL_SITES_CONTEXT,初始化失败");
        }

    }

    /**
     * 得到网站小说章节的解析规则和网页的编码信息
     *
     * @param novelSiteEnum : 传入一个 NovelSiteEnum 类型
     * @return Map<String , String> : 得到含有网站小说章节的解析规则和网页的编码信息
     */
    public static Map<String, String> getNovelSitesContext(NovelSiteEnum novelSiteEnum) {
        return NOVEL_SITES_CONTEXT.get(novelSiteEnum);
    }
}
