<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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

    <script src="<%=basePath%>js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/index.css"/>
    <script src="<%=basePath%>js/index.js"></script>

    <script type="text/javascript" src="<%=basePath%>js/enroll.js"></script>
</head>
<body>
<div class="x-body">
<div class="layui-row">
    <form class="layui-form layui-col-md12  layui-form-pane">
        <p></p>
        <div class="layui-form layui-card-body ">
            <div class="demoTable" align="center">
                <div class="layui-inline layui-show-xs-block">
                    <label class="layui-form-label">角色名称</label>
                </div>
                <div class="layui-input-inline" onblur="check()">
                    <select id="rid" name="rid">
                        <option value="">请选择角色</option>
                        <c:forEach items="${list}" var="i">
                            <option class="layui-input" value="${i.rid}">${i.role}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>





    </form>
</div>

    <div class="selection-container">
        <div class="select-box left">
            <div class="select-box-title">
                <input type="checkbox" class="checkbox-all" id="checkbox-all1" />
                <label for="checkbox-all1"></label>
                <span>列表一</span>
            </div>
            <div class="select-content">
                <ul class="unselect-ul"   id="tyue-checkbox-blue2" >
<c:forEach items="${flist}" var="b">
    <li>
        <input type="checkbox" class="checkboxs"/>
        <label></label>
        <span>${b.menu}</span>
    </li>
</c:forEach>
                </ul>
            </div>
        </div>



        <div class="arrows-box">
            <div class="arrow-btns">
                <button class="arrow-btn right">
                    <svg class="icon icon-seach" aria-hidden="true">
                        <use xlink:href="#icon-com-dajiantouyou"></use>
                    </svg>
                </button>
                <button class="arrow-btn left">
                    <svg class="icon icon-seach" aria-hidden="true">
                        <use xlink:href="#icon-com-dajiantouzuo"></use>
                    </svg>
                </button>
            </div>
        </div>
        <div class="select-box right">
            <div class="select-box-title">
                <input type="checkbox" class="checkbox-all" id="checkbox-all2" />
                <label for="checkbox-all2"></label>
                <span>列表二</span>
            </div>
            <div class="select-content">
                <ul class="selected-ul" id="tyue-checkbox-blue9">
                    <c:forEach items="${clist}" var="a">
                            <li>
                                <input type="checkbox" class="checkboxs"/>
                                <label></label>
                                <span>${a.menu}</span>
                            </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>


</div>



<script>
    layui.use(['form', 'layer'],
        function() {
            $ = layui.jquery;
            var form = layui.form,
                layer = layui.layer;
    })
    function check() {
        var rid=$("#rid").val();

        $.ajax({
            url: "/aid_war_exploded/adminHandle/power.action",
            type: "POST",
            data:{"rid":rid},
            dataType: "text",
            success : function(res){
                window.location.reload();
                if(res=="1"){
                    layer.msg("1111");
                    window.location.reload();
                }else{
                    layer.msg("2222");
                }
            }
        });
    }
</script>
</body>
</html>
