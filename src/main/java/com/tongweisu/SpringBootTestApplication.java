package com.tongweisu
        ;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

//@ImportResource(locations= {"classpath:beans.xml"})//导入指定的配置文件
@MapperScan(value = "com.tongweisu.dao")
@SpringBootApplication
//@ComponentScan(basePackages={"com.tongfu"})
public class SpringBootTestApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SpringBootTestApplication.class, args);
        System.err.println("tongweisu——启动完毕");
    }

}
