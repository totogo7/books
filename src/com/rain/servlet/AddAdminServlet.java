package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.AdminDao;

/**
 * Servletʵ����AddAdminServlet�����ڴ�������¹���Ա������
 * ��δ�����һ��Servlet�����������Ǵ������Ա���POST���󣬽��¹���Ա����Ϣ��ӵ����ݿ��С�
 * ��ͨ��AdminDao�������ݿ���н��������û��ύ���û��������롢�����������ʼ��͵绰�洢�����ݿ��С�
 * ������Ϻ������û��ض��򵽹���Ա�б�ҳ�档
 */
@WebServlet("/AddAdminServlet")
public class AddAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //@WebServlet("/AddAdminServlet")��һ��ע�⣬�����������Servlet��URLģʽ��
	//���ͻ��˿���ͨ������/AddAdminServlet���������Servlet��
	//serialVersionUID��һ���������л��Ķ���汾�ţ�ͨ�����ڰ汾���ơ�
	
	
	/**
	 * Ĭ�Ϲ��캯����
	 */
	public AddAdminServlet() {
		super();
		// ���캯������д�Ե��ø���Ĺ��캯��
	}

	/**
	 * ����GET����ķ�����
	 * ���ڱ�Servlet��Ҫ����POST����GET����Ĵ�����Ϊ�ա�
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����ʵ�֣�GET���󲻱�����
	}

	/**
	 * ����POST����ķ�����
	 * �������л�ȡ����Ա�ύ�����ݣ�������AdminDao��Register2�������¹���Ա��Ϣ��ӵ����ݿ⡣
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡ�û��ύ�����ݡ�
		String username = request.getParameter("username"); // ��ȡ�û���
		String password = request.getParameter("password"); // ��ȡ����
		String name = request.getParameter("name"); // ��ȡ����
		String email = request.getParameter("email"); // ��ȡ�����ʼ�
		String phone = request.getParameter("phone"); // ��ȡ�绰

		// ����AdminDaoʵ�����������ݿ������
		AdminDao userdao = new AdminDao();

		// ����AdminDao��Register2���������¹���Ա��Ϣ��ӵ����ݿ⡣
		userdao.Register2(username, password, name, email, phone);

		// �ض��򵽹���Ա�б�ҳ�档
		response.sendRedirect("/books/admin_admin.jsp");
	}
}