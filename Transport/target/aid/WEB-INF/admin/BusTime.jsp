<%@ page language="java" contentType="text/html;"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%pageContext.setAttribute("path",request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>车辆维修信息</title>
    <link href="${path}/layui/css/layui.css" rel="stylesheet"  media="all"/>
	<script type="text/javascript" src="${path}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/layui/layui.js"></script>
	<script type="text/javascript" src="${path}/layui/lay/modules/jquery.js"></script>
	<script type="text/javascript" src="${path}/layui/lay/modules/layer.js"></script>
	<script type="text/javascript" src="${path}/layui/lay/modules/form.js"></script>
	<script type="text/javascript" src="${path}/layui/layui.all.js"></script>

<script type="text/javascript">
    $(function(){
        $("#bid-add").blur(function () {//新增车牌唯一
            var bid=$("#bid-add").val();
            $.ajax({
                url:"${path}/bus/checkBid.action",
                type:"post",
                data:{"bid":bid},
                dataType:"text",
                success:(function (res) {
                    if(res=="1"){
                        alert("该车牌已存在");
                    }
                })
            })
        })
    });

</script>
	<style type="text/css">
		.d1{
			width:2px;
			height:2px;
			border:1ch solid #000;
			border-radius:50%;
			box-shadow:0px 0px 20px red;
		}
		.d2{float: left;}
		.layui-timeline-item{float: left;}
	</style>
</head>

<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
	<legend>常规时间线</legend>
</fieldset>
<ul class="layui-timeline">
	<li class="d2">
		<div class="d1"></div>
		<hr style="width: 5%;height: 2px">
	</li>
	<li class="layui-timeline-item">
		<i class="layui-icon layui-timeline-axis"></i>
		<div class="layui-timeline-content layui-text">
			<h3 class="layui-timeline-title">8月16日</h3>
			<p>杜甫的思想核心是儒家的仁政思想，他有<em>“致君尧舜上，再使风俗淳”</em>的宏伟抱负。个人最爱的名篇有：</p>
			<ul>
				<li>《登高》</li>
				<li>《茅屋为秋风所破歌》</li>
			</ul>
		</div>
	</li>
	<li class="layui-timeline-item">
		<i class="layui-icon layui-timeline-axis"></i>
		<div class="layui-timeline-content layui-text">
			<h3 class="layui-timeline-title">8月15日</h3>
			<p>
				中国人民抗日战争胜利日
				<br>常常在想，尽管对这个国家有这样那样的抱怨，但我们的确生在了最好的时代
				<br>铭记、感恩
				<br>所有为中华民族浴血奋战的英雄将士
				<br>永垂不朽
			</p>
		</div>
	</li>
	<li class="layui-timeline-item">
		<i class="layui-icon layui-timeline-axis"></i>
		<div class="layui-timeline-content layui-text">
			<div class="layui-timeline-title">过去</div>
		</div>
	</li>
</ul>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
	<legend>简约时间线：大事记</legend>
</fieldset>
<ul class="layui-timeline">
	<li class="layui-timeline-item">
		<i class="layui-icon layui-timeline-axis"></i>
		<div class="layui-timeline-content layui-text">
			<div class="layui-timeline-title">2018年，layui 5.0 发布。并发展成为中国最受欢迎的前端UI框架（期望）</div>
		</div>
	</li>
	<li class="layui-timeline-item">
		<i class="layui-icon layui-timeline-axis"></i>
		<div class="layui-timeline-content layui-text">
			<div class="layui-timeline-title">2017年，layui 里程碑版本 2.0 发布</div>
		</div>
	</li>
	<li class="layui-timeline-item">
		<i class="layui-icon layui-timeline-axis"></i>
		<div class="layui-timeline-content layui-text">
			<div class="layui-timeline-title">2016年，layui 首个版本发布</div>
		</div>
	</li>
	<li class="layui-timeline-item">
		<i class="layui-icon layui-timeline-axis"></i>
		<div class="layui-timeline-content layui-text">
			<div class="layui-timeline-title">2015年，layui 孵化</div>
		</div>
	</li>
	<li class="layui-timeline-item">
		<i class="layui-icon layui-anim layui-anim-rotate layui-anim-loop layui-timeline-axis"></i>
		<div class="layui-timeline-content layui-text">
			<div class="layui-timeline-title">更久前，轮子时代。维护几个独立组件：layer等</div>
		</div>
	</li>
</ul>

</body>
</html>