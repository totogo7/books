package com.rain.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.bean.BookBean;
import com.rain.dao.BookDao;

/**
 * Servlet实现类selectServlet，用于处理图书选择和查询的请求。
 * 这段代码是一个Servlet，它的作用是处理图书查询的请求。
 * 它通过BookDao类与数据库进行交互，根据提交的参数查询匹配的图书列表，
 * 并将结果设置到request属性中，以便在JSP页面中展示。
 * 根据操作类型，它将请求转发到不同的JSP页面。
 */
@WebServlet("/selectServlet")
public class selectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数。
	 */
	public selectServlet() {
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
	 * 根据请求参数执行图书查询的操作，并根据结果进行页面跳转。
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求和响应的编码类型为UTF-8，以支持国际化字符。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 获取请求参数tip，用于判断查询的图书类型。
		int tip = Integer.parseInt(request.getParameter("tip"));
		// 获取请求参数name，用于模糊查询图书名称。
		String name = request.getParameter("name");

		// 创建BookDao实例，用于数据库操作。
		BookDao bookdao = new BookDao();
		// 调用BookDao的getLikeList方法，根据name参数查询匹配的图书列表。
		ArrayList<BookBean> data = bookdao.getLikeList(name);

		// 将查询结果设置到request属性中，以便在JSP页面中使用。
		request.setAttribute("data", data);

		// 根据tip参数确定要跳转的JSP页面。
		String url = "";
		if (tip == 1) {
			// 如果tip为1，跳转到管理员图书管理页面。
			url = response.encodeURL("admin_book.jsp");
		} else {
			// 否则，跳转到普通图书查询结果页面。
			url = response.encodeURL("select.jsp");
		}

		// 使用RequestDispatcher将请求转发到指定的JSP页面。
		request.getRequestDispatcher(url).forward(request, response);
	}
}