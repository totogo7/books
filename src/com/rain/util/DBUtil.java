package com.rain.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库工具类DBUtil，用于数据库连接和关闭资源。
 * 这段代码是一个数据库工具类，它提供了获取数据库连接和关闭数据库资源的方法。
 * 通过这个类，可以方便地在其他部分的代码中获取数据库连接和释放数据库资源。
 * 静态初始化块用于加载MySQL数据库驱动，确保数据库连接可以正常建立。
 * getConnectDb方法用于获取数据库连接，CloseDB方法用于关闭数据库资源，释放内存。
 */
public class DBUtil {
    /**
     * 数据库连接参数。
     */
    public static String username = "root"; // 数据库用户名
    public static String password = "cd13467"; // 数据库密码
    public static String url = "jdbc:mysql://localhost:3306/books?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8"; // 数据库连接URL

    /**
     * 静态初始化块，用于加载数据库驱动。
     */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 加载MySQL数据库驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // 驱动类找不到时打印异常堆栈
        }
    }

    /**
     * 获取数据库连接。
     * 使用DriverManager获取数据库连接。
     *
     * @return 数据库连接对象Connection。
     */
    public static Connection getConnectDb() {
        Connection conn = null; // 初始化连接对象
        try {
            conn = DriverManager.getConnection(url, username, password); // 根据URL、用户名和密码获取连接
        } catch (SQLException e) {
            e.printStackTrace(); // 连接失败时打印异常堆栈
        }
        return conn; // 返回连接对象
    }

    /**
     * 关闭数据库资源。
     * 关闭ResultSet、PreparedStatement和Connection对象，释放资源。
     *
     * @param rs ResultSet对象，可能为null。
     * @param stm PreparedStatement对象，可能为null。
     * @param conn Connection对象，可能为null。
     */
    public static void CloseDB(ResultSet rs, PreparedStatement stm, Connection conn) {
        if (rs != null) {
            try {
                rs.close(); // 关闭ResultSet对象
            } catch (SQLException e) {
                e.printStackTrace(); // 关闭失败时打印异常堆栈
            }
        }
        if (stm != null) {
            try {
                stm.close(); // 关闭PreparedStatement对象
            } catch (SQLException e) {
                e.printStackTrace(); // 关闭失败时打印异常堆栈
            }
        }
        if (conn != null) {
            try {
                conn.close(); // 关闭Connection对象
            } catch (SQLException e) {
                e.printStackTrace(); // 关闭失败时打印异常堆栈
            }
        }
    }
}