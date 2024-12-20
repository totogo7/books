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
 * Servletʵ����AddBookTypeServlet�����ڴ��������ͼ�����͵�����
 * ��δ�����һ��Servlet�����������Ǵ������Ա���POST����
 * ����ͼ�����͵���Ϣ��ӵ����ݿ��С�
 * ��ͨ��TypeDao�������ݿ���н��������û��ύ��ͼ���������ƴ洢�����ݿ��С�
 * ������Ϻ������û��ض���ͼ�����͹���ҳ�档
 */
@WebServlet("/AddBookTypeServlet")
public class AddBookTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public AddBookTypeServlet() {
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
	 * �������л�ȡͼ�����͵����ݣ�������TypeDao��addBookType��������ͼ��������Ϣ��ӵ����ݿ⡣
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡ�û��ύ��ͼ���������ݡ�
		String name = request.getParameter("name"); // ��ȡͼ����������

		// ����TypeDaoʵ�����������ݿ������
		TypeDao typedao = new TypeDao();

		// ����TypeDao��addBookType����������ͼ��������Ϣ��ӵ����ݿ⡣
		typedao.addBookType(name);

		// �ض���ͼ�����͹���ҳ�档
		response.sendRedirect("/books/admin_booktype.jsp");
	}
}