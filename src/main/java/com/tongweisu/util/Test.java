package com.tongfu.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.text.NumberFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) {

//        String hashAlgorithmName = "MD5";//加密方式
//        String crdentials = "admin";//密码原值
//        String salt = "admin";//盐值
//        int hashIterations = 1024;//加密1024次
//        String result = new SimpleHash(hashAlgorithmName,crdentials, ByteSource.Util.bytes(salt),hashIterations).toBase64();
//        System.out.println(">>>>>"+result);

        String str=null;
        //$使用
        str=String.format("格式参数$的使用：%1$d,%2$s", 99,"abc");
        System.out.println(str);
        //+使用
        System.out.printf("显示正负数的符号：%+d与%d%n", 99,-99);
        //补O使用
        str=String.format("%04d%n", 10);
        System.out.println("-"+str.trim()+"-");
        System.out.printf("最牛的编号是：%03d%n", 7);
        //空格使用
        System.out.printf("Tab键的效果是：% 8d%n", 7);
        //.使用
        System.out.printf("整数分组的效果是：%,d%n", 9989997);
        //空格和小数点后面个数
        System.out.printf("一本书的价格是：% 50.5f元%n", 49.8);

         int num1 = 5;

         int num2 = 9;

         // 创建一个数值格式化对象

         NumberFormat numberFormat = NumberFormat.getInstance();

         // 设置精确到小数点后2位

         numberFormat.setMaximumFractionDigits(2);

        System.out.println((float) (num1-num2) / (float) num2*100);


         String result = numberFormat.format((float) (num1-num2) / (float) num2 * 100);


        System.out.println(Float.valueOf(result));
         System.out.println("num1和num2的百分比为:" + result + "%");

         for (int i = 0;i<50;i++){
             System.out.println(">>>"+new Date().getTime());
         }


    }







}
