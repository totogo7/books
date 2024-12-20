<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath() + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN" class="bootstrap-admin-vertical-centered">
<head>
<base href="<%=basePath%>" />
<meta charset="UTF-8">
<title>图书馆管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">
<script src="static/js/bootstrap.min.js"></script>
<script src="static/jQuery/jquery-3.1.1.min.js"></script>
<script src="static/js/login.js"></script>
<style>
body {
	background-image: url("2.jpg");
}
</style>
</head>
<body class="bootstrap-admin-without-padding">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="alert alert-info">
					<a class="close" data-dismiss="alert" href="#">&times;</a>
					欢迎登录图书馆管理系统
				</div>
				<form class="bootstrap-admin-login-form" method="post"
					action="/books/LoginServlet">
					<%
						String state = (String) session.getAttribute("state");
						session.removeAttribute("state");
						if (state != null) {
					%>
					<label class="control-label" for="username">输入有误！请重试</label>
					<% } %>
					<div class="form-group">
						<label class="control-label" for="username">账&nbsp;号</label> <input
							type="text" class="form-control" id="username" name="username"
							required="required" placeholder="学号/工号" />
					</div>
					<div class="form-group">
						<label class="control-label" for="password">密&nbsp;码</label> <input
							type="password" class="form-control" id="password"
							name="password" required="required" placeholder="密码" />
					</div>
					<label class="control-label" for="password">没有账号请<a
						href="/books/register.jsp" style="color: blue;">注册</a></label><br>
					<input type="submit" class="btn btn-lg btn-primary"
						value="登&nbsp;&nbsp;&nbsp;&nbsp;录" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>