package com.framework.jdbc;

import java.sql.*;

/**
 * Created by jiamingku on 2018/10/30.
 */
public class Jdbc1 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 本地开发数据地址：     192.168.20.87     uNmae: root       uPass: abc123
        String URL="jdbc:mysql://192.168.20.87:3306/test";
        String USER="root";
        String PASSWORD="abc123";
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2.获得数据库链接
        Connection conn= DriverManager.getConnection(URL, USER, PASSWORD);
        //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
        Statement st=conn.createStatement();
        st.setQueryTimeout(11);

        System.out.println("conn.getAutoCommit() = " + conn.getAutoCommit());
        conn.setAutoCommit(false);

//        ResultSet rs=st.executeQuery("select * from user");
//        //4.处理数据库的返回结果(使用ResultSet类)
//        while(rs.next()){
//            System.out.println(rs.getString("user_name")+" "
//                    +rs.getString("user_password"));
//        }

        //关闭资源
//        rs.close();
        st.close();
        conn.close();
    }
}
