package com.zwp.myappframework.framwork.bean;

import java.util.List;

/**
 * Created by zwp on 2017/5/23.
 * 描述:
 */
public class CommentList {
    public String userName;

    public String commentDate;

    public String score;

    public String content;

    public String memberPic;

    public List<CommentPicList> commentPicList ;

    public String fine;

    @Override
    public String toString() {
        return "CommentList{" +
                "userName='" + userName + '\'' +
                ", commentDate='" + commentDate + '\'' +
                ", score='" + score + '\'' +
                ", content='" + content + '\'' +
                ", memberPic='" + memberPic + '\'' +
                ", commentPicList=" + commentPicList +
                ", fine='" + fine + '\'' +
                '}';
    }
}
