package com.zwp.myappframework.framwork.bean;

import java.util.List;

/**
 * Created by zwp on 2017/5/23.
 * 描述:
 */
public class HJiaBean {
    public String adpTitle;
    public String adpLogo;
    public String shareUrl;
    public String sharePic;
    public String shareCtx;
    public String shareSlogan;
    public String isShare;
    public String cardTotal;
    public String offset;
    public String count;
    public List<Carstyle> carstyleList;

    @Override
    public String toString() {
        return "HJiaBean{" +
                "adpTitle='" + adpTitle + '\'' +
                ", adpLogo='" + adpLogo + '\'' +
                ", shareUrl='" + shareUrl + '\'' +
                ", sharePic='" + sharePic + '\'' +
                ", shareCtx='" + shareCtx + '\'' +
                ", shareSlogan='" + shareSlogan + '\'' +
                ", isShare='" + isShare + '\'' +
                ", cardTotal='" + cardTotal + '\'' +
                ", offset='" + offset + '\'' +
                ", count='" + count + '\'' +
                ", carstyleList=" + carstyleList +
                '}';
    }
}
