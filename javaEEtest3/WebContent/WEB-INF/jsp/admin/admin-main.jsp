<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

body {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	margin: 0px auto;
	height: auto;
	width: 800px;
	border: 1px solid #006633;
}

#header {
	height: 90px;
	width: 800px;
	background-image: url(images/admin/bb.jpg);
	margin: 0px 0px 3px 0px;
}

#header h1 {
	text-align: center;
	font-family: 华文彩云;
	color: #000000;
	font-size: 30px；
}

#navigator {
	height: 25px;
	width: 800px;
	font-size: 14px;
	background-image: url(images/admin/bb.jpg);
}
#navigator ul {
	list-style-type: none;
}
#navigator li {
	float: left;
	position: relative;
}
#navigator li a {
	color: #000000;
	text-decoration: none;
	padding-top: 4px;
	display: block;
	width: 98px;
	height: 22px;
	text-align: center;
	background-color: PaleGreen;
	margin-left: 2px;
}
#navigator li a:hover {
	background-color: #006633;
	color: #FFFFFF;
}
#navigator ul li ul {
   visibility: hidden;
   position: absolute;
}

#navigator ul li:hover ul,
#navigator ul a:hover ul{
   visibility: visible;
}

#content {
	height: auto;
	width: 780px;
	padding: 10px;
}

#content iframe {
	height: 300px;
	width: 780px;
}

#footer {
	height: 30px;
	width: 780px;
	line-height: 2em;
	text-align: center;
	background-color: PaleGreen;
	padding: 10px;
}
</style>

	
</head>
<body>
<div id="header">
		<br>
		<br>
		<h1>欢迎${admindetail.adminName }进入后台管理系统！</h1>
		
	</div>
	<div id="navigator">
		<ul>
			<li><a>用户管理</a>
				<ul>
					<li><a href="${pageContext.request.contextPath }/adminUse/toAddUse" target="center">添加账户</a></li>
					<li><a href="${pageContext.request.contextPath }/adminUse/selectUse?act=deleteSelect" target="center">删除账户</a></li>
					<li><a href="${pageContext.request.contextPath }/adminUse/selectUse?act=updateSelect" target="center">修改账户</a></li>
					<li><a href="${pageContext.request.contextPath }/adminUse/selectUse" target="center">查询所有账户</a></li>
				</ul>
			</li>
			<li><a>套餐管理</a>
				<ul>
					<li><a href="${pageContext.request.contextPath }/Adminmoneysearch" target="center">查询套餐</a></li>
					<li><a href="#" target="center">修改套餐</a></li>
				</ul>
			</li>
			<li><a>审核上传文件</a>
				<ul>
					<li><a href="#" target="center">查询上传文件</a></li>
					<li><a href="#" target="center">删除不合法文件</a></li>
				</ul>
			</li>
			<li><a>服务器文件管理</a>
				<ul>
					<li><a href="#" target="center">删除文件</a></li>
				</ul>
			</li>
			<li><a>管理员信息管理</a>
				<ul>
				
					<li><a href="#" target="center">添加管理员</a></li>
					<li><a href="#" target="center">更新管理员信息</a></li>
					<li><a href="#" target="center">删除管理员信息</a></li>
				</ul>
			</li>
			<li><a>用户留言管理</a>
				<ul>
					<li><a href="#" target="center">查询所有留言</a></li>
					<li><a href="#" target="center">删除留言</a></li>
					
				</ul>
			</li>
			<li><a href="${pageContext.request.contextPath }/admindetail/logout">安全退出</a></li>
		</ul>
		
	</div>
	<div id="content">
		
		<a href="${pageContext.request.contextPath }/adminUse/selectUse">查看</a>
	</div>
	<div id="content">
		<iframe src="admin/selectUse"  name="center" frameborder="0"></iframe>
	</div>
	
</body>
</html>