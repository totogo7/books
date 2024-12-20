package com.rain.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.bean.ProblemBean;
import com.rain.dao.ProblemDao;

/**
 * Servletʵ����selectProblemServlet�����ڴ����ѯ���ⷴ��������
 * ��δ�����һ��Servlet�����������Ǵ����ѯ���ⷴ��������
 * ��ͨ��ProblemDao�������ݿ���н����������ύ�Ĳ�����ѯƥ������ⷴ���б�
 * ����������õ�request�����У��Ա���JSPҳ����չʾ��
 * Ȼ����������ת�������ⷴ������ҳ�档
 * 
 */
@WebServlet("/selectProblemServlet")
public class selectProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public selectProblemServlet() {
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
	 * �����������ִ�в�ѯ���ⷴ���Ĳ�����
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡ��ѯ����name������ģ����ѯ���ⷴ����
		String name = request.getParameter("name");

		// ����ProblemDaoʵ�����������ݿ������
		ProblemDao problemdao = new ProblemDao();

		// ����ProblemDao��getLikeList����������name������ѯƥ������ⷴ���б�
		ArrayList<ProblemBean> data = problemdao.getLikeList(name);

		// ����ѯ������õ�request�����У��Ա���JSPҳ����ʹ�á�
		request.setAttribute("data", data);

		// ȷ��Ҫ��ת��JSPҳ��URL��
		String url = response.encodeURL("admin_feedback.jsp");

		// ʹ��RequestDispatcher������ת����ָ����JSPҳ�档
		request.getRequestDispatcher(url).forward(request, response);
	}
}