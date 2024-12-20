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
 * Servlet实现类borrowServlet，用于处理图书借阅和归还的请求。
 * 
 * 这段代码是一个Servlet，它的作用是处理图书借阅和归还的请求。
 * 根据提交的参数，它可以执行图书借阅或归还操作，并将用户重定向到指定的页面。
 * 如果操作是借阅图书，它会通过BookDao类与数据库进行交互，记录借阅信息；
 * 如果是归还图书，它会更新借阅记录的状态。
 * 
 */
@WebServlet("/borrowServlet")
public class borrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数。
	 */
	public borrowServlet() {
		super();
		// 构造函数中暂无代码，仅为了满足Servlet规范。
	}

	/**
	 * 处理GET请求的方法。
	 * 根据请求参数执行图书借阅或归还操作。
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求和响应的编码类型为UTF-8，以支持国际化字符。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 创建BookDao实例，用于数据库操作。
		BookDao bookdao = new BookDao();

		// 获取操作类型参数，用于判断是借阅还是归还图书。
		int tip = Integer.parseInt(request.getParameter("tip"));

		if (tip == 1) {
			// 借阅图书操作
			// 获取图书ID参数
			int bid = Integer.parseInt(request.getParameter("bid"));
			// 获取是否显示在首页的参数
			int show = Integer.parseInt(request.getParameter("show"));
			// 获取当前会话对象
			HttpSession session = request.getSession();
			// 创建AdminBean对象，用于存储管理员信息
			AdminBean admin = new AdminBean();
			// 从会话中获取管理员的ID
			String aid = (String) session.getAttribute("aid");
			// 创建AdminDao实例，用于数据库操作
			AdminDao admindao = new AdminDao();
			// 根据aid获取管理员的详细信息
			admin = admindao.get_AidInfo2(aid);
			// 调用BookDao的borrowBook方法，执行图书借阅操作
			bookdao.borrowBook(bid, admin);
			// 根据show参数决定重定向的页面
			if (show == 1) {
				response.sendRedirect("/books/select.jsp");
			} else {
				response.sendRedirect("/books/bdtimes.jsp");
			}
		} else {
			// 归还图书操作
			// 获取借阅记录ID参数
			int hid = Integer.parseInt(request.getParameter("hid"));
			// 获取是否显示在首页的参数
			int show = Integer.parseInt(request.getParameter("show"));
			// 调用BookDao的borrowBook2方法，执行图书归还操作
			bookdao.borrowBook2(hid);
			// 根据show参数决定重定向的页面
			if (show == 1) {
				response.sendRedirect("/books/borrow.jsp");
			} else {
				response.sendRedirect("/books/admin_borrow.jsp");
			}
		}
	}

	/**
	 * 处理POST请求的方法。
	 * 本Servlet主要处理GET请求，POST请求的处理方法为空，直接调用GET方法。
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}