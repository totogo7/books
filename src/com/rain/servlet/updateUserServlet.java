package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.AdminDao;

/**
 * Servlet实现类updateUserServlet，用于处理更新用户信息的请求。
 * 这段代码是一个Servlet，它的作用是处理更新用户信息的请求。
 * 它通过AdminDao类与数据库进行交互，根据提交的用户信息执行更新操作，
 * 并将用户重定向到用户管理页面。这个Servlet主要处理POST请求，GET请求将不会被处理。
 */
@WebServlet("/updateUserServlet")
public class updateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数。
	 */
	public updateUserServlet() {
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
	 * 从请求中获取更新用户信息的数据，并调用AdminDao的updateUser方法更新用户信息。
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求和响应的编码类型为UTF-8，以支持国际化字符。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 从请求中获取用户信息更新所需的参数。
		String username = request.getParameter("username"); // 获取用户名
		String password = request.getParameter("password"); // 获取密码
		String name = request.getParameter("name"); // 获取姓名
		String email = request.getParameter("email"); // 获取电子邮件
		String phone = request.getParameter("phone"); // 获取电话
		int lend_num = Integer.parseInt(request.getParameter("lend_num")); // 获取已借书数量
		int max_num = Integer.parseInt(request.getParameter("max_num")); // 获取最大借书数量
		int aid = Integer.parseInt(request.getParameter("aid")); // 获取用户ID

		// 创建AdminDao实例，用于数据库操作。
		AdminDao userdao = new AdminDao();

		// 调用AdminDao的updateUser方法，根据用户ID更新用户信息。
		userdao.updateUser(aid, username, password, name, email, phone, lend_num, max_num);

		// 更新用户信息后，重定向到用户管理页面。
		response.sendRedirect("/books/admin_user.jsp");
	}
}