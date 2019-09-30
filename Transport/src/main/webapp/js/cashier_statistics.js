    var path=$("#selectctiy").attr("title");
    var myChart = echarts.init(document.getElementById('show'));
    var myChart2 = echarts.init(document.getElementById('show2'));
    // addmap(point);
    var val;//城市下拉框文本
    var cid;//城市下拉框id
    function cityselect() {
        layui.use('form', function () {
               var form = layui.form,
                $ = layui.$;
            $.ajax({
                url: path + "siteHandle/stationcity.action",
                type: "post",
                dataType:"json",
                success: function (redata) {
                    $.each(redata,function (i,u) {

                        $("#citys").append
                        (
                            "<option value="+u.cid+">"+u.city+"</option>"

                        )
                        form.render();
                    })

                },

            });
        });

    }

layui.use(['table','form'], function () {
    var table = layui.table,
        form=layui.form,
        $=layui.$;

    form.on('select(city)',function (cityva) {
        val=cityva.elem[cityva.elem.selectedIndex].text;
        cid=cityva.value;
        if(cid==null||cid=="")
        {
            layer.msg("请先选择城市",{icon:5,time:1500,title:"提示",weight:600});
        }
        else{
            $.ajax({
                url: path+"statisticsHandle/statisticsline.action?cid="+cid,
                type: "post",
                dataType:"json",

                success: function (redata) {
                    var data2=[];
                    // var mes={};
                    var data=[];
                    var mcount=0;
                    $.each(redata,function (i,u) {
                        data2.push(u.line);
                        var count=(u.psum/3)*u.money;
                        mcount+=count;
                      var mes={value:count,name:u.line};
                        data.push(mes);

                    })

                    option1 = {
                        title: {
                            text: '线路收银统计',
                            subtext:'总计：'+mcount,
                            x:'center'
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: "{a} <br/>{b}: {c} ({d}%)"
                        },
                        legend: {
                            orient: 'vertical',
                            x: 'left',
                            data:data2
                        },
                        series: [
                            {
                                name:'收银情况',
                                type:'pie',
                                radius: ['50%', '70%'],
                                avoidLabelOverlap: false,
                                label: {
                                    normal: {
                                        show: false,
                                        position: 'center'
                                    },
                                    emphasis: {
                                        show: true,
                                        textStyle: {
                                            fontSize: '30',
                                            fontWeight: 'bold'
                                        }
                                    }
                                },
                                labelLine: {
                                    normal: {
                                        show: false
                                    }
                                },
                                data:data
                            }
                        ]

                    };
                    myChart2.setOption(option1);

                },
                error:function () {
                    console.log("出现异常");
                }

            });

            $.ajax({
                url: path+"statisticsHandle/mothcount.action?cid="+cid,
                type: "post",
                dataType:"json",

                success: function (redata) {
                    var data=[];
                    var data2=[];
                    var mcount2=0;
                    $.each(redata,function (i,u) {
                        data.push(u.week);
                        data2.push(u.count1);
                    mcount2+=u.count1;
                    })
                    option = {
                        title: {
                            text: '本月收银周统计',
                            subtext:'总计：'+mcount2,
                            x:'center'
                        },
                        tooltip: {
                            text:'总计：'
                        },
                        xAxis: {
                            type: 'category',
                            data: data
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                            data: data2,
                            type: 'bar'
                        }]
                    };


                    myChart.setOption(option);

                },
                error:function () {
                    console.log("出现异常");
                }

            });


        }
    });
});
