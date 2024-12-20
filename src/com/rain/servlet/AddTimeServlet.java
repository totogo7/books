package com.rain.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.bean.HistoryBean;
import com.rain.dao.BookDao;

/**
 * Servletʵ����AddTimeServlet�����ڴ���ͼ����������
 * ��δ�����һ��Servlet�����������Ǵ���ͼ�����ڵ�����
 * ������GET������POST���󣬶������doPost����������
 * ��ͨ��BookDao�������ݿ���н���������ָ�����ļ�¼�Ľ�ֹ���ڡ�
 * ������Ϻ������û��ض���ͼ����Ĺ���ҳ�档
 * 
 */
@WebServlet("/AddTimeServlet")
public class AddTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public AddTimeServlet() {
		super();
		// ���캯�������޴��룬��Ϊ������Servlet�淶��
	}

	/**
	 * ����GET����ķ�����
	 * ���ڱ�Servlet��Ҫ����POST����GET����Ĵ�������ֱ�ӵ���POST������
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ֱ�ӵ���POST������������
		doPost(request, response);
	}

	/**
	 * ����POST����ķ�����
	 * �������л�ȡͼ���������ݣ�������BookDao��AddTime��������ͼ����Ľ�ֹ���ڡ�
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// �������л�ȡ�û��ύ��������Ϣ��
		String endtime = request.getParameter("endtime"); // ��ȡ�µĽ�ֹ����
		int hid = Integer.parseInt(request.getParameter("hid")); // ��ȡ���ļ�¼ID

		// ����BookDaoʵ�����������ݿ������
		BookDao bookdao = new BookDao();

		// ����BookDao��AddTime����������ͼ��Ľ��Ľ�ֹ���ڡ�
		bookdao.AddTime(hid, endtime);

		// �ض���ͼ����Ĺ���ҳ�档
		response.sendRedirect("/books/admin_borrow.jsp");
	}
}