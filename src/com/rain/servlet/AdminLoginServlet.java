package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rain.bean.AdminBean;
import com.rain.dao.AdminDao;

/**
 * Servlet实现类AdminLoginServlet，用于处理管理员登录请求。
 * 这段代码是一个Servlet，它的作用是处理管理员的登录请求。
 * 它通过AdminDao类与数据库进行交互，验证提交的用户名和密码是否匹配。
 * 如果验证成功，将用户重定向到管理员主页；
 * 如果验证失败，则将错误信息设置到会话中，并重定向回登录页面。
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数。
	 */
	public AdminLoginServlet() {
		super();
		// 构造函数中暂无代码，仅为了满足Servlet规范。
	}

	// 创建AdminDao实例，用于数据库操作
	AdminDao adminDao = new AdminDao();

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
	 * 从请求中获取管理员登录信息，并验证管理员身份。
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求和响应的编码类型为UTF-8，以支持国际化字符。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 从请求中获取用户提交的登录信息。
		String username = request.getParameter("username"); // 获取用户名
		String password = request.getParameter("password"); // 获取密码

		// 使用AdminDao验证管理员信息
		AdminBean adminBean = adminDao.getAdminInfo(username, password);

		// 判断管理员信息是否有效
		if (adminBean != null) {
			// 如果管理员信息有效，重定向到管理员主页
			response.sendRedirect("/books/admin_admin.jsp");
		} else {
			// 如果管理员信息无效，设置错误信息到会话，并重定向到登录页面
			HttpSession session = request.getSession();
			session.setAttribute("state", "用户名或密码错误");
			response.sendRedirect("/books/admin_login.jsp");
		}
	}
}