package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.AdminDao;

/**
 * Servlet实现类AddUserServlet，用于处理添加新用户的请求。
 * 这段代码是一个Servlet，它的作用是处理来自表单的POST请求，
 * 将新用户的信息添加到数据库中。它通过AdminDao类与数据库进行交互，
 * 将用户提交的用户名、密码、姓名、电子邮件、电话、借书数量和最大借书数量存储到数据库中。
 * 处理完毕后，它将用户重定向到管理员用户列表页面。
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数。
	 */
	public AddUserServlet() {
		super();
		// 构造函数中暂无代码，仅为了满足Servlet规范。
	}

	/**
	 * 处理GET请求的方法。
	 * 由于本Servlet主要处理POST请求，GET请求的处理方法为空。
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 暂无实现，GET请求不被处理。
	}

	/**
	 * 处理POST请求的方法。
	 * 从请求中获取用户提交的数据，并调用AdminDao的Register方法将新用户信息添加到数据库。
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求和响应的编码类型为UTF-8，以支持国际化字符。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 从请求中获取用户提交的数据。
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		int lend_num = Integer.parseInt(request.getParameter("lend_num"));
		int max_num = Integer.parseInt(request.getParameter("max_num"));

		// 创建AdminDao实例，用于数据库操作。
		AdminDao userdao = new AdminDao();

		// 调用AdminDao的Register方法，将新用户信息添加到数据库。
		userdao.Register(username, password, name, email, phone, lend_num, max_num);

		// 重定向到管理员用户列表页面。
		response.sendRedirect("/books/admin_user.jsp");
	}
}