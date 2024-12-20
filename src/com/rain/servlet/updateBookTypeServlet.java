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
 * Servletʵ����updateBookTypeServlet�����ڴ������ͼ��������Ϣ������
 * ��δ�����һ��Servlet�����������Ǵ������ͼ��������Ϣ������
 * ��ͨ��TypeDao�������ݿ���н����������ύ��ͼ��������Ϣִ�и��²�����
 * �����û��ض���ͼ�����͹���ҳ�档
 * ���Servlet��Ҫ����POST����GET���󽫲��ᱻ����
 */
@WebServlet("/updateBookTypeServlet")
public class updateBookTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public updateBookTypeServlet() {
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
	 * �������л�ȡ����ͼ��������Ϣ�����ݣ�������TypeDao��updateTypeBook��������ͼ��������Ϣ��
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡͼ��������Ϣ��������Ĳ�����
		String name = request.getParameter("name"); // ��ȡͼ����������
		int tid = Integer.parseInt(request.getParameter("tid")); // ��ȡͼ������ID

		// ����TypeDaoʵ�����������ݿ������
		TypeDao typedao = new TypeDao();

		// ����TypeDao��updateTypeBook����������ͼ������ID����ͼ��������Ϣ��
		typedao.updateTypeBook(tid, name);

		// ����ͼ��������Ϣ���ض���ͼ�����͹���ҳ�档
		response.sendRedirect("/books/admin_booktype.jsp");
	}
}