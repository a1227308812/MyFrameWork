package com.zwp.myappframework.framwork.utils;

/**
 * Created by zwp on 2017/5/23.
 * Description:
 */
public class UrlUtils {

    public static final String BASE_URL = "http://123.56.145.151:8080/TuanCheNetWork/";
    public static final String Getcitys = BASE_URL + "bwf_TuanChe_Getcitys";//选择城市列表
    public static final String QueryCityByLatitude = BASE_URL + "bwf_TuanChe_QueryCityByLatitude";//获取当前城市
    public static final String VersionUpadteServlet = BASE_URL + "bwf_TuanChe_VersionUpadteServlet";//获取更新数据


    public static final String BUY_CAR = BASE_URL + "bwf_TuanChe_HomeServlet";
    public static final String HOT_BRAND = BASE_URL + "bwf_TuanChe_TopBrand";
    public static final String HOT_TYPE = BASE_URL + "bwf_TuanChe_Hotstyle";
    public static final String BANNER = BASE_URL + "bwf_TuanChe_BannerServlet";


    //汽车列表
    public static final String CAR_BUY_LIST = BASE_URL + "bwf_TuanChe_BrandCarStyleServlet";
    //汽车详情
    public static final String CAR_BUY_DETAIL = BASE_URL + "bwf_TuanChe_BuyInfoServlet";
    //汽车详情—购车流程
    public static final String CAR_BUY_NOGROUP = BASE_URL + "bwf_TuanChe_BuyInfoNogroupServlet";
    //汽车详情—常见问题
    public static final String CAR_BUY_QUESTION = BASE_URL + "bwf_TuanChe_BuyInfoQuestionServlet";
    //汽车详情—全部评价
    public static final String CAR_BUY_EVALUATE = BASE_URL + "bwf_TuanChe_BuyInfoEvaluateServlet";
    //汽车详情—全部评价
    public static final String CAR_BUY_HJia = BASE_URL + "bwf_TuanChe_AdplistServlet";

}
