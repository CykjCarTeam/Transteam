
$(function(){
	//加载layui资源
	layui.use(['transfer','form'], function(){
		var transfer = layui.transfer,
		form = layui.form,
		layer = layui.layer,
	    $ = layui.$;
		//获取path
		var path = $("#path").attr("title");
		loadStations(transfer,path);//加载站点
		 //提交路线
		 $(".layui-btn").click(function(){
			 var result = transfer.getData('line');//返回右侧值
			 var mid = [];//提取出右侧菜单id
			 $.each(result, function(index,elem){
				 mid.push(elem.value);
			 });
			 //线路名不为空验证
			 var line_name = $("#line_info").val().trim();
			 if(line_name == ""){
			 	layer.msg("请输入线路名！");
			 	return;
			 }
			 if(mid.length < 2){//未配置起点和终点
			 	layer.msg("未配置线路终始点！");
			 	return;
			 }
			 var str = JSON.stringify(mid);//转成json字符串
			 addLine(str,line_name,path);//响应给后台
		 });
	});
	
});

//初始化城市底下的所有站点
function loadStations(transfer, path){

	$.ajax({
		type:'post',
		url:path + 'lineHandle/loadStation.action',
		dataType:'JSON',
		success:function(result){
			var stations = eval(result);
			init(stations,transfer);
		},
		error:function(){
			console.log('通讯错误');
		}
	});
}

//初始化穿梭框
function init(stations, transfer){
	 //渲染
    transfer.render({
      elem: '#lineData'  //绑定容器
      ,title:['未添加站点(全选)','已添加站点(全选)']
      ,data: stations,
      parseData: function(res){
    	    return {
    	      "value": res.sid //数据值
    	      ,"title": res.station //数据标题
    	    }
    	  }

      ,id: 'line' //定义索引
    }); 
     
}

//数据提交到后台
function addLine(lid, line_name,path){

	$.ajax({
		type:'post',
		url:path + 'lineHandle/commitLine.action',
		data:{'lid':lid, 'line':line_name},
		dataType:'text',
		success:function(result){
			if(result == "-1"){
				layer.msg("线路已存在！");
				return;
			}
			layer.msg("线路配置成功！点击右上角退出当前窗口！");
			//关闭弹窗（关闭之身）
		},
		error:function(){
			console.log('通讯错误');
		}
	});
}


