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
 * Servlet实现类updateBookServlet，用于处理更新图书信息的请求。
 * 这段代码是一个Servlet，它的作用是处理更新图书信息的请求。
 * 它通过BookDao类与数据库进行交互，根据提交的图书信息执行更新操作，
 * 并将用户重定向到图书管理页面。
 * 这个Servlet主要处理POST请求，GET请求将不会被处理。
 */
@WebServlet("/updateBookServlet")
public class updateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数。
	 */
	public updateBookServlet() {
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
	 * 从请求中获取更新图书信息的数据，并调用BookDao的updateBook方法更新图书信息。
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求和响应的编码类型为UTF-8，以支持国际化字符。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 从请求中获取图书信息更新所需的参数。
		String card = request.getParameter("card"); // 获取借书证号
		String name = request.getParameter("name"); // 获取图书名称
		String type = request.getParameter("type"); // 获取图书类型
		String autho = request.getParameter("autho"); // 获取作者
		String press = request.getParameter("press"); // 获取出版社
		int num = Integer.parseInt(request.getParameter("num")); // 获取图书数量
		int bid = Integer.parseInt(request.getParameter("updatebid")); // 获取图书ID

		// 创建BookDao实例，用于数据库操作。
		BookDao bookdao = new BookDao();

		// 调用BookDao的updateBook方法，根据图书ID更新图书信息。
		bookdao.updateBook(bid, card, name, type, autho, press, num);

		// 更新图书信息后，重定向到图书管理页面。
		response.sendRedirect("/books/admin_book.jsp");
	}
}