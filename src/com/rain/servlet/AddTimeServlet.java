package com.rain.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.bean.HistoryBean;
import com.rain.dao.BookDao;

/**
 * Servlet实现类AddTimeServlet，用于处理图书延期请求。
 * 这段代码是一个Servlet，它的作用是处理图书延期的请求。
 * 无论是GET请求还是POST请求，都会调用doPost方法来处理。
 * 它通过BookDao类与数据库进行交互，更新指定借阅记录的截止日期。
 * 处理完毕后，它将用户重定向到图书借阅管理页面。
 * 
 */
@WebServlet("/AddTimeServlet")
public class AddTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数。
	 */
	public AddTimeServlet() {
		super();
		// 构造函数中暂无代码，仅为了满足Servlet规范。
	}

	/**
	 * 处理GET请求的方法。
	 * 由于本Servlet主要处理POST请求，GET请求的处理方法会直接调用POST方法。
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 直接调用POST方法处理请求
		doPost(request, response);
	}

	/**
	 * 处理POST请求的方法。
	 * 从请求中获取图书延期数据，并调用BookDao的AddTime方法更新图书借阅截止日期。
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置请求和响应的编码类型为UTF-8，以支持国际化字符。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 从请求中获取用户提交的延期信息。
		String endtime = request.getParameter("endtime"); // 获取新的截止日期
		int hid = Integer.parseInt(request.getParameter("hid")); // 获取借阅记录ID

		// 创建BookDao实例，用于数据库操作。
		BookDao bookdao = new BookDao();

		// 调用BookDao的AddTime方法，更新图书的借阅截止日期。
		bookdao.AddTime(hid, endtime);

		// 重定向到图书借阅管理页面。
		response.sendRedirect("/books/admin_borrow.jsp");
	}
}