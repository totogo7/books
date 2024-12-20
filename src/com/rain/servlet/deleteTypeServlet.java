package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.BookDao;
import com.rain.dao.TypeDao;

/**
 * Servletʵ����deleteTypeServlet�����ڴ���ɾ��ͼ�����͵�����
 * ��δ�����һ��Servlet�����������Ǵ���ɾ��ͼ�����͵�����
 * ��ͨ��TypeDao�������ݿ���н����������ύ��ͼ������IDִ��ɾ��������
 * �����û��ض���ͼ�����͹���ҳ�档
 * ���Servlet��Ҫ����GET����POST����ֱ�ӵ���GET�������д���
 */
@WebServlet("/deleteTypeServlet")
public class deleteTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public deleteTypeServlet() {
		super();
		// ���캯�������޴��룬��Ϊ������Servlet�淶��
	}

	/**
	 * ����GET����ķ�����
	 * �����������ִ��ɾ��ͼ�����͵Ĳ�����
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡҪɾ����ͼ������ID������
		int tid = Integer.parseInt(request.getParameter("tid"));

		// ����TypeDaoʵ�����������ݿ������
		TypeDao typedao = new TypeDao();

		// ����TypeDao��deleteBookType����������ͼ������IDɾ��ͼ�����͡�
		typedao.deleteBookType(tid);

		// ɾ��ͼ�����ͺ��ض���ͼ�����͹���ҳ�档
		response.sendRedirect("/books/admin_booktype.jsp");
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