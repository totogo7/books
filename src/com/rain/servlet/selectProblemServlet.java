package com.rain.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.bean.ProblemBean;
import com.rain.dao.ProblemDao;

/**
 * Servlet实现类selectProblemServlet，用于处理查询问题反馈的请求。
 * 这段代码是一个Servlet，它的作用是处理查询问题反馈的请求。
 * 它通过ProblemDao类与数据库进行交互，根据提交的参数查询匹配的问题反馈列表，
 * 并将结果设置到request属性中，以便在JSP页面中展示。
 * 然后，它将请求转发到问题反馈管理页面。
 * 
 */
@WebServlet("/selectProblemServlet")
public class selectProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数。
	 */
	public selectProblemServlet() {
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
	 * 根据请求参数执行查询问题反馈的操作。
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求和响应的编码类型为UTF-8，以支持国际化字符。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 从请求中获取查询参数name，用于模糊查询问题反馈。
		String name = request.getParameter("name");

		// 创建ProblemDao实例，用于数据库操作。
		ProblemDao problemdao = new ProblemDao();

		// 调用ProblemDao的getLikeList方法，根据name参数查询匹配的问题反馈列表。
		ArrayList<ProblemBean> data = problemdao.getLikeList(name);

		// 将查询结果设置到request属性中，以便在JSP页面中使用。
		request.setAttribute("data", data);

		// 确定要跳转的JSP页面URL。
		String url = response.encodeURL("admin_feedback.jsp");

		// 使用RequestDispatcher将请求转发到指定的JSP页面。
		request.getRequestDispatcher(url).forward(request, response);
	}
}