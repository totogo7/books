package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.AdminDao;

/**
 * Servletʵ����updateAdminServlet�����ڴ�����¹���Ա��Ϣ������
 * ��δ�����һ��Servlet�����������Ǵ�����¹���Ա��Ϣ������
 * ��ͨ��AdminDao�������ݿ���н����������ύ�Ĺ���Ա��Ϣִ�и��²�����
 * �����û��ض��򵽹���Ա�б�ҳ�档
 * ���Servlet��Ҫ����POST����GET���󽫲��ᱻ����
 * 
 */
@WebServlet("/updateAdminServlet")
public class updateAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public updateAdminServlet() {
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
	 * �������л�ȡ���¹���Ա��Ϣ�����ݣ�������AdminDao��updateAdmin�������¹���Ա��Ϣ��
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡ����Ա��Ϣ��������Ĳ�����
		String username = request.getParameter("username"); // ��ȡ�û���
		String password = request.getParameter("password"); // ��ȡ����
		String name = request.getParameter("name"); // ��ȡ����
		String email = request.getParameter("email"); // ��ȡ�����ʼ�
		String phone = request.getParameter("phone"); // ��ȡ�绰
		int aid = Integer.parseInt(request.getParameter("aid")); // ��ȡ����ԱID

		// ����AdminDaoʵ�����������ݿ������
		AdminDao userdao = new AdminDao();

		// ����AdminDao��updateAdmin���������ݹ���ԱID���¹���Ա��Ϣ��
		userdao.updateAdmin(aid, username, password, name, email, phone);

		// ���¹���Ա��Ϣ���ض��򵽹���Ա�б�ҳ�档
		response.sendRedirect("/books/admin_admin.jsp");
	}
}