package cn.bus.mapper;

import cn.bus.entity.BusUser;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
* @Author:小星
* @Description: 后台用户表操作接口
* @Date:上午 9:53 2019/9/19 0019
*/
@Repository
public interface UserMapper {

    BusUser findUser(Integer uid);
    BusUser findUserByTradeNo(String tradeNo);
    void addHead(Map map);

    void makeTrade(Map map);//发起充值请求，生成订单，等待支付宝收款确认，
    void addMoney(Map map);//支付宝收款确认，返回交易号、订单号
}
