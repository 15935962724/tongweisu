package com.tongfu.util;

import com.alibaba.druid.sql.visitor.functions.Substring;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;

public class MysqlUtils {



    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://106.13.123.1:3306/tongfu";

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "Test@123";



    public static void main(String[] args) {


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String driverClass = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://106.13.123.1:3306/tongfu";
            String user = "root";
            String pass= "Test@123";

            Class.forName(driverClass);
            connection = DriverManager.getConnection(url, user, pass);



            File xlsFile = new File("E:/come.xlsx");
            /**
             * 这里根据不同的excel类型
             * 可以选取不同的处理类：
             *          1.XSSFWorkbook
             *          2.HSSFWorkbook
             */
            // 获得工作簿
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(xlsFile));

            // 获得工作表
            XSSFSheet sheet = workbook.getSheetAt(0);

            int rows = sheet.getPhysicalNumberOfRows();
            System.err.println(rows);
            for (int i = 0; i < rows; i++) {
                // 获取第i行数据
                XSSFRow sheetRow = sheet.getRow(i);
                // 获取第0格数据
                String  content = "";
                for (int j = 0;j<4;j++){
                    XSSFCell cell = sheetRow.getCell(j);
                    content += ","+cell;
                    // 调用toString方法获取内容
//                    System.out.println((i+1)+"+"+cell);
                }
                String []values = content.split(",");
                String sql = "INSERT INTO `tf_hospital` ( `name`,`create_date`, `hospital_rank_id`, `hospital_category_id`, `area_name`) VALUES('"+values[1]+"','2015-11-10 00:03:52',"+values[2]+","+values[3]+",'"+values[4]+"');";
                System.out.println(sql);

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(preparedStatement != null){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }




        Connection conn = null;
        PreparedStatement pst = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
//            stmt = conn.createStatement();




//
//            // 展开结果集数据库
//            while(rs.next()){
//                // 通过字段检索
//                int id  = rs.getInt("id");
//                String name = rs.getString("name");
//                String url = rs.getString("url");
//
//                // 输出数据
//                System.out.print("ID: " + id);
//                System.out.print(", 站点名称: " + name);
//                System.out.print(", 站点 URL: " + url);
//                System.out.print("\n");
//            }
            // 完成后关闭
//            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }


}
