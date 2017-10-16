package com.zwp.myappframework.framwork.bean;


import java.util.List;

/**
 * Created by zwp on 2017/5/23.
 */
public class AllCommentBean extends HttpResultBean {
    public String count;

    public String commentTotal;

    public String priceScore;

    public String salerScore;

    public String shopScore;

    public String offset;

    public List<CommentList> commentList ;

    @Override
    public String toString() {
        return "AllCommentBean{" +
                "count='" + count + '\'' +
                ", commentTotal='" + commentTotal + '\'' +
                ", priceScore='" + priceScore + '\'' +
                ", salerScore='" + salerScore + '\'' +
                ", shopScore='" + shopScore + '\'' +
                ", offset='" + offset + '\'' +
                ", commentList=" + commentList +
                '}';
    }

}
