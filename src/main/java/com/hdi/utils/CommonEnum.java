package com.hdi.utils;

import java.util.EnumSet;

/**
 * 公共枚举类
 * @author 王慧东
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
