
function check(path) {
    layui.use('layer', function(){ //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer;
     var anum=$("#anum").val();
     if(null==anum||anum==""){
         layer.msg("账户不为空");
        return false;
     }else{
        $.ajax({
             url: "path/logajax.action",
            type: "POST",
             data:{"anum":anum},
             dataType: "text",
            success : function(res){
                 if(res=="1"){
                     layer.msg("账户正确");
                 }else{
                     layer.msg("账户有误");
                }
             }
         });
     }});
 }
 function check1() {
     layui.use('layer', function() { //独立版的layer无需执行这一句
         var $ = layui.jquery, layer = layui.layer;
         layer = layui.layer;
         var apwd = $("#apwd").val();
         if (null == apwd || apwd == "" || apwd.length < 6) {
             layer.msg("请输入六位数的密码");
         } else {
             layer.msg("密码位数符合");
         }
     });
 }

function chageCode(path){
    $('#codeImage').attr('src','/authCode.action?abc='+Math.random());
}
function yanzhenma(path){
    layui.use('layer', function() { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer;
        $.post(
            "/validate.action",
            {"value": $("#authCode").val()},
            function (res) {
                if (res == "1") {
                    layer.msg("验证成功");
                } else {
                    layer.msg("验证失败");
                    //刷新验证码图片
                    $("#change").trigger("click");
                }
            },
            "JSON"
        );
    });
}