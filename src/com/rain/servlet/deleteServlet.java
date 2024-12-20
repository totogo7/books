package com.rain.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.BookDao;

/**
 * Servlet实现类deleteServlet，用于处理删除图书的请求。
 * 这段代码是一个Servlet，它的作用是处理删除图书的请求。
 * 它通过BookDao类与数据库进行交互，根据提交的图书ID执行删除操作，
 * 并将用户重定向到图书管理页面。
 * 这个Servlet主要处理GET请求，POST请求将直接调用GET方法进行处理。
 */
@WebServlet("/deleteServlet")
public class deleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数。
	 */
	public deleteServlet() {
		super();
		// 构造函数中暂无代码，仅为了满足Servlet规范。
	}

	/**
	 * 处理GET请求的方法。
	 * 根据请求参数执行删除图书的操作。
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求和响应的编码类型为UTF-8，以支持国际化字符。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 从请求中获取要删除的图书ID参数。
		int bid = Integer.parseInt(request.getParameter("bid"));

		// 创建BookDao实例，用于数据库操作。
		BookDao bookdao = new BookDao();

		// 调用BookDao的deleteBook方法，根据图书ID删除图书。
		bookdao.deleteBook(bid);

		// 删除图书后，重定向到图书管理页面。
		response.sendRedirect("/books/admin_book.jsp");
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