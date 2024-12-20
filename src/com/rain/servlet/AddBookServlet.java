package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.BookDao;

/**
 * Servletʵ����AddBookServlet�����ڴ��������ͼ�������
 * ��δ�����һ��Servlet�����������Ǵ������Ա���POST���󣬽���ͼ�����Ϣ��ӵ����ݿ��С�
 * ��ͨ��BookDao�������ݿ���н��������û��ύ�Ľ���֤�š�ͼ�����ơ����͡����ߡ�������������洢�����ݿ��С�
 * ������Ϻ������û��ض���ͼ�����ҳ�档
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public AddBookServlet() {
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
	 * �������л�ȡͼ�����ݣ�������BookDao��addBook��������ͼ����Ϣ��ӵ����ݿ⡣
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡ�û��ύ��ͼ�����ݡ�
		String card = request.getParameter("card"); // ��ȡ����֤��
		String name = request.getParameter("name"); // ��ȡͼ������
		String type = request.getParameter("type"); // ��ȡͼ������
		String autho = request.getParameter("autho"); // ��ȡ����
		String press = request.getParameter("press"); // ��ȡ������
		int num = Integer.parseInt(request.getParameter("num")); // ��ȡͼ������

		// ����BookDaoʵ�����������ݿ������
		BookDao bookdao = new BookDao();

		// ����BookDao��addBook����������ͼ����Ϣ��ӵ����ݿ⡣
		bookdao.addBook(card, name, type, autho, press, num);

		// �ض���ͼ�����ҳ�档
		response.sendRedirect("/books/admin_book.jsp");
	}
}