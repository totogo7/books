package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.AdminDao;

/**
 * Servlet实现类AddAdminServlet，用于处理添加新管理员的请求。
 * 这段代码是一个Servlet，它的作用是处理来自表单的POST请求，将新管理员的信息添加到数据库中。
 * 它通过AdminDao类与数据库进行交互，将用户提交的用户名、密码、姓名、电子邮件和电话存储到数据库中。
 * 处理完毕后，它将用户重定向到管理员列表页面。
 */
@WebServlet("/AddAdminServlet")
public class AddAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //@WebServlet("/AddAdminServlet")是一个注解，用于声明这个Servlet的URL模式，
	//即客户端可以通过访问/AddAdminServlet来触发这个Servlet。
	//serialVersionUID是一个用于序列化的对象版本号，通常用于版本控制。
	
	
	/**
	 * 默认构造函数。
	 */
	public AddAdminServlet() {
		super();
		// 构造函数被重写以调用父类的构造函数
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
	 * 从请求中获取管理员提交的数据，并调用AdminDao的Register2方法将新管理员信息添加到数据库。
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求和响应的编码类型为UTF-8，以支持国际化字符。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 从请求中获取用户提交的数据。
		String username = request.getParameter("username"); // 获取用户名
		String password = request.getParameter("password"); // 获取密码
		String name = request.getParameter("name"); // 获取姓名
		String email = request.getParameter("email"); // 获取电子邮件
		String phone = request.getParameter("phone"); // 获取电话

		// 创建AdminDao实例，用于数据库操作。
		AdminDao userdao = new AdminDao();

		// 调用AdminDao的Register2方法，将新管理员信息添加到数据库。
		userdao.Register2(username, password, name, email, phone);

		// 重定向到管理员列表页面。
		response.sendRedirect("/books/admin_admin.jsp");
	}
}