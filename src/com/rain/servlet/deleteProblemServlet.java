package com.rain.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.ProblemDao;

/**
 * Servletʵ����deleteServlet�����ڴ���ɾ�����ⷴ��������
 * ��δ�����һ��Servlet�����������Ǵ���ɾ�����ⷴ��������
 * ��ͨ��ProblemDao�������ݿ���н����������ύ�����ⷴ��IDִ��ɾ��������
 * �����û��ض������ⷴ���б�ҳ�档
 * ���Servlet��Ҫ����GET����POST����ֱ�ӵ���GET�������д���
 * 
 */
@WebServlet("/deleteProblemServlet")
public class deleteProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public deleteProblemServlet() {
		super();
		// ���캯�������޴��룬��Ϊ������Servlet�淶��
	}

	/**
	 * ����GET����ķ�����
	 * �����������ִ��ɾ�����ⷴ���Ĳ�����
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡҪɾ�������ⷴ��ID������
		int pid = Integer.parseInt(request.getParameter("pid"));

		// ����ProblemDaoʵ�����������ݿ������
		ProblemDao problemdao = new ProblemDao();

		// ����ProblemDao��deleteProblem�������������ⷴ��IDɾ�����ⷴ����
		problemdao.deleteProblem(pid);

		// ɾ�����ⷴ�����ض������ⷴ���б�ҳ�档
		response.sendRedirect("/books/admin_feedback.jsp");
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