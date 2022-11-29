package com.tongweisu.controller.admin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller("webfile")
//@RequestMapping("/web/file")
public class FileController {

    Logger logger = LoggerFactory.getLogger(getClass());




    @RequestMapping("/config")
    public String config(String action ,HttpServletRequest request, HttpServletResponse response){


        String json = "";
        response.setContentType("application/json");
        // 获取项目在磁盘的绝对路径
//        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
//        try {
//            // 将josn文件读到Stirng
//            json =  IOUtils.toString(new FileInputStream(new File(path + "/static/ueditor/jsp/config.json")), Charsets.UTF_8.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return  "redirect:/ueditor/jsp/config.json";


//
        if(action.equals("config")) {
            return "redirect:/admin/lib/ueditor/jsp/config.json";
        }else if(action.equals("uploadimage")){
            return "forward:/uploadimage";
        }
        return "";

    }




    @RequestMapping("/uploadimage")
    @ResponseBody
    public Object demo(@RequestParam("upfile") MultipartFile upfile,HttpServletRequest request, HttpServletResponse response){
        String rootPath = "/file/";

        String staticPath= ClassUtils.getDefaultClassLoader().getResource("static").getPath()+rootPath;
        File files = new File(staticPath);
        if (!files.isDirectory()) {
            files.mkdirs();
        }

        // 获取文件名
        String fileName = upfile.getOriginalFilename();
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
            upfile.transferTo(dest);

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("original",fileName);
            map.put("name",fileName);
//            map.put("url",CommonConstnts.IMAGES_PATH+path+"//"+uuid1+hz);
            String url =  request.getRequestURI().replaceAll(request.getServletPath(),"")+rootPath+fileName;
            map.put("url",rootPath+fileName);
            map.put("size",upfile.getSize());
            map.put("type",suffixName);
            map.put("state","SUCCESS");
            return map;

//            return path+fileName;
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
