package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.AdminDao;

/**
 * Servletʵ����AddUserServlet�����ڴ���������û�������
 * ��δ�����һ��Servlet�����������Ǵ������Ա���POST����
 * �����û�����Ϣ��ӵ����ݿ��С���ͨ��AdminDao�������ݿ���н�����
 * ���û��ύ���û��������롢�����������ʼ����绰�����������������������洢�����ݿ��С�
 * ������Ϻ������û��ض��򵽹���Ա�û��б�ҳ�档
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public AddUserServlet() {
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
	 * �������л�ȡ�û��ύ�����ݣ�������AdminDao��Register���������û���Ϣ��ӵ����ݿ⡣
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡ�û��ύ�����ݡ�
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		int lend_num = Integer.parseInt(request.getParameter("lend_num"));
		int max_num = Integer.parseInt(request.getParameter("max_num"));

		// ����AdminDaoʵ�����������ݿ������
		AdminDao userdao = new AdminDao();

		// ����AdminDao��Register�����������û���Ϣ��ӵ����ݿ⡣
		userdao.Register(username, password, name, email, phone, lend_num, max_num);

		// �ض��򵽹���Ա�û��б�ҳ�档
		response.sendRedirect("/books/admin_user.jsp");
	}
}