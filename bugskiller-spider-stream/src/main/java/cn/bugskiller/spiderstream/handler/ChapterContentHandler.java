package cn.bugskiller.spiderstream.handler;

import cn.bugskiller.spiderstream.entity.ChapterContent;

import java.io.IOException;

/**
 * @author Tiakon
 * 2018/4/23 12:57
 */
public interface ChapterContentHandler {
    /**
     * 根据小说章节的URl，得到小说章节的全部信息。
     *
     * @param url 小说章节的URl
     * @return List 小说的全部信息
     * @throws IOException io异常
     */
    ChapterContent getChapterContentByURL(String url) throws IOException;
}
