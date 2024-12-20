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
import com.rain.dao.ProblemDao;

/**
 * Servlet实现类AddProblemServlet，用于处理添加问题反馈的请求。
 * 这段代码是一个Servlet，它的作用是处理来自表单的POST请求，
 * 将用户的问题反馈信息添加到数据库中。
 * 它通过ProblemDao类与数据库进行交互，
 * 将用户提交的姓名、页面、反馈内容和电话存储到数据库中。
 * 同时，它还从会话中获取当前登录管理员的信息，并将其与问题反馈关联。
 * 处理完毕后，它将用户重定向到结果页面。
 */
@WebServlet("/AddProblemServlet")
public class AddProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数。
	 */
	public AddProblemServlet() {
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
	 * 从请求中获取问题反馈数据，并调用ProblemDao的addProblem方法将问题反馈信息添加到数据库。
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求和响应的编码类型为UTF-8，以支持国际化字符。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 获取当前会话对象
		HttpSession session = request.getSession();
		// 创建AdminBean对象，用于存储管理员信息
		AdminBean adminbean = new AdminBean();
		// 从会话中获取管理员的ID
		String aid = (String) session.getAttribute("aid");
		// 创建AdminDao对象，用于数据库操作
		AdminDao admindao = new AdminDao();
		// 根据aid获取管理员的详细信息
		adminbean = admindao.get_AidInfo2(aid);

		// 从请求中获取用户提交的问题反馈数据
		String name = request.getParameter("name"); // 获取反馈者姓名
		String page = request.getParameter("page"); // 获取反馈页面
		String body = request.getParameter("body"); // 获取反馈内容
		String phone = request.getParameter("phone"); // 获取反馈者电话

		// 创建ProblemDao对象，用于数据库操作
		ProblemDao problemdao = new ProblemDao();
		// 调用ProblemDao的addProblem方法，将问题反馈信息添加到数据库
		problemdao.addProblem(adminbean, name, page, body, phone);

		// 重定向到结果页面
		response.sendRedirect("/books/result.jsp");
	}
}