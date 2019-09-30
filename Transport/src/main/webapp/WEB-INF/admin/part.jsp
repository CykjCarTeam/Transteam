<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>角色修改</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="<%=basePath%>css/font.css">
    <link rel="stylesheet" href="<%=basePath%>css/xadmin.css">
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=basePath%>lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/enroll.js"></script>
</head>
<body>
<div class="x-body">
        <form class="layui-form">

<div class="layui-form-item">
    <p></p>
    <label class="layui-form-label" >
        <span class="x-red">*</span>角色ID
    </label>
    <div class="layui-input-inline" style="width:120px" align="cener">
        <input type="text" name="rid" placeholder="${rid}" disabled autocomplete="off" class="layui-input">
    </div>
</div>


    <div class="layui-form-item">
        <label for="role" class="layui-form-label">
            <span class="x-red">*</span>角色名称
        </label>
        <div class="layui-input-inline" style="width:120px">
            <input type="text" id="role" name="role" required="" lay-verify="required"
                   autocomplete="off"  class="layui-input" onblur="check()">
        </div>
    </div>
            <p></p>

    <div class="layui-form-item" align="center">
        <button class="layui-btn" lay-filter="save" lay-submit="">修改</button>
        <button class="layui-btn" lay-submit="" lay-filter="add" onclick="xadmin.close()">返回</button>
    </div>

        </form>
</div>

<script>
    layui.use(['form','layer'], function(){
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer;

        form.on('submit(save)', function(data){
            var rid=${rid};
            var role=$("#role").val();
            console.log(data);
            $.ajax({
                url: "/aid_war_exploded/adminHandle/changerid.action",
                type: "POST",
                data: {"rid": rid,
                    "role":role
                },
                dataType: "text",
            });
            //发异步，把数据提交给php
            layer.alert("修改成功", {icon: 6},function () {
                // 获得frame索引
                var index = parent.layer.getFrameIndex(window.name);
                //关闭当前frame
                parent.location.reload();
                parent.layer.close(index);
            });
            return false;
        });
    });
function check() {
    var role=$("#role").val();
    if(null==role||role==""){
        layer.msg("角色不为空");
        return false;
    }else{
        $.ajax({
            url: "/aid_war_exploded/adminHandle/roleajax.action",
            type: "POST",
            data:{"role":role},
            dataType: "text",
            success : function(res){
                if(res=="1"){
                    layer.msg("角色已存在");
                }else{
                    layer.msg("允许新增");
                }
            }
        });
    }};
</script>



</body>
</html>
