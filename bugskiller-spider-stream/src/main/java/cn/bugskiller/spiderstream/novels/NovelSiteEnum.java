package cn.bugskiller.spiderstream.novels;

/**
 * 添加已被支持的小说网站
 *
 * @author Tiakon
 * 2018/4/22 19:03
 */
public enum NovelSiteEnum {

    LaBi(1, "https://www.lbxs.com"),
    DingDian(2, "https://www.x23us.com"),
    BiQqGe(3, "http://www.biquge.com.tw"),
    BiXia(4, "https://www.bixia.org");

    private int id;
    private String url;

    NovelSiteEnum(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static NovelSiteEnum getNovelSiteById(int id) {
        switch (id) {
            case 1:
                return LaBi;
            case 2:
                return DingDian;
            case 3:
                return BiQqGe;
            case 4:
                return BiXia;
            default:
                throw new RuntimeException("id = " + id + " 不是被支持的小说网站！！！");
        }
    }

    public static NovelSiteEnum getNovelSiteByUrl(String url) {
        //        values()方法的作用就是获取枚举类中的所有变量
        for (NovelSiteEnum novelSiteEnum : values()) {
            if (url.contains(novelSiteEnum.getUrl())) {
                return novelSiteEnum;
            }
        }
        throw new RuntimeException("url = " + url + " 不是被支持的小说网站！！！");
    }

}


