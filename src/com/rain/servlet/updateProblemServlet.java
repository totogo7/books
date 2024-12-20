package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.ProblemDao;

/**
 * Servletʵ����updateProblemServlet�����ڴ���������ⷴ��״̬������
 * ��δ�����һ��Servlet�����������Ǵ���������ⷴ��״̬������
 * ��ͨ��ProblemDao�������ݿ���н����������ύ�����ⷴ��״ִ̬�и��²�����
 * �����û��ض������ⷴ������ҳ�档���Servlet��Ҫ����POST����GET���󽫲��ᱻ����
 */
@WebServlet("/updateProblemServlet")
public class updateProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public updateProblemServlet() {
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
	 * �������л�ȡ�������ⷴ��״̬�����ݣ�������ProblemDao��updateProblem�����������ⷴ��״̬��
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡ���ⷴ��״̬��������Ĳ�����
		String status = request.getParameter("status"); // ��ȡ���ⷴ������״̬
		int pid = Integer.parseInt(request.getParameter("pid")); // ��ȡ���ⷴ��ID

		// ����ProblemDaoʵ�����������ݿ������
		ProblemDao problemdao = new ProblemDao();

		// ����ProblemDao��updateProblem�������������ⷴ��ID�������ⷴ��״̬��
		problemdao.updateProblem(pid, status);

		// �������ⷴ��״̬���ض������ⷴ������ҳ�档
		response.sendRedirect("/books/admin_feedback.jsp");
	}
}