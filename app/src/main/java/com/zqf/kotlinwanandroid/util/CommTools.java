package com.zqf.kotlinwanandroid.util;

import java.util.Random;

/**
 * Author: zqf
 * Date: 2022/09/20
 */
public class CommTools {

    public static String colorValue() {
        //红色
        String red;
        //绿色
        String green;
        //蓝色
        String blue;
        //生成随机对象
        Random random = new Random();
        //生成红色颜色代码
        red = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //生成绿色颜色代码
        green = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //生成蓝色颜色代码
        blue = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //判断红色代码的位数
        red = red.length() == 1 ? "0" + red : red;
        //判断绿色代码的位数
        green = green.length() == 1 ? "0" + green : green;
        //判断蓝色代码的位数
        blue = blue.length() == 1 ? "0" + blue : blue;
        //生成十六进制颜色值
        return "#" + red + green + blue;
    }

    /**
     * 本地设置的颜色值
     */
    public static String colorValueNative() {
        String[] colorsArray = {"#1FBC63", "#511BBB", "#32D9D6", "#C55AC6", "#C2B15D",
                "#1FA60B", "#7FC962", "#F99A17", "#86D1A4",
                "#8B521F", "#257204", "#9CC6BB",
                "#96135B", "#EA7AC3"};
        //生成随机对象
        Random random = new Random();
        //生成colorsArray数组大小内的随机数
        int i = random.nextInt(colorsArray.length);
        return colorsArray[i];
    }
}
