package cn.bus.web;

import cn.bus.biz.UserBiz;
import cn.bus.entity.Admin;
import cn.bus.entity.BusUser;
import cn.bus.entity.PayInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
* @Author:小星
* @Description: 后台用户访问句柄
* @Date:上午 9:53 2019/9/19 0019
*/
@Controller
@RequestMapping("/user/")
public class UserHandle {

    @Resource
    private UserBiz userBiz;

    @RequestMapping("login")
    public ModelAndView login(Admin admin){

        System.out.println("进来了");
        System.out.println("anum" + admin.getAnum());

        return new ModelAndView("admin/success");
    }
    @RequestMapping("userInfo")
    public ModelAndView userInfo(ModelAndView model,BusUser user){
        System.out.println("userInfo");
        userBiz.find(model,1);
        model.setViewName("user/UserInfo");
        return model;
    }

    @RequestMapping("addUserInfo")
    public ModelAndView upFile(HttpServletRequest request,Integer uid,String company_addr, String home_addr, MultipartFile head)throws Exception{
        Map map=new HashMap();
        map.put("uid",1);
        map.put("company_addr",company_addr);
        map.put("home_addr",home_addr);
        System.out.println("进来了");
        String path=request.getSession().getServletContext().getRealPath("/");
        path=path.substring(0,path.indexOf("\\",13))+"/Transport/src/main/webapp/image/";
        //定义文件保存的本地路径
        //定义 文件名
        String filename=null;
        if(!head.isEmpty()){
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType=head.getContentType();
            //获得文件后缀名
            String suffixName=contentType.substring(contentType.indexOf("/")+1);
            //得到 文件名
            filename=uuid+"."+suffixName;
            //文件保存路径
            head.transferTo(new File(path+filename));
            //把图片的相对路径保存至数据库
            map.put("header",path);
            userBiz.addHead(map);
        }
        return new ModelAndView("admin/UserInfo");
    }
    //我的账户界面
    @RequestMapping("myAccount")
    public ModelAndView myAccount(ModelAndView model,BusUser user){
        userBiz.find(model,1);
        model.setViewName("user/UserMoney");//
        return model;
    }

//点击充值，进入充值页面
    @RequestMapping("charge")
    public ModelAndView charge(ModelAndView model){
        model.setViewName("alipay/index");//跳转到支付宝
        return model;
    }
    //设置充钱金额，发起充值请求，下单，生成订单号，扫码、进行手机付款
    @RequestMapping("addMoney")
    public ModelAndView scanQrcode(ModelAndView model, Integer uid,PayInfo payInfo){
        System.out.println("充值金额"+payInfo.getWIDtotal_amount());
        System.out.println("发起充值订单号"+payInfo.getWIDout_trade_no());
        Map map=new HashMap();
        map.put("uid",uid);
        map.put("money",payInfo.getWIDtotal_amount());
        map.put("trade_no",payInfo.getWIDout_trade_no());//记录发起充值的订单号
//        userBiz.makeTrade(map);//记录发起充值的订单号
        model.addObject("payInfo",payInfo);
        model.setViewName("alipay/alipay.trade.page.pay");//跳转到支付二维码界面
        return model;
    }

    //付款成功，支付宝返回交易号、商家订单号out_trade_no，
    //out_trade_no 对应之前扫码的订单号，根据out_trade_no找到用户，进行数据库操作
    @RequestMapping("addSuccess")
    public ModelAndView addMoney( ModelAndView model,String money,String trade_no){
        System.out.println("支付宝返回订单号"+trade_no);
        Map map=new HashMap();
        map.put("trade_no",trade_no);
        map.put("trade_no",money);
        userBiz.addMoney(map);
//        userBiz.find(model,1);//根据订单号查询用户
        model.setViewName("user/UserMoney");//
        return model;
    }

}
