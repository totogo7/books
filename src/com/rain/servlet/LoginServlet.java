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
 * Servletʵ����LoginServlet�����ڴ������Ա��¼������
 * ��δ�����һ��Servlet�����������Ǵ������Ա�ĵ�¼����
 * ��ͨ��AdminDao�������ݿ���н�������֤�ύ���û����������Ƿ�ƥ�䡣
 * �����֤�ɹ���������ԱID���õ��Ự�У������ݹ���Ա��״̬�ض�����Ӧ��ҳ�棻
 * �����֤ʧ�ܣ��򽫴�����Ϣ���õ��Ự�У����ض���ص�¼ҳ�档
 * ���Servlet��Ҫ����POST����GET���󽫲��ᱻ����
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public LoginServlet() {
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
	 * �������л�ȡ����Ա��¼��Ϣ������֤����Ա��ݡ�
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡ����Ա���û��������롣
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// ����AdminDaoʵ�����������ݿ������
		AdminDao userdao = new AdminDao();

		// ����AdminDao��Login_verify��������֤�û����������Ƿ���ȷ��
		boolean result = userdao.Login_verify(username, password);

		// ��ȡ��ǰ�Ự����
		HttpSession session = request.getSession();

		if (result) {
			// �����֤ͨ��������AdminBean�������ڴ洢����Ա��Ϣ��
			AdminBean adminbean = new AdminBean();
			// ����AdminDaoʵ�������ڻ�ȡ����Ա��ϸ��Ϣ��
			AdminDao admindao = new AdminDao();
			// �����û����������ȡ����Ա��ϸ��Ϣ��
			adminbean = admindao.getAdminInfo(username, password);
			// ������ԱID�洢���Ự�У��Ա����ʹ�á�
			session.setAttribute("aid", "" + adminbean.getAid());
			// ���ûỰ����ʱ��Ϊ6000�루16���ӣ���
			session.setMaxInactiveInterval(6000);
			// ���ݹ���Ա��״̬�ض��򵽲�ͬ��ҳ�档
			if (adminbean.getStatus() == 1) {
				response.sendRedirect("/books/index.jsp");
			} else {
				response.sendRedirect("/books/admin.jsp");
			}
		} else {
			// �����֤ʧ�ܣ����ô�����Ϣ���Ự�����ض��򵽵�¼ҳ�档
			session.setAttribute("state", "�˺Ż��������");
			response.sendRedirect("/books/login.jsp");
		}
	}
}