package com.zwp.myappframework.framwork.bean;

import java.util.List;

/**
 * Created by zwp on 2017/5/23.
 * 描述:
 */
public class BuyWays {
    public List<BuyWayList> buyWayList ;

    public String showWhere;

    public String isMust;

    @Override
    public String toString() {
        return "BuyWays{" +
                "buyWayList=" + buyWayList +
                ", showWhere='" + showWhere + '\'' +
                ", isMust='" + isMust + '\'' +
                '}';
    }
}
