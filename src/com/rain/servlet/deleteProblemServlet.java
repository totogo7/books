package com.rain.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.ProblemDao;

/**
 * Servlet实现类deleteServlet，用于处理删除问题反馈的请求。
 * 这段代码是一个Servlet，它的作用是处理删除问题反馈的请求。
 * 它通过ProblemDao类与数据库进行交互，根据提交的问题反馈ID执行删除操作，
 * 并将用户重定向到问题反馈列表页面。
 * 这个Servlet主要处理GET请求，POST请求将直接调用GET方法进行处理。
 * 
 */
@WebServlet("/deleteProblemServlet")
public class deleteProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数。
	 */
	public deleteProblemServlet() {
		super();
		// 构造函数中暂无代码，仅为了满足Servlet规范。
	}

	/**
	 * 处理GET请求的方法。
	 * 根据请求参数执行删除问题反馈的操作。
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求和响应的编码类型为UTF-8，以支持国际化字符。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 从请求中获取要删除的问题反馈ID参数。
		int pid = Integer.parseInt(request.getParameter("pid"));

		// 创建ProblemDao实例，用于数据库操作。
		ProblemDao problemdao = new ProblemDao();

		// 调用ProblemDao的deleteProblem方法，根据问题反馈ID删除问题反馈。
		problemdao.deleteProblem(pid);

		// 删除问题反馈后，重定向到问题反馈列表页面。
		response.sendRedirect("/books/admin_feedback.jsp");
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