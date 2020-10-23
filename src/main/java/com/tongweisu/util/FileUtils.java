package com.tongfu.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 上传文件工具类
 */
public class FileUtils {

    /**
     *
     * @param file 文件
     * @param path 文件存放路径
     * @param fileName 源文件名
     * @return
     */
    public static boolean upload(MultipartFile file, String path, String fileName) {

        // 生成新的文件名
        //String realPath = path + "/" + FileNameUtils.getFileName(fileName);

        File files = new File(path);
        if (!files.isDirectory()) {
            files.mkdirs();
        }

        //使用原文件名
        String realPath = path + "/" + fileName;

        File dest = new File(realPath);

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            //保存文件
            file.transferTo(dest);
            return true;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
        /**
         * 上传文件
         */
        public static String upload (MultipartFile file, String path){

            if (file.getSize()<=0){
                return null;
            }

            // 生成新的文件名
            //String realPath = path + "/" + FileNameUtils.getFileName(fileName);

            //		获取静态资源路径
            String staticPath= ClassUtils.getDefaultClassLoader().getResource("static").getPath()+path;
            File files = new File(staticPath);
            if (!files.isDirectory()) {
                files.mkdirs();
            }

            // 获取文件名
            String fileName = file.getOriginalFilename();
            System.out.println("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            System.out.println("上传的后缀名为：" + suffixName);
            fileName= Long.toString(System.currentTimeMillis())+suffixName;//重新命名图片，变成随机的名字
            //使用原文件名
            String realPath = staticPath  + fileName;

            File dest = new File(realPath);

            //判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdir();
            }
            try {
                //保存文件
                file.transferTo(dest);
                return path+fileName;
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }

        }






}
