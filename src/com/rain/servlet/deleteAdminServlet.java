package com.rain.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.AdminDao;

/**
 * Servletʵ����deleteUserServlet�����ڴ���ɾ������Ա������
 * ��δ�����һ��Servlet�����������Ǵ���ɾ������Ա������
 * ��ͨ��AdminDao�������ݿ���н����������ύ�Ĺ���ԱIDִ��ɾ��������
 * �����û��ض��򵽹���Ա�б�ҳ�档
 * ���Servlet��Ҫ����GET����POST����ֱ�ӵ���GET�������д���
 */
@WebServlet("/deleteAdminServlet")
public class deleteAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public deleteAdminServlet() {
		super();
		// ���캯�������޴��룬��Ϊ������Servlet�淶��
	}

	/**
	 * ����GET����ķ�����
	 * �����������ִ��ɾ������Ա�Ĳ�����
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡҪɾ���Ĺ���ԱID������
		int aid = Integer.parseInt(request.getParameter("aid"));

		// ����AdminDaoʵ�����������ݿ������
		AdminDao admindao = new AdminDao();

		// ����AdminDao��deleteAdmin���������ݹ���ԱIDɾ������Ա��
		admindao.deleteAdmin(aid);

		// ɾ������Ա���ض��򵽹���Ա�б�ҳ�档
		response.sendRedirect("/books/admin_admin.jsp");
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