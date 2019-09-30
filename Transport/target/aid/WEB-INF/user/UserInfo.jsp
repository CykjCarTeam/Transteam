<%@ page language="java" contentType="text/html;"
    pageEncoding="utf-8"%>
<%@ page import="cn.bus.entity.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%pageContext.setAttribute("path",request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
	<title>公交管理</title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
    <link href="${path}/layui/css/layui.css" rel="stylesheet"  media="all"/>
	<script type="text/javascript" src="${path}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/layui/layui.js"></script>
	<script type="text/javascript" src="${path}/layui/lay/modules/jquery.js"></script>
	<script type="text/javascript" src="${path}/layui/lay/modules/layer.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ykrYFllyk7nAN0oTIWHPwdxTkRkzfPuE&callback"></script>
	<script type="text/javascript" src="${path}/layui/layui.all.js"></script>
    <style type="text/css">
        body, html,#map {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    </style>
    <script>
		$(function(){

		})
	</script>
</head>

<body>

<form class="table" style="margin-left: 25%;margin-right: 25%;" action="${path}/user/addUserInfo.action" method="post" enctype="multipart/form-data">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>用户个人信息</legend>
    </fieldset>
	<div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-inline">
                <input type="text" name="number" readonly="readonly" value="${user.phone}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="username" readonly="readonly"  value="${user.uname}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 150px">配置常用地点</label>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">家</label>
            <div class="layui-input-inline">
                <input type="text" id="home" name="company_addr"  value="${user.company_addr}" class="layui-input">
                <input type="hidden" id="home_x">
                <input type="hidden" id="home_y">
            </div>
            <div class="layui-input-inline">
                <button type="button" class="layui-btn" id="chooseHome" data-type="chooseHome">选点</button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">公司</label>
            <div class="layui-input-inline">
                <input type="text" id="company" name="home_addr"  value="${user.home_addr}" class="layui-input">
                <input type="hidden" id="comp_x">
                <input type="hidden" id="comp_y">
            </div>
            <div class="layui-input-inline">
                <button type="button" class="layui-btn" id="chooseComp" data-type="chooseHome">选点</button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-inline">
                <input type="text" name="sex" readonly="readonly"  value="${user.sex}" class="layui-input">
            </div>
        </div>
	</div>
	<div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">头像</label>
            <div class="layui-input-inline">
                <input type="file" name="head" lay-verify="required|"  class="layui-input">
            </div>
        </div>
	</div>
    <button type="submit" class="layui-btn" style="margin-left: 10%" id="testListAction">提交</button>
</form>
<div id="map" style="display: none;"></div>
<script type="text/javascript">
    var map =null;
    var x=null;
    var y=null;
    var place=null;

   function loadMap(){
        map = new BMap.Map("map");
        var point = new BMap.Point(116.331398,39.897445);
        map.centerAndZoom(point,12);

        function myFun(result){
            var cityName = result.name;
            map.setCenter(cityName);
            alert("当前定位城市:"+cityName);
        }
        var myCity = new BMap.LocalCity();
        myCity.get(myFun);
        map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
        //单击获取点击的经纬度
        map.addEventListener("click",function(e){
            var pt = e.point;
            geoc.getLocation(pt, function(rs){
                var addComp = rs.addressComponents;
                //获取地点名
                place=addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
            });
            //获取坐标
            x=e.point.lng;
            y=e.point.lat;
        });
    }
    layui.use(['table','form','layer'], function() {
        var table = layui.table,
            layer = layui.layer;
            $ = layui.$;
        var $ = layui.$, active = {
            chooseHome: function (){
                loadMap();
                layer.open(
                    {
                        type: 1, //页面
                        title: "地图",
                        btn:['确定','取消'],
                        area: ['800px', '650px'],
                        content: $("#map"),
                        btn1:function (index) {
                            $("#home_x").val(x);
                            $("#home_y").val(y);
                            $("#home").val(place);
                            layer.close(index);
                        },
                        btn2:function (index) {
                            layer.close(index);
                        }
                    }
                );
            },
            chooseComp: function (){
                loadMap();
                layer.open(
                    {
                        type: 1, //页面
                        title: "地图",
                        btn:['确定','取消'],
                        area: ['800px', '650px'],
                        content: $("#map"),
                        btn1:function (index) {
                            $("#company_x").val(x);
                            $("#company_y").val(y);
                            $("#company").val(place);
                            layer.close(index);
                        },
                        btn2:function (index) {
                            layer.close(index);
                        }
                    }
                );
            }
        };

        $('.table .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    })
</script>
</body>


</html>