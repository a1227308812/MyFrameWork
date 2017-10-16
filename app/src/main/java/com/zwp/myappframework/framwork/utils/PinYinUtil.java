package com.zwp.myappframework.framwork.utils;//package com.zwp.myappframe.framwork.utils;
//
//
///**
// * Created by zwp on 2017/5/23.
// * 拼音帮助类
// */
//public class PinYinUtil {
//
//
//    /**
//     * 获取第一个字符的首字母
//     *
//     * @param chines
//     * @return
//     */
//    public static String getFirstTag(String chines) {
//        if (StringUtils.isEmpty(chines))
//            return "";
//        chines = chines.substring(0, 1);
//
//        String pinyinName = "";
//        char[] nameChar = chines.toCharArray();
//        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
//        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
//        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
//
//        char name = nameChar[0];
//
//        if (name > 96 && name < 122) {//a-z的字母
//            pinyinName = "" + name;
//        } else if (name > 128) {
//            try {
//                pinyinName += PinyinHelper.toHanyuPinyinStringArray(name, defaultFormat)[0].charAt(0);
//            } catch (Exception e) {
//                pinyinName = "#";
//                e.printStackTrace();
//            }
//        } else {
//            pinyinName = "#";
//        }
//
//        return pinyinName;
//    }
//}