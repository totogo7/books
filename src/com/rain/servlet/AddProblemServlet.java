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
import com.rain.dao.ProblemDao;

/**
 * Servletʵ����AddProblemServlet�����ڴ���������ⷴ��������
 * ��δ�����һ��Servlet�����������Ǵ������Ա���POST����
 * ���û������ⷴ����Ϣ��ӵ����ݿ��С�
 * ��ͨ��ProblemDao�������ݿ���н�����
 * ���û��ύ��������ҳ�桢�������ݺ͵绰�洢�����ݿ��С�
 * ͬʱ�������ӻỰ�л�ȡ��ǰ��¼����Ա����Ϣ�������������ⷴ��������
 * ������Ϻ������û��ض��򵽽��ҳ�档
 */
@WebServlet("/AddProblemServlet")
public class AddProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public AddProblemServlet() {
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
	 * �������л�ȡ���ⷴ�����ݣ�������ProblemDao��addProblem���������ⷴ����Ϣ��ӵ����ݿ⡣
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// ��ȡ��ǰ�Ự����
		HttpSession session = request.getSession();
		// ����AdminBean�������ڴ洢����Ա��Ϣ
		AdminBean adminbean = new AdminBean();
		// �ӻỰ�л�ȡ����Ա��ID
		String aid = (String) session.getAttribute("aid");
		// ����AdminDao�����������ݿ����
		AdminDao admindao = new AdminDao();
		// ����aid��ȡ����Ա����ϸ��Ϣ
		adminbean = admindao.get_AidInfo2(aid);

		// �������л�ȡ�û��ύ�����ⷴ������
		String name = request.getParameter("name"); // ��ȡ����������
		String page = request.getParameter("page"); // ��ȡ����ҳ��
		String body = request.getParameter("body"); // ��ȡ��������
		String phone = request.getParameter("phone"); // ��ȡ�����ߵ绰

		// ����ProblemDao�����������ݿ����
		ProblemDao problemdao = new ProblemDao();
		// ����ProblemDao��addProblem�����������ⷴ����Ϣ��ӵ����ݿ�
		problemdao.addProblem(adminbean, name, page, body, phone);

		// �ض��򵽽��ҳ��
		response.sendRedirect("/books/result.jsp");
	}
}