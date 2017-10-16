package com.zwp.myappframework.framwork.bean;

import java.util.List;

/**
 * Created by zwp on 2017/5/23.
 * 描述:
 */
public class Comment {

    public String count;

    public String commentTotal;

    public List<CommentList> commentList ;

    @Override
    public String toString() {
        return "Comment{" +
                "count='" + count + '\'' +
                ", commentTotal='" + commentTotal + '\'' +
                ", commentList=" + commentList +
                '}';
    }

}
