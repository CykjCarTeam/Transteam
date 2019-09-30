
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.2</title>
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

    <!--[if lt IE 9]>
    <![endif]--></head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form" id="ddd" enctype="multipart/form-data">
            <div class="layui-upload">
                <label class="layui-form-label">
                    <span class="x-red">*</span>用户头像</label>
                <div class="layui-input-inline">
                    <div class="site-demo-upbar">
                        <input type="file" name="fileact" id="fileact">
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label for="rid" class="layui-form-label">
                    <span class="x-red">*</span>角色名</label>
                <div class="layui-input-inline">
                    <select id="rid" name="rid">
                        <option value="">请选择角色</option>
                        <c:forEach items="${list}" var="i">
                            <option value="${i.rid}">${i.role}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label for="anum" class="layui-form-label">
                    <span class="x-red">*</span>用户账户</label>
                <div class="layui-input-inline">
                    <input type="text" id="anum" name="anum" required="" lay-verify="required" autocomplete="off" class="layui-input" onblur="check()">
                </div>
            </div>

            <div class="layui-form-item">
                <label for="aname" class="layui-form-label">
                    <span class="x-red">*</span>用户名称</label>
                <div class="layui-input-inline">
                    <input type="text" id="aname" name="aname" required="" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label for="apwd" class="layui-form-label">
                    <span class="x-red">*</span>账户密码</label>
                <div class="layui-input-inline">
                    <input type="text" id="apwd" name="apwd" required=""  onblur="check1()" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label for="age" class="layui-form-label">
                    <span class="x-red">*</span>年龄</label>
                <div class="layui-input-inline">
                    <input type="text" id="age" name="age" required="" onblur="check2()" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class="x-red">*</span>性别</label>
                <div class="layui-input-block">
                    <input type="radio" name="sex" value="男" title="男" checked="">
                    <input type="radio" name="sex" value="女" title="女">
                </div>
            </div>

            <div class="layui-form-item">
                <label for="phone" class="layui-form-label">
                    <span class="x-red">*</span>手机</label>
                <div class="layui-input-inline">
                    <input type="text" id="phone" name="phone" onblur="check3()" required="" lay-verify="phone" autocomplete="off" class="layui-input"></div>
            </div>


            <div class="layui-form-item">
                <label for="phone" class="layui-form-label">
                    <span class="x-red">*</span>地址</label>
                <div class="layui-input-inline">
                    <input type="text" id="area" name="area" required="" lay-verify="required" autocomplete="off" class="layui-input"></div>
            </div>
            <div class="layui-form-item" align="center">
                <button class="layui-btn" lay-filter="add" lay-submit="" type="submit">注册</button>
                <button class="layui-btn" onclick="xadmin.close()">返回</button>
            </div>

</form>

</div>
</div>
<script>
    layui.use(['form', 'layer'],
    function() {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer;
        form.on('submit(add)',
            function(data) {
            var form=new FormData(document.getElementById("ddd"));
                console.log(data);
                $.ajax({
                    url: "/aid_war_exploded/adminHandle/fileact.action",
                    type: "POST",
                    data:form,
                    processData:false,
                    contentType:false,
                    dataType: "text",
                    success : function(res){
                        if(res=="1"){
                            layer.msg("账户已存在");
                        }else{
                            layer.msg("账户未存在");
                        }
                    }
                });
                layer.alert("增加成功", {
                        icon: 6
                    },
                    function() {
                        // 获得frame索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
                    });
                return false;
            });



    });

</script>

</body>

</html>