package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.ProblemDao;

/**
 * Servlet实现类updateProblemServlet，用于处理更新问题反馈状态的请求。
 * 这段代码是一个Servlet，它的作用是处理更新问题反馈状态的请求。
 * 它通过ProblemDao类与数据库进行交互，根据提交的问题反馈状态执行更新操作，
 * 并将用户重定向到问题反馈管理页面。这个Servlet主要处理POST请求，GET请求将不会被处理。
 */
@WebServlet("/updateProblemServlet")
public class updateProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数。
	 */
	public updateProblemServlet() {
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
	 * 从请求中获取更新问题反馈状态的数据，并调用ProblemDao的updateProblem方法更新问题反馈状态。
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求和响应的编码类型为UTF-8，以支持国际化字符。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 从请求中获取问题反馈状态更新所需的参数。
		String status = request.getParameter("status"); // 获取问题反馈的新状态
		int pid = Integer.parseInt(request.getParameter("pid")); // 获取问题反馈ID

		// 创建ProblemDao实例，用于数据库操作。
		ProblemDao problemdao = new ProblemDao();

		// 调用ProblemDao的updateProblem方法，根据问题反馈ID更新问题反馈状态。
		problemdao.updateProblem(pid, status);

		// 更新问题反馈状态后，重定向到问题反馈管理页面。
		response.sendRedirect("/books/admin_feedback.jsp");
	}
}