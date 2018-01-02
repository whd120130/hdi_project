package com.hdi.utils;


/**
 * 公共枚举类
 * @author wanghuidong
 * @date 2017/12/30.
 * @version 1.0
 */
public enum CommonEnum {
    LEFY_PUTPEOPLE{
        public String toString() {
            return "左安置人";
        }
    },
    RIGTH_PUTPEOPLE{
        public String toString() {
            return "右安置人";
        }
    };
    private CommonEnum() {}

}
