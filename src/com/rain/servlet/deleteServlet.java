package com.rain.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.BookDao;

/**
 * Servletʵ����deleteServlet�����ڴ���ɾ��ͼ�������
 * ��δ�����һ��Servlet�����������Ǵ���ɾ��ͼ�������
 * ��ͨ��BookDao�������ݿ���н����������ύ��ͼ��IDִ��ɾ��������
 * �����û��ض���ͼ�����ҳ�档
 * ���Servlet��Ҫ����GET����POST����ֱ�ӵ���GET�������д���
 */
@WebServlet("/deleteServlet")
public class deleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public deleteServlet() {
		super();
		// ���캯�������޴��룬��Ϊ������Servlet�淶��
	}

	/**
	 * ����GET����ķ�����
	 * �����������ִ��ɾ��ͼ��Ĳ�����
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡҪɾ����ͼ��ID������
		int bid = Integer.parseInt(request.getParameter("bid"));

		// ����BookDaoʵ�����������ݿ������
		BookDao bookdao = new BookDao();

		// ����BookDao��deleteBook����������ͼ��IDɾ��ͼ�顣
		bookdao.deleteBook(bid);

		// ɾ��ͼ����ض���ͼ�����ҳ�档
		response.sendRedirect("/books/admin_book.jsp");
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