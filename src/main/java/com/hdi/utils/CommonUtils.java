package com.hdi.utils;

import java.util.Random;

/**
 * Created by wanghuidong on 2017/12/30.
 */
public class CommonUtils {
    /**
     * 随机生成字母加数字的组合
     * @param length
     * @return
     */
    public static String getInviteCode(Integer length) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();//随机用以下三个随机生成器
        Random randdata = new Random();
        int data = 0;
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(2);
            //目的是随机选择生成数字，大小写字母
            switch (index) {
                case 0:
                    data = randdata.nextInt(10);//仅仅会生成0~9
                    sb.append(data);
                    break;
                case 1:
                    data = randdata.nextInt(26) + 65;//保证只会产生65~90之间的整数
                    sb.append((char) data);
                    break;
            }
        }
        return sb.toString();
    }
}
