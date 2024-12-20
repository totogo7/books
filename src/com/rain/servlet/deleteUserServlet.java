package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.AdminDao;

/**
 * Servletʵ����deleteUserServlet�����ڴ���ɾ���û�������
 * ��δ�����һ��Servlet�����������Ǵ���ɾ���û�������
 * ��ͨ��AdminDao�������ݿ���н����������ύ���û�IDִ��ɾ��������
 * �����û��ض����û�����ҳ�档
 * ���Servlet��Ҫ����GET����POST����ֱ�ӵ���GET�������д���
 */
@WebServlet("/deleteUserServlet")
public class deleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public deleteUserServlet() {
		super();
		// ���캯�������޴��룬��Ϊ������Servlet�淶��
	}

	/**
	 * ����GET����ķ�����
	 * �����������ִ��ɾ���û��Ĳ�����
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡҪɾ�����û�ID������
		int aid = Integer.parseInt(request.getParameter("aid"));

		// ����AdminDaoʵ�����������ݿ������
		AdminDao admindao = new AdminDao();

		// ����AdminDao��deleteUser�����������û�IDɾ���û���
		admindao.deleteUser(aid);

		// ɾ���û����ض����û�����ҳ�档
		response.sendRedirect("/books/admin_user.jsp");
	}

	/**
	 * ����POST����ķ�����
	 * ��Servlet��Ҫ����GET����POST����Ĵ�����Ϊ�գ�ֱ�ӵ���GET������
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}