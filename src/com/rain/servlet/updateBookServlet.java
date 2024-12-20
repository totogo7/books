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
 * Servletʵ����updateBookServlet�����ڴ������ͼ����Ϣ������
 * ��δ�����һ��Servlet�����������Ǵ������ͼ����Ϣ������
 * ��ͨ��BookDao�������ݿ���н����������ύ��ͼ����Ϣִ�и��²�����
 * �����û��ض���ͼ�����ҳ�档
 * ���Servlet��Ҫ����POST����GET���󽫲��ᱻ����
 */
@WebServlet("/updateBookServlet")
public class updateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public updateBookServlet() {
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
	 * �������л�ȡ����ͼ����Ϣ�����ݣ�������BookDao��updateBook��������ͼ����Ϣ��
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡͼ����Ϣ��������Ĳ�����
		String card = request.getParameter("card"); // ��ȡ����֤��
		String name = request.getParameter("name"); // ��ȡͼ������
		String type = request.getParameter("type"); // ��ȡͼ������
		String autho = request.getParameter("autho"); // ��ȡ����
		String press = request.getParameter("press"); // ��ȡ������
		int num = Integer.parseInt(request.getParameter("num")); // ��ȡͼ������
		int bid = Integer.parseInt(request.getParameter("updatebid")); // ��ȡͼ��ID

		// ����BookDaoʵ�����������ݿ������
		BookDao bookdao = new BookDao();

		// ����BookDao��updateBook����������ͼ��ID����ͼ����Ϣ��
		bookdao.updateBook(bid, card, name, type, autho, press, num);

		// ����ͼ����Ϣ���ض���ͼ�����ҳ�档
		response.sendRedirect("/books/admin_book.jsp");
	}
}