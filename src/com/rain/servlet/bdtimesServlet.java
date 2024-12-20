package com.rain.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.bean.BookBean;
import com.rain.dao.BookDao;

/**
 * Servletʵ����bdtimesServlet�����ڴ���ͼ����Ĵ�����ص�����
 * ��δ�����һ��Servlet�����������Ǵ���ͼ����Ĵ����Ĳ�ѯ����
 * ��ͨ��BookDao�������ݿ���н����������ύ�Ĳ�����ѯƥ���ͼ���б�
 * ����������õ�request�����У��Ա���JSPҳ����չʾ��
 * ���ݲ������ͣ���������ת������ͬ��JSPҳ�档
 */
@WebServlet("/bdtimesServlet")
public class bdtimesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public bdtimesServlet() {
		super();
		// ���캯�������޴��룬��Ϊ������Servlet�淶��
	}

	/**
	 * ����GET����ķ�����
	 * ���ڱ�Servlet��Ҫ����POST����GET����Ĵ�����Ϊ�ա�
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ����ʵ�֣�GET���󲻱�����
	}

	/**
	 * ����POST����ķ�����
	 * �����������ִ��ͼ����Ĵ����Ĳ�ѯ������
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// ��ȡ�������tip�������ж��ǲ�ѯ���н��Ĵ������ǲ�ѯ�ض������Ľ��Ĵ�����
		int tip = Integer.parseInt(request.getParameter("tip"));
		// ��ȡ�������name������ģ����ѯͼ�����ơ����߻����͡�
		String name = request.getParameter("name");

		// ����BookDaoʵ�����������ݿ������
		BookDao bookdao = new BookDao();
		// ����name��������getLikeList������ѯƥ���ͼ���б�
		ArrayList<BookBean> data = bookdao.getLikeList(name);

		// ����ѯ������õ�request�����У��Ա���JSPҳ����ʹ�á�
		request.setAttribute("data", data);

		// ����tip����ȷ��Ҫ��ת��JSPҳ�档
		String url = "";
		if (tip == 1) {
			// ���tipΪ1����ת������Ա���Ĵ���ͳ��ҳ�档
			url = response.encodeURL("admin_bdtimes.jsp");
		} else {
			// ������ת����ͨ���Ĵ���ͳ��ҳ�档
			url = response.encodeURL("bdtimes.jsp");
		}

		// ʹ��RequestDispatcher������ת����ָ����JSPҳ�档
		request.getRequestDispatcher(url).forward(request, response);
	}
}