package com.rain.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.AdminDao;

/**
 * Servlet实现类RegisterServlet，用于处理用户注册的请求。
 * 这段代码是一个Servlet，它的作用是处理用户的注册请求。
 * 它通过AdminDao类与数据库进行交互，将用户提交的注册信息存储到数据库中。
 * 注册成功后，用户会被重定向到登录页面。
 * 这个Servlet主要处理POST请求，GET请求将不会被处理。
 * 
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数。
	 */
	public RegisterServlet() {
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
	 * 从请求中获取用户注册信息，并调用AdminDao的Register方法将新用户信息添加到数据库。
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求和响应的编码类型为UTF-8，以支持国际化字符。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 从请求中获取用户提交的注册信息。
		String username = request.getParameter("username"); // 获取用户名
		String password = request.getParameter("password"); // 获取密码
		String name = request.getParameter("name"); // 获取姓名
		String email = request.getParameter("email"); // 获取电子邮件
		String phone = request.getParameter("phone"); // 获取电话

		// 设置默认的借书数量和最大借书数量。
		int lend_num = 30; // 默认借书数量
		int max_num = 5;   // 默认最大借书数量

		// 创建AdminDao实例，用于数据库操作。
		AdminDao userdao = new AdminDao();

		// 调用AdminDao的Register方法，将新用户信息添加到数据库。
		userdao.Register(username, password, name, email, phone, lend_num, max_num);

		// 注册成功后，重定向到登录页面。
		response.sendRedirect("/books/login.jsp");
	}
}