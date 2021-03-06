<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%--  <% String path = request.getContextPath() + "/"; %>--%>

    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
  <meta charset="utf-8">
  <link rel="stylesheet" href="<%=basePath%>lib/layui/css/layui.css" media="all">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>新闻</legend>
</fieldset>



<div class="demoTable">

  <button class="layui-btn" data-type="reload">增加</button>

<%--  <button class="layui-btn" data-type="delete">测试</button>--%>
</div>

<table id="demo" lay-filter="test"></table>

<%--必须用script标签包裹，如果用普通的div会多一对--%>
<script type="text/html" id="barDemo">
<%--  <a class="layui-btn layui-btn-xs" lay-event="edit">工作量查看</a>--%>

  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="update">修改</a>
<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>

</script>

<script src="<%=basePath%>lib/layui/layui.js"></script>
<script>

  layui.use(['table','form'], function(){
    var table = layui.table,
    form = layui.form;

    table.render({
      elem: '#demo'
      ,height: 312
      ,url:  '<%=basePath%>newlist/newfind.action' //数据接口
      ,page: true //开启分页
       ,limit:5
      ,id: 'testReload'

      ,parseData:function(res){
        console.log("返回值" + res);
        console.log("状态码" + res.code);
        console.log("消息" + res.msg);
        console.log("条数" + res.count);
        console.log("数据" + res.data);
        return {
          "code": eval(res.code), //解析接口状态
          "msg": res.msg, //解析提示文本
          "count": res.count, //解析数据长度
          "data": res.data //解析数据列表
        };
      }
      ,cols: [[ //表头
        // {title : '全选', type : 'checkbox'},
        {field: 'nid', title: '序号'}
        ,{field: 'daynews'+"", title: '内容'}
        ,{field: 'daytime', title: '日期'}

        ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
      ]]
    });

    //监听行工具事件
    table.on('tool(test)', function(obj){
      var data = obj.data;
      if(obj.event === 'delete'){
        layer.confirm('真的删除行么', function(index){
             alert(data.nid);
            fal("<%=basePath%>newlist/newdelete.action",data.nid);
            layer.close(index);
            obj.del();
        });
      } else if(obj.event === 'update'){
          $("#aid1").val(data.nid);
          $("#xinwen").val(data.daynews);
          layer.open({

              type: 1,
              title: "新闻修改",
              area: ['420px', '530px'],
              content: $("#updatexinwen")//引用的弹出层的页面层的方式加载修改界面表单
          });

      }
    });
      //弹窗

      <%--table.on('tool(test)', function(obj){--%>
      <%--    var data = obj.data;--%>

      <%--    if(obj.event === 'edit'){--%>

      <%--        //弹出工作量窗口--%>
      <%--        layer.open({--%>
      <%--            type: 1,--%>
      <%--            title: "工作量",--%>
      <%--            area: ['420px', '530px'],--%>
      <%--            content: $("#scheduling")//引用的弹出层的页面层的方式加载修改界面表单--%>
      <%--        });--%>
      <%--    }else if (obj.event === 'update') {--%>

      <%--        //1走ajax获取所有线路--%>
      <%--        //1走ajax获取所有线路--%>
      <%--        $.ajax({--%>
      <%--            url:<%=path%> + 'Linelist/find.action',--%>
      <%--            type:"POST",--%>
      <%--           data:{"aid":"kkk"},--%>
      <%--            // dataType:"json",--%>
      <%--            success:function (flist){--%>
      <%--              // alert(flist[2].line);--%>
      <%--              //   alert(flist[2].lid);--%>
      <%--              var items=flist;--%>
      <%--                $("#majorid1").empty();--%>
      <%--                $.each(items,function (i,n) {--%>

      <%--                    $("#majorid1").append("<option value = '"+items[i].lid+"'>"+items[i].line+"<option>")--%>
      <%--                    $("#aid1").val(data.nid);--%>
      <%--                    $("#aname").val(data.daynews);--%>
      <%--                    $("#phone1").val(data.ddaytime);--%>
      <%--                    form.render();--%>
      <%--                });--%>
      <%--                --%>
      <%--            }--%>
      <%--        });--%>


      //         layer.open({
      //             type: 1,
      //             title: "新闻修改",
      //             area: ['420px', '530px'],
      //             content: $("#updatexinwen")//引用的弹出层的页面层的方式加载修改界面表单
      //         });
      //     }
      // });







    //根据data-type值调用对应事件
    var $ = layui.$,

    active = {//这东西相当于一个事件数组
      reload: function(){
          alert("ooo")
        var demoReload = $('#demoReload');
        var phone = $('#phone');
        var site = $('#site');

        table.reload('testReload', {
          //执行重载
          page: {
            curr: 1 //重新从第 1 页开始
          }
          ,where: {
             "dname": demoReload.val(),
             "dphone": phone.val(),
             "dsite": site.val()
          }
        }, 'data');
      }



    };

    //监听查询按钮事件
    $('.demoTable .layui-btn').on('click', function(){
      var type = $(this).data('type'); //获取data-type里的值
      //相当于遍历匹配数组中的元素(匹配到就回调)
      active[type] ? active[type].call(this) : '';
    });

      function fal(url,nid) {
          alert(nid);
          alert(url);
          $.ajax({
              async: true,
              type: "post",
              url: url,
              data: {"nid":nid},
              success: function (dat) {
                  if(dat==1){
                      layer.msg("删除成功");
                  }else{
                      layer.msg("删除失败11");
                  }
                  table.reload('testReload', {

                      where:{
                      }
                  }, 'data');
              },
              error: function (dat) {
                  layer.msg('错误');
              }
          })
      }


  });

</script>

<%--------------------------------修改界面----------------%>

<div id="updatexinwen" style="display: none">

    <form class="layui-form" action="<%=basePath%>newlist/newchange.action" lay-filter="example"   >

        <div class="layui-form-item">
            <label class="layui-form-label">序号</label>
            <div class="layui-input-inline">

                <input type="text" name="aid1" id="aid1"  readonly="true"  class="layui-input"
                >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">内容</label>
            <div class="layui-input-inline">
                <input type="text" name="xinwen" id="xinwen" required
                       lay-verify="required"  autocomplete="off"
                       class="layui-input">
            </div>

        </div>


        <div class="layui-form-item">
            <div class="layui-input-block">

                <button class="layui-btn" lay-submit="" lay-filter="*">修改</button>

            </div>
        </div>
    </form>





</div>



</body>
</html>