package cn.bugskiller.spiderstream.entity;

import java.io.Serializable;

/**
 * 小说章节对应的JavaBean
 *
 * @author Tiakon
 * 2018/4/19 16:18
 */
public class Chapter implements Serializable {
    private String title;
    private String url;

    public Chapter() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
