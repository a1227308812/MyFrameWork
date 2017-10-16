package com.zwp.myappframework;

import com.zwp.myappframework.framwork.db.model.BusInfoModel;

/**
 * Created by zwp on 2017/5/23.
 */

public class Config {
    //API请求地址（没有control）
//    String API_URL = "https://api.github.com/";
//    //API请求地址
//    String API_BASE_URL = API_URL + "users/";

    public static String API_URL = "http://192.168.1.55:803";//周志强
//    String API_URL = "http://125.71.211.128:82";//外网
//    //API请求地址
//    String API_BASE_URL = API_URL + "/mobileOrg/";
    public static String API_BASE_URL = "http://v.juhe.cn/weather/";


    //SharePreferences默认文件名
    public static String DEFAULT_SP_FILE_NAME = "default_share";

    //用户信息序列化文件
    public static final String USER_CONTEXT_SERIALIZE_FILE_NAME = "user_context";

    //文件存储目录
    public static final String FILE_RECV = "westar/file_recv";

    //更改的ip信息文件
    public static final String CUSTOM_IP_CONFIG = "custom_ip_config";

    public static final String DB_NAME = "tuanche_db";//数据库名称

    public static int DB_VERSION = 1;//数据库版本

    //新增一张车辆信息表
    public static String BUS_TABLE_NAME = "bus_table";

    //数据库所有的表
    public static String[] TABLES = new String[]{BusInfoModel.class.getName()};//DBhelper中可以根据类名那到类的对象

    //bugly的AppId
    public static final String APPID = "c9e8f4bc6f";

    public static void initUrl(String url){
        API_URL = url;
//        API_BASE_URL = API_URL + "/mobileOrg/";
    }


}
