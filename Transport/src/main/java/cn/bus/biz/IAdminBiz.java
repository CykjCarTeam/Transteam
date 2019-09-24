package cn.bus.biz;

import cn.bus.entity.Admin;
import cn.bus.entity.Menu;

import java.util.List;

/**
* @Author:小星
* @Description:后台用户业务接口
* @Date:上午 9:53 2019/9/19 0019
*/
public interface IAdminBiz {

    public Admin login(String anum, String apwd);
    public Admin loginjax(String anum);
    public List<Menu> findfmenu(String anum);
    public List<Menu> findcmenu(String anum);
}
