package cn.bugskiller.spiderstream.entity;

import org.apache.commons.lang3.StringUtils;

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
    private String chapterListURl;
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

    public String getChapterListURl() {
        return chapterListURl;
    }

    public void setChapterListURl(String chapterListURl) {
        this.chapterListURl = chapterListURl;
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
                ", content='" + StringUtils.abbreviate(content, 30) + '\'' +
                ", chapterListURl='" + chapterListURl + '\'' +
                ", prev='" + prev + '\'' +
                ", next='" + next + '\'' +
                '}';
    }
}
