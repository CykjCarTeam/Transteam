package cn.bus.biz;

import cn.bus.entity.Admin;
import cn.bus.entity.Menu;
import cn.bus.entity.Parameter;
import cn.bus.entity.Role;
import cn.bus.mapper.IAdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class
AdminBizImp implements IAdminBiz {

    @Resource
    private IAdminMapper iAdminMapper;

    @Override
    public Admin login(String anum, String apwd) {
        return iAdminMapper.queryUser(new Admin(anum, apwd));
    }

    @Override
    public Admin loginjax(String anum) {
        return iAdminMapper.loginjax(anum);
    }
    @Override
    public List<Menu> findfmenu(String anum) {
        return iAdminMapper.findfmenu(anum);
    }
    @Override
    public List<Menu> findcmenu(String anum) {
        return iAdminMapper.findcmenu(anum);
    }

    @Override
    public List<Admin> findrole() {
        return iAdminMapper.findrole();
    }

    @Override
    public List<Admin> listajax(Map<String,Object> conditions) {
        return iAdminMapper.listajax(conditions);
    }

    @Override
    public Integer listcount(Map<String, Object> conditions) {
        return iAdminMapper.listcount(conditions);
    }

    @Override
    public Integer deleteanum(Map<String, Object> delete) {
        return iAdminMapper.deleteanum(delete);
    }

    @Override
    public Integer upstate(Map<String, Object> upstate) {
        return iAdminMapper.upstate(upstate);
    }

    @Override
    public Parameter findpass(Integer pid) {
        return iAdminMapper.findpass(pid);
    }

    @Override
    public Integer uppass(Map<String, Object> upstate) {
        return iAdminMapper.uppass(upstate);
    }

    @Override
    public Integer addAdmin(Admin admin) {
        return iAdminMapper.addAdmin(admin);
    }

    @Override
    public Integer uprole(Map<String, Object> uprole) {
        return iAdminMapper.uprole(uprole);
    }

    @Override
    public Role roleajax(String role) {
        return iAdminMapper.roleajax(role);
    }

    @Override
    public Integer deleterole(Integer rid) {
        return iAdminMapper.deleterole(rid);
    }

    @Override
    public Integer addrole(String role) {
        return iAdminMapper.addrole(role);
    }

    @Override
    public List<Menu> allpower() {
        return iAdminMapper.allpower();
    }

    @Override
    public List<Menu> power(Integer rid) {
        return iAdminMapper.power(rid);
    }


}
