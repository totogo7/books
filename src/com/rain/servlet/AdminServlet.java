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
 * Servletʵ����AdminServlet�����ڴ������Ա��ز���������
 * ��δ�����һ��Servlet�����������Ǵ������Ա��Ϣ���µ�����
 * �����ύ�Ĳ����������Ը��¹���Ա�����������������Ϣ��
 * ��������ɹ������ض���ָ����ҳ�棻���ʧ�ܣ���������󣩣�
 * ����ʾ������Ϣ������ԭҳ�档


 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Ĭ�Ϲ��캯����
	 */
	public AdminServlet() {
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
	 * �����������ִ�й���Ա��Ϣ�ĸ��²�����
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ��Ӧ��PrintWriter�����������JavaScript����
		PrintWriter out = response.getWriter();
		// �����������Ӧ�ı�������ΪUTF-8����֧�ֹ��ʻ��ַ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// ��ȡ�������Ͳ����������ж����޸����뻹���޸�������Ϣ
		int tip = Integer.parseInt(request.getParameter("tip"));
		// ��ȡ��תURL���������ڲ�����ɺ��ҳ����ת
		String url = request.getParameter("url");

		// ��ȡ��ǰ�Ự����
		HttpSession session = request.getSession();
		// ����AdminBean�������ڴ洢����Ա��Ϣ
		AdminBean adminbean = new AdminBean();
		// �ӻỰ�л�ȡ����Ա��ID
		String aid = (String) session.getAttribute("aid");
		// ����AdminDao�����������ݿ����
		AdminDao admindao = new AdminDao();
		// ����aid��ȡ����Ա����ϸ��Ϣ
		adminbean = admindao.get_AidInfo2(aid);

		// ���ݲ�������ִ�в�ͬ�ĸ��²���
		if (tip == 1) {
			// �޸��������
			// ��ȡ�������ȷ���������
			String password = request.getParameter("password");
			String password2 = request.getParameter("password2");
			// ��ȡԭ����
			String old_password = adminbean.getPassword();
			// ��֤ԭ�����Ƿ���ȷ
			if (old_password.equals(password)) {
				// ������֤ͨ��������������
				admindao.updateUser(adminbean.getAid(), adminbean.getUsername(), password2, adminbean.getName(),
						adminbean.getEmail(), adminbean.getPhone(), adminbean.getLend_num(), adminbean.getMax_num());
				// ������º���ת��ָ��ҳ��
				response.sendRedirect("/books/" + url + ".jsp");
			} else {
				// ������֤ʧ�ܣ����������Ϣ����ת��ԭҳ��
				out.write("<script type='text/javascript'>alert('password error');location.href='" + url
						+ ".jsp';  </script>");
			}
		} else {
			// �޸�������Ϣ����
			// ��ȡ�����������ʼ��͵绰����
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			// ���¹���Ա��������Ϣ
			admindao.updateUser(adminbean.getAid(), adminbean.getUsername(), adminbean.getPassword(), name, email,
					phone, adminbean.getLend_num(), adminbean.getMax_num());
			// ��Ϣ���º���ת��ָ��ҳ��
			response.sendRedirect("/books/" + url + ".jsp");
		}
	}
}