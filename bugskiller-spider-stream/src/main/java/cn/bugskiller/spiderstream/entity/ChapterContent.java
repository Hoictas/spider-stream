package cn.bugskiller.spiderstream.entity;

import java.io.Serializable;

/**
 * 章节内容对应的JavaBean
 *
 * @author Tiakon
 * 2018/4/23 12:25
 */
public class ChapterContent implements Serializable {

    private String title;
    private String content;
    private String chaptersURL;
    private String prev;
    private String next;

    public ChapterContent() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChaptersURL() {
        return chaptersURL;
    }

    public void setChaptersURL(String chaptersURL) {
        this.chaptersURL = chaptersURL;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "ChapterContent{" +
                "title='" + title + '\'' +
//                ", content='" + StringUtils.abbreviate(content, 30) + '\'' +
                ", content='" + content + '\'' +
                ", chaptersURL='" + chaptersURL + '\'' +
                ", prev='" + prev + '\'' +
                ", next='" + next + '\'' +
                '}';
    }
}
