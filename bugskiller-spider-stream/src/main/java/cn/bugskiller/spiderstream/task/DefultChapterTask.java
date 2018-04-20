package cn.bugskiller.spiderstream.task;

import cn.bugskiller.spiderstream.entity.Chapter;

import java.io.IOException;
import java.util.List;

/**
 * 默认获取小说的所有章节
 *
 * @author Tiakon
 * 2018/4/19 18:16
 */
public class DefultChapterTask extends ChapterTask {
    @Override
    public List<Chapter> getChaptersByURL(String urlStr) throws IOException {
        return super.getChaptersByURL(urlStr);
    }
}
