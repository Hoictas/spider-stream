package cn.bugskiller.spiderstream.handler;

import cn.bugskiller.spiderstream.entity.Chapter;

import java.io.IOException;
import java.util.List;

/**
 * 章节处理器
 *
 * @author Tiakon
 * 2018/4/19 16:24
 */
public interface ChaperHandler {
    /**
     * 根据小说的URl，得到小说的全部章节。
     * @param url 小说的URl
     * @return  List 小说的全部章节
     */
    List<Chapter> getChaptersByURL(String url) throws IOException;
}
