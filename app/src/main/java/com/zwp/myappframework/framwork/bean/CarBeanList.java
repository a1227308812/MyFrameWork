package com.zwp.myappframework.framwork.bean;

import java.util.List;

/**
 * Created by zwp on 2017/5/23.
 * 描述:
 */
public class CarBeanList {

    public String brandId;

    public String brandName;

    public String brandPic;

    public List<StyleList> styleList ;

    @Override
    public String toString() {
        return "CarList{" +
                "brandId='" + brandId + '\'' +
                ", brandName='" + brandName + '\'' +
                ", brandPic='" + brandPic + '\'' +
                ", styleList=" + styleList +
                '}';
    }
}

