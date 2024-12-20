package com.rain.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ���ݿ⹤����DBUtil���������ݿ����Ӻ͹ر���Դ��
 * ��δ�����һ�����ݿ⹤���࣬���ṩ�˻�ȡ���ݿ����Ӻ͹ر����ݿ���Դ�ķ�����
 * ͨ������࣬���Է�������������ֵĴ����л�ȡ���ݿ����Ӻ��ͷ����ݿ���Դ��
 * ��̬��ʼ�������ڼ���MySQL���ݿ�������ȷ�����ݿ����ӿ�������������
 * getConnectDb�������ڻ�ȡ���ݿ����ӣ�CloseDB�������ڹر����ݿ���Դ���ͷ��ڴ档
 */
public class DBUtil {
    /**
     * ���ݿ����Ӳ�����
     */
    public static String username = "root"; // ���ݿ��û���
    public static String password = "cd13467"; // ���ݿ�����
    public static String url = "jdbc:mysql://localhost:3306/books?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8"; // ���ݿ�����URL

    /**
     * ��̬��ʼ���飬���ڼ������ݿ�������
     */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // ����MySQL���ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // �������Ҳ���ʱ��ӡ�쳣��ջ
        }
    }

    /**
     * ��ȡ���ݿ����ӡ�
     * ʹ��DriverManager��ȡ���ݿ����ӡ�
     *
     * @return ���ݿ����Ӷ���Connection��
     */
    public static Connection getConnectDb() {
        Connection conn = null; // ��ʼ�����Ӷ���
        try {
            conn = DriverManager.getConnection(url, username, password); // ����URL���û����������ȡ����
        } catch (SQLException e) {
            e.printStackTrace(); // ����ʧ��ʱ��ӡ�쳣��ջ
        }
        return conn; // �������Ӷ���
    }

    /**
     * �ر����ݿ���Դ��
     * �ر�ResultSet��PreparedStatement��Connection�����ͷ���Դ��
     *
     * @param rs ResultSet���󣬿���Ϊnull��
     * @param stm PreparedStatement���󣬿���Ϊnull��
     * @param conn Connection���󣬿���Ϊnull��
     */
    public static void CloseDB(ResultSet rs, PreparedStatement stm, Connection conn) {
        if (rs != null) {
            try {
                rs.close(); // �ر�ResultSet����
            } catch (SQLException e) {
                e.printStackTrace(); // �ر�ʧ��ʱ��ӡ�쳣��ջ
            }
        }
        if (stm != null) {
            try {
                stm.close(); // �ر�PreparedStatement����
            } catch (SQLException e) {
                e.printStackTrace(); // �ر�ʧ��ʱ��ӡ�쳣��ջ
            }
        }
        if (conn != null) {
            try {
                conn.close(); // �ر�Connection����
            } catch (SQLException e) {
                e.printStackTrace(); // �ر�ʧ��ʱ��ӡ�쳣��ջ
            }
        }
    }
}