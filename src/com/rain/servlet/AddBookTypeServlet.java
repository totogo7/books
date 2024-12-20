package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.BookDao;
import com.rain.dao.TypeDao;

/**
 * Servlet实现类AddBookTypeServlet，用于处理添加新图书类型的请求。
 * 这段代码是一个Servlet，它的作用是处理来自表单的POST请求，
 * 将新图书类型的信息添加到数据库中。
 * 它通过TypeDao类与数据库进行交互，将用户提交的图书类型名称存储到数据库中。
 * 处理完毕后，它将用户重定向到图书类型管理页面。
 */
@WebServlet("/AddBookTypeServlet")
public class AddBookTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数。
	 */
	public AddBookTypeServlet() {
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
	 * 从请求中获取图书类型的数据，并调用TypeDao的addBookType方法将新图书类型信息添加到数据库。
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求和响应的编码类型为UTF-8，以支持国际化字符。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 从请求中获取用户提交的图书类型数据。
		String name = request.getParameter("name"); // 获取图书类型名称

		// 创建TypeDao实例，用于数据库操作。
		TypeDao typedao = new TypeDao();

		// 调用TypeDao的addBookType方法，将新图书类型信息添加到数据库。
		typedao.addBookType(name);

		// 重定向到图书类型管理页面。
		response.sendRedirect("/books/admin_booktype.jsp");
	}
}