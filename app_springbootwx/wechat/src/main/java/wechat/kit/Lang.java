package wechat.kit;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName Lang
 * @Description TODO 语言支持
 * @createdate 2019/4/6 星期六 03:37
 */
public enum Lang {
    ZH_CN("zh_CN",1),//中文
    ZH_TW("zh_TW",2),//繁体
    EN("en",3);//英文
    private String name;
    private int index;

    Lang(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
