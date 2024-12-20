package com.rain.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rain.bean.AdminBean;
import com.rain.dao.AdminDao;
import com.rain.dao.BookDao;

/**
 * Servletʵ����borrowServlet�����ڴ���ͼ����ĺ͹黹������
 * 
 * ��δ�����һ��Servlet�����������Ǵ���ͼ����ĺ͹黹������
 * �����ύ�Ĳ�����������ִ��ͼ����Ļ�黹�����������û��ض���ָ����ҳ�档
 * ��������ǽ���ͼ�飬����ͨ��BookDao�������ݿ���н�������¼������Ϣ��
 * ����ǹ黹ͼ�飬������½��ļ�¼��״̬��
 * 
 */
@WebServlet("/borrowServlet")
public class borrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public borrowServlet() {
		super();
		// ���캯�������޴��룬��Ϊ������Servlet�淶��
	}

	/**
	 * ����GET����ķ�����
	 * �����������ִ��ͼ����Ļ�黹������
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// ����BookDaoʵ�����������ݿ������
		BookDao bookdao = new BookDao();

		// ��ȡ�������Ͳ����������ж��ǽ��Ļ��ǹ黹ͼ�顣
		int tip = Integer.parseInt(request.getParameter("tip"));

		if (tip == 1) {
			// ����ͼ�����
			// ��ȡͼ��ID����
			int bid = Integer.parseInt(request.getParameter("bid"));
			// ��ȡ�Ƿ���ʾ����ҳ�Ĳ���
			int show = Integer.parseInt(request.getParameter("show"));
			// ��ȡ��ǰ�Ự����
			HttpSession session = request.getSession();
			// ����AdminBean�������ڴ洢����Ա��Ϣ
			AdminBean admin = new AdminBean();
			// �ӻỰ�л�ȡ����Ա��ID
			String aid = (String) session.getAttribute("aid");
			// ����AdminDaoʵ�����������ݿ����
			AdminDao admindao = new AdminDao();
			// ����aid��ȡ����Ա����ϸ��Ϣ
			admin = admindao.get_AidInfo2(aid);
			// ����BookDao��borrowBook������ִ��ͼ����Ĳ���
			bookdao.borrowBook(bid, admin);
			// ����show���������ض����ҳ��
			if (show == 1) {
				response.sendRedirect("/books/select.jsp");
			} else {
				response.sendRedirect("/books/bdtimes.jsp");
			}
		} else {
			// �黹ͼ�����
			// ��ȡ���ļ�¼ID����
			int hid = Integer.parseInt(request.getParameter("hid"));
			// ��ȡ�Ƿ���ʾ����ҳ�Ĳ���
			int show = Integer.parseInt(request.getParameter("show"));
			// ����BookDao��borrowBook2������ִ��ͼ��黹����
			bookdao.borrowBook2(hid);
			// ����show���������ض����ҳ��
			if (show == 1) {
				response.sendRedirect("/books/borrow.jsp");
			} else {
				response.sendRedirect("/books/admin_borrow.jsp");
			}
		}
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