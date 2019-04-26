package bean;

import kit.Status;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName Error
 * @Description TODO
 * @createdate 2019/4/6 星期六 00:02
 */
public class WechatError {
    private Integer errcode;
    private String errmsg;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return null!=errcode?Status.is(errcode):this.errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "errcode:"+this.errcode+",errmsg:"+this.getErrmsg();
    }
}
