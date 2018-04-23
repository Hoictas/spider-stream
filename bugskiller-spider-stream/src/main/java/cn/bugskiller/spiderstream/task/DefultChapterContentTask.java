package cn.bugskiller.spiderstream.task;

import cn.bugskiller.spiderstream.entity.ChapterContent;

import java.io.IOException;

/**
 *
 * @author Tiakon
 * 2018/4/23 13:12
 */
public class DefultChapterContentTask extends AbstractChapterContentTask {
    @Override
    public ChapterContent getChapterContentByURL(String urlStr) throws IOException {
        return super.getChapterContentByURL(urlStr);
    }
}
