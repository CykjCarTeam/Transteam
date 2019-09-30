
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用戶信息</title>
    <link rel="stylesheet" href="<%=basePath%>css/font.css">
    <link rel="stylesheet" href="<%=basePath%>css/xadmin.css">
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/xadmin.js" charset="utf-8"></script>
    <%--    <script type="text/javascript" src="<%=basePath%>js/xa.js"></script>--%>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header" align="right">
                    <button class="layui-btn" onclick="xadmin.open('新增角色','<%=basePath%>adminHandle/interface.action?url=addrole',300,300)">
                        <i class="layui-icon"></i>新增角色</button>
                </div>
                <div class="layui-card-body" style="width:600px;margin:auto">
                    <table class="layui-table" lay-filter="test" id="demo" align="center">
                        <tr align="center">
                            <td>角色ID</td>
                            <td>角色名称</td>
                            <td>编辑</td>
                        </tr>
                        <c:forEach items="${list}" var="i">
                            <tr align="center" id="rid">
                                <td>${i.rid}</td>
                                <td>${i.role}</td>
                                <td>
                                    <div class = "layui-btn-container" >
                                        <button class="layui-btn layui-btn layui-btn-xs"  onclick="xadmin.open('修改','<%=basePath%>adminHandle/interface.action?url=part&rid=${i.rid}&role=${i.role}',300,300)" >
                                            <i class="layui-icon">&#xe642;</i>编辑</button>
                                        <a title="删除" class="layui-btn-danger layui-btn layui-btn-xs" onclick="dele(${i.rid})">
                                            <i class="layui-icon">&#xe640;</i></a>
                                        <button class="layui-btn layui-btn-warm layui-btn-xs"  onclick="xadmin.open('配置权限','<%=basePath%>adminHandle/interface.action?url=enroll',500,390)" >
                                            <i class="layui-icon">&#xe642;</i>配置权限</button>
                                    </div >
                                </td>

                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>



<script>
    function dele (rid) {
        $.ajax({
            url: "/aid_war_exploded/adminHandle/delerole.action",
            type: "POST",
            data:{"rid":rid},
            dataType: "text",
            success : function(res){
                // if(res=="1"){
                //     layer.msg("删除成功");
                // }else{
                //     layer.msg("删除失败");
                // }
                window.location.reload();
            }
        });
    }
</script>
</div>
</body>
</html>

