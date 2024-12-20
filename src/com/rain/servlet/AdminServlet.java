package com.rain.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rain.bean.AdminBean;
import com.rain.dao.AdminDao;
import com.rain.dao.BookDao;

/**
 * Servlet实现类AdminServlet，用于处理管理员相关操作的请求。
 * 这段代码是一个Servlet，它的作用是处理管理员信息更新的请求。
 * 根据提交的参数，它可以更新管理员的密码或其他个人信息。
 * 如果操作成功，将重定向到指定的页面；如果失败（如密码错误），
 * 则显示错误信息并返回原页面。


 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数。
	 */
	public AdminServlet() {
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
	 * 根据请求参数执行管理员信息的更新操作。
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取响应的PrintWriter对象，用于输出JavaScript代码
		PrintWriter out = response.getWriter();
		// 设置请求和响应的编码类型为UTF-8，以支持国际化字符。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 获取操作类型参数，用于判断是修改密码还是修改其他信息
		int tip = Integer.parseInt(request.getParameter("tip"));
		// 获取跳转URL参数，用于操作完成后的页面跳转
		String url = request.getParameter("url");

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

		// 根据操作类型执行不同的更新操作
		if (tip == 1) {
			// 修改密码操作
			// 获取新密码和确认密码参数
			String password = request.getParameter("password");
			String password2 = request.getParameter("password2");
			// 获取原密码
			String old_password = adminbean.getPassword();
			// 验证原密码是否正确
			if (old_password.equals(password)) {
				// 密码验证通过，更新新密码
				admindao.updateUser(adminbean.getAid(), adminbean.getUsername(), password2, adminbean.getName(),
						adminbean.getEmail(), adminbean.getPhone(), adminbean.getLend_num(), adminbean.getMax_num());
				// 密码更新后跳转到指定页面
				response.sendRedirect("/books/" + url + ".jsp");
			} else {
				// 密码验证失败，输出错误信息并跳转到原页面
				out.write("<script type='text/javascript'>alert('password error');location.href='" + url
						+ ".jsp';  </script>");
			}
		} else {
			// 修改其他信息操作
			// 获取姓名、电子邮件和电话参数
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			// 更新管理员的其他信息
			admindao.updateUser(adminbean.getAid(), adminbean.getUsername(), adminbean.getPassword(), name, email,
					phone, adminbean.getLend_num(), adminbean.getMax_num());
			// 信息更新后跳转到指定页面
			response.sendRedirect("/books/" + url + ".jsp");
		}
	}
}