<%@ page language="java" contentType="text/html;"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%pageContext.setAttribute("path",request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()); %>
<%@ page import="java.lang.String,cn.bus.entity.*,java.util.List "%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>全天在用时间轴</title>
	<link href="${path}/css/css.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${path}/js/jquery1.10.2.js"></script>
    <link href="${path}/layui/css/layui.css" rel="stylesheet"  media="all"/>
	<script type="text/javascript" src="${path}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/layui/layui.js"></script>
	<script type="text/javascript" src="${path}/layui/lay/modules/jquery.js"></script>
	<script type="text/javascript" src="${path}/layui/lay/modules/layer.js"></script>
	<script type="text/javascript" src="${path}/layui/lay/modules/form.js"></script>
	<script type="text/javascript" src="${path}/layui/layui.all.js"></script>
	<style>
		#div1{
			margin-top: 10px;
			width: 90%;
			border-bottom: 5px solid red;
		}
		#div2{
			margin-top: 10px;
			width: 90%;
			border-bottom: 5px solid green;
		}
	</style>
<script type="text/javascript">

</script>

</head>

<body style="">
<div class="course">
	<div class="clearfix web_widht course_nr" >
		<ul class="course_nr" style="height:50px;margin-left: 2%;" >
            <c:forEach items="${allTime}" var="t">
				<c:if test="${t.spareStart==null}">
					<li style="height: 40px" style="width:${t.workLength}%">
					<div class="shiji">在${t.line.line}线路运行
						<div id="div1"></div>
						<p>
						</p><p>${t.times}-${t.end}</p>
					</div>
					</li>
				</c:if>
				<c:if test="${t.spareStart!=null}">
					<li style="height: 40px" style="width:${t.spareLength}%">
					<div class="shiji">空闲
						<div id="div2" ></div>
						<p>
						</p><p>${t.spareStart}-${t.spareEnd}</p>
					</div>
				</c:if>
                </li>
            </c:forEach>
		</ul>

	</div>
</div>


</body>
</html>