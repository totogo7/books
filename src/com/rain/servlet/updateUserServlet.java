package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.AdminDao;

/**
 * Servletʵ����updateUserServlet�����ڴ�������û���Ϣ������
 * ��δ�����һ��Servlet�����������Ǵ�������û���Ϣ������
 * ��ͨ��AdminDao�������ݿ���н����������ύ���û���Ϣִ�и��²�����
 * �����û��ض����û�����ҳ�档���Servlet��Ҫ����POST����GET���󽫲��ᱻ����
 */
@WebServlet("/updateUserServlet")
public class updateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public updateUserServlet() {
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
	 * �������л�ȡ�����û���Ϣ�����ݣ�������AdminDao��updateUser���������û���Ϣ��
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡ�û���Ϣ��������Ĳ�����
		String username = request.getParameter("username"); // ��ȡ�û���
		String password = request.getParameter("password"); // ��ȡ����
		String name = request.getParameter("name"); // ��ȡ����
		String email = request.getParameter("email"); // ��ȡ�����ʼ�
		String phone = request.getParameter("phone"); // ��ȡ�绰
		int lend_num = Integer.parseInt(request.getParameter("lend_num")); // ��ȡ�ѽ�������
		int max_num = Integer.parseInt(request.getParameter("max_num")); // ��ȡ����������
		int aid = Integer.parseInt(request.getParameter("aid")); // ��ȡ�û�ID

		// ����AdminDaoʵ�����������ݿ������
		AdminDao userdao = new AdminDao();

		// ����AdminDao��updateUser�����������û�ID�����û���Ϣ��
		userdao.updateUser(aid, username, password, name, email, phone, lend_num, max_num);

		// �����û���Ϣ���ض����û�����ҳ�档
		response.sendRedirect("/books/admin_user.jsp");
	}
}