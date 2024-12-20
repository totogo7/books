package com.rain.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.bean.AdminBean;
import com.rain.dao.AdminDao;

/**
 * Servletʵ����brtimesServlet�����ڴ������Ա���Ĵ�����ص�����
 * ��δ�����һ��Servlet�����������Ǵ������Ա���Ĵ����Ĳ�ѯ����
 * ��ͨ��AdminDao�������ݿ���н����������ύ�Ĳ�����ѯƥ��Ĺ���Ա�б�
 * ����������õ�request�����У��Ա���JSPҳ����չʾ�����ݲ������ͣ���������ת������ͬ��JSPҳ�档
 */
@WebServlet("/brtimesServlet")
public class brtimesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public brtimesServlet() {
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
	 * �����������ִ�й���Ա���Ĵ����Ĳ�ѯ������
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// ��ȡ�������tip�������ж��ǲ�ѯ���н��Ĵ������ǲ�ѯ�ض������Ľ��Ĵ�����
		int tip = Integer.parseInt(request.getParameter("tip"));
		// ��ȡ�������name������ģ����ѯ����Ա������
		String name = request.getParameter("name");

		// ����AdminDaoʵ�����������ݿ������
		AdminDao admindao = new AdminDao();
		// ����name��������getLikeList������ѯƥ��Ĺ���Ա�б�
		ArrayList<AdminBean> data = admindao.getLikeList(name);

		// ����ѯ������õ�request�����У��Ա���JSPҳ����ʹ�á�
		request.setAttribute("data", data);

		// ����tip����ȷ��Ҫ��ת��JSPҳ�档
		String url = "";
		if (tip == 1) {
			// ���tipΪ1����ת������Ա���Ĵ���ͳ��ҳ�档
			url = response.encodeURL("admin_brtimes.jsp");
		} else {
			// ������ת����ͨ���Ĵ���ͳ��ҳ�档
			url = response.encodeURL("brtimes.jsp");
		}

		// ʹ��RequestDispatcher������ת����ָ����JSPҳ�档
		request.getRequestDispatcher(url).forward(request, response);
	}
}