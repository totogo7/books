package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rain.bean.AdminBean;
import com.rain.dao.AdminDao;

/**
 * Servletʵ����AdminLoginServlet�����ڴ������Ա��¼����
 * ��δ�����һ��Servlet�����������Ǵ������Ա�ĵ�¼����
 * ��ͨ��AdminDao�������ݿ���н�������֤�ύ���û����������Ƿ�ƥ�䡣
 * �����֤�ɹ������û��ض��򵽹���Ա��ҳ��
 * �����֤ʧ�ܣ��򽫴�����Ϣ���õ��Ự�У����ض���ص�¼ҳ�档
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public AdminLoginServlet() {
		super();
		// ���캯�������޴��룬��Ϊ������Servlet�淶��
	}

	// ����AdminDaoʵ�����������ݿ����
	AdminDao adminDao = new AdminDao();

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
	 * �������л�ȡ����Ա��¼��Ϣ������֤����Ա��ݡ�
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡ�û��ύ�ĵ�¼��Ϣ��
		String username = request.getParameter("username"); // ��ȡ�û���
		String password = request.getParameter("password"); // ��ȡ����

		// ʹ��AdminDao��֤����Ա��Ϣ
		AdminBean adminBean = adminDao.getAdminInfo(username, password);

		// �жϹ���Ա��Ϣ�Ƿ���Ч
		if (adminBean != null) {
			// �������Ա��Ϣ��Ч���ض��򵽹���Ա��ҳ
			response.sendRedirect("/books/admin_admin.jsp");
		} else {
			// �������Ա��Ϣ��Ч�����ô�����Ϣ���Ự�����ض��򵽵�¼ҳ��
			HttpSession session = request.getSession();
			session.setAttribute("state", "�û������������");
			response.sendRedirect("/books/admin_login.jsp");
		}
	}
}