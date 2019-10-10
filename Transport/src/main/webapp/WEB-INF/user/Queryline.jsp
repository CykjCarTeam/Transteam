<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html class="x-admin-sm">

<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <meta charset="UTF-8">
    <title></title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="<%=basePath%>css/font.css">
    <link rel="stylesheet" href="<%=basePath%>css/xadmin.css">
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
</head>

<body >
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="demoTable" >
                    <div class="layui-form-pane">
                        <label class="layui-form-label">线路名称：</label>
                    </div>
                    <div class="layui-inline ">
                        <input type="text"  id="line" name="line" placeholder="请输入线路名称" autocomplete="off"
                               class="layui-input"></div>
                    <div class="layui-inline">
                        <button id="btn_ok" class="layui-btn" data-type="reload">查询</button>
                    </div>

                </div>
                <div  id="bodydiv" class="layui-card-body" align="center" >
                </div>

            </div>
        </div>
    </div>
</div>
</body>
<div id="tc" class="layui-card-body" align="center" hidden="hidden">
    <table id="times">
    </table>
</div>

<script src="<%=basePath%>lib/layui/layui.js"></script>
<script>

    layui.use(['table','form'], function () {
        var table = layui.table,
            form=layui.form,
            $=layui.$;
    $("#btn_ok").click(function () {
        var line=$("#line").val();
        if(line==null||line=="")
        {
            layer.msg("请输入线路名称",{icon:5,time:1500,title:"提示"})
        }else
        {
            $.ajax({
                url:"<%=basePath%>uquerylineHandle/uqueryline.action?line="+line ,
                type: "post",
                dataType:"json",
                success: function (redata) {
                    $("#bodydiv").empty();
                    $.each(redata,function (i,u) {
                        $("#bodydiv").append(
                            '<div id="'+i+'" style="background-color: #00A0E8">'+
                            '线路名称：'+u.line+
                            '</br>'+
                            '车牌号：'+u.bid+
                            '</br>'+
                            '起点：'+u.fstation+
                            '</br>'+
                            '终点：'+u.lstation+'</div>'+ '</br>'
                        )
                        $("#"+i).click(function () {

                            $.ajax({
                                url:"<%=basePath%>uquerylineHandle/uquerylinetime.action?bid="+u.bid ,
                                type: "post",
                                dataType:"json",
                                success: function (redat) {
                                    if (redat == null || redat == "") {
                                        layer.msg("暂无发车时刻安排", {icon: 5, time: 1500, title: "提示"})
                                    }
                                    else
                                    {
                                        $("#times").empty();
                                    $.each(redat, function (i, x) {
                                        $("#times").append(
                                            '</br>'+
                                            '<tr style="font-size: 24pt;height: 50px">'
                                            + '<td >' + x.times + '</td>'
                                            + '</tr>'
                                            +'</br>'

                                        )
                                    })
                                    layer.open({
                                        type: 1
                                        , title: "发车时刻表" //不显示标题栏
                                        , closeBtn: false
                                        , area: ['600px', '600px']
                                        , shade: 0.8
                                        , id: 'LAY_layuipro' //设定一个id，防止重复弹出
                                        , btn: ['返回']
                                        , btnAlign: 'c'
                                        ,anim: 4
                                        , moveType: 1 //拖拽模式，0或者1
                                        , content: $("#tc")
                                        , success: function (layero) {
                                            var btn = layero.find('.layui-layer-btn');
                                            btn.find('.layui-layer-btn0').attr({
                                                target: '_blank'
                                            });
                                        }
                                    });
                                }
                                },

                            });
                        })
                    })

                },

            });
        }

    })


    });
</script>
</html>