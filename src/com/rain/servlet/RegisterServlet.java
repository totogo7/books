package com.rain.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.AdminDao;

/**
 * Servletʵ����RegisterServlet�����ڴ����û�ע�������
 * ��δ�����һ��Servlet�����������Ǵ����û���ע������
 * ��ͨ��AdminDao�������ݿ���н��������û��ύ��ע����Ϣ�洢�����ݿ��С�
 * ע��ɹ����û��ᱻ�ض��򵽵�¼ҳ�档
 * ���Servlet��Ҫ����POST����GET���󽫲��ᱻ����
 * 
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public RegisterServlet() {
		super();
		// ���캯�������޴��룬��Ϊ������Servlet�淶��
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
	 * �������л�ȡ�û�ע����Ϣ��������AdminDao��Register���������û���Ϣ��ӵ����ݿ⡣
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡ�û��ύ��ע����Ϣ��
		String username = request.getParameter("username"); // ��ȡ�û���
		String password = request.getParameter("password"); // ��ȡ����
		String name = request.getParameter("name"); // ��ȡ����
		String email = request.getParameter("email"); // ��ȡ�����ʼ�
		String phone = request.getParameter("phone"); // ��ȡ�绰

		// ����Ĭ�ϵĽ���������������������
		int lend_num = 30; // Ĭ�Ͻ�������
		int max_num = 5;   // Ĭ������������

		// ����AdminDaoʵ�����������ݿ������
		AdminDao userdao = new AdminDao();

		// ����AdminDao��Register�����������û���Ϣ��ӵ����ݿ⡣
		userdao.Register(username, password, name, email, phone, lend_num, max_num);

		// ע��ɹ����ض��򵽵�¼ҳ�档
		response.sendRedirect("/books/login.jsp");
	}
}