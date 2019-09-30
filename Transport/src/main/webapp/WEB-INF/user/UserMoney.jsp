<%@ page language="java" contentType="text/html;"
    pageEncoding="utf-8"%>
<%@ page import="cn.bus.entity.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%pageContext.setAttribute("path",request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
	<title>我的钱包</title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
    <link href="${path}/layui/css/layui.css" rel="stylesheet"  media="all"/>
	<script type="text/javascript" src="${path}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/layui/layui.js"></script>
	<script type="text/javascript" src="${path}/layui/lay/modules/jquery.js"></script>
	<script type="text/javascript" src="${path}/layui/lay/modules/layer.js"></script>
	<script type="text/javascript" src="${path}/layui/lay/modules/form.js"></script>
	<script type="text/javascript" src="${path}/layui/layui.all.js"></script>
    <script>
		function check()
		{
			var uname=$("#cname").val();
			var upwd=$("#cpwd").val();
			var unameTest=/^[a-zA-Z][a-zA-Z0-9]{1,5}$/;
			if(unameTest.test(uname)==false)
			{
				alert("用户名格式错误,字母开头，长度2至6位");
				return false;
			}

			
			return true;
		}
		$(function(){
			$("#back").click(function(){
				location.href="Login.jsp";
			})

		})
	</script>
</head>

<body>

<form class="layui-form" style="margin-left: 25%;margin-right: 25%;" action="${path}/user/addMoney.action" method="post">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>充值</legend>
    </fieldset>
	<div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">账户余额</label>
            <div class="layui-input-inline">
                <input type="text" name="number" readonly="readonly" value="${user.fee}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">金额</label>
            <div class="layui-input-inline">
                <input type="text"  id="WIDtotal_amount" name="WIDtotal_amount" lay-verify="required"  class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 150px">扫描二维码充值</label>
        </div>
        <input type="hidden" id="WIDout_trade_no" name="WIDout_trade_no" />
        <input type="hidden" id="WIDsubject" name="WIDsubject" />
        <input type="hidden" id="WIDbody" name="WIDbody" value="描述"/>
	</div>
    <button type="submit" class="layui-btn" style="margin-left: 15%" id="charge">充值</button>
</form>
<script language="javascript">
    var tabs = document.getElementsByName('tab');
    var contents = document.getElementsByName('divcontent');

    function GetDateNow() {
        var vNow = new Date();
        var sNow = "";
        sNow += String(vNow.getFullYear());
        sNow += String(vNow.getMonth() + 1);
        sNow += String(vNow.getDate());
        sNow += String(vNow.getHours());
        sNow += String(vNow.getMinutes());
        sNow += String(vNow.getSeconds());
        sNow += String(vNow.getMilliseconds());
        document.getElementById("WIDout_trade_no").value =  sNow;
        document.getElementById("WIDsubject").value = "测试";
        document.getElementById("WIDtotal_amount").value = "0.01";
    }
    GetDateNow();
</script>
</body>


</html>