package cn.bugskiller.spiderstream.test;

import cn.bugskiller.spiderstream.entity.Chapter;
import cn.bugskiller.spiderstream.handler.ChaperHandler;
import cn.bugskiller.spiderstream.task.JjwxcChapterTask;
import cn.bugskiller.spiderstream.task.LbxsChapterTask;
import cn.bugskiller.spiderstream.task.X23usChapterTask;
import cn.bugskiller.spiderstream.task.ZonghengChapterTask;
import cn.bugskiller.spiderstream.utils.PageUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TaskTest {


    @Test
    public void testPageUtils() {
        try {
//            String page = PageUtils.crawlerPage("https://www.lbxs.com/dudu/54/54200/");
//            String page = PageUtils.crawlerPage("https://www.x23us.com/html/57/57570/");
//            String page = PageUtils.crawlerPage("http://www.biquge.com.tw/18_18550/");
            //起点
//            String page = PageUtils.crawlerPage("https://book.qidian.com/info/1011687743");
            //晋江文学城
//            String page = PageUtils.crawlerPage("http://www.jjwxc.net/onebook.php?novelid=3459704");
//            纵横
            String page = PageUtils.crawlerPage("http://book.zongheng.com/showchapter/713091.html");
            System.out.println(page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testChapterTask() {
//        ChaperHandler chapterTask = new LbxsChapterTask();
//        ChaperHandler chapterTask = new X23usChapterTask();
//        ChaperHandler chapterTask = new JjwxcChapterTask();
        ChaperHandler chapterTask = new ZonghengChapterTask();
        try {
//            List<Chapter> chapters = chapterTask.getChaptersByURL("https://www.lbxs.com/dudu/54/54200");
//            List<Chapter> chapters = chapterTask.getChaptersByURL("https://www.x23us.com/html/57/57570/");
//            List<Chapter> chapters = chapterTask.getChaptersByURL("http://www.biquge.com.tw/18_18550/");

            List<Chapter> chapters = chapterTask.getChaptersByURL("http://book.zongheng.com/showchapter/713091.html");
            for (Chapter chapter : chapters) {
                System.out.println(chapter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
