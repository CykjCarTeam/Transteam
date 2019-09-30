package cn.bus.web;

import cn.bus.biz.IAdminBiz;
import cn.bus.entity.Admin;
import cn.bus.entity.Menu;
import cn.bus.entity.Parameter;
import cn.bus.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/adminHandle")
public class AdminHandle {
    private Integer page;
    private Integer paging=5;
    private Integer rid;

    @Resource
    private IAdminBiz iAdminBiz;
    @RequestMapping("/login")
    public ModelAndView login(Admin admin, String authCode, HttpServletRequest request){
        String valueCode=(String)request.getSession().getAttribute("strCode");
        Admin admins = iAdminBiz.login(admin.getAnum(), admin.getApwd());
        if(null !=admins&valueCode.equals(authCode)) {
            //活动菜单数据
            List<Menu> flist=iAdminBiz.findfmenu(admins.getAnum());//父类菜单
            List<Menu> clist=iAdminBiz.findcmenu(admins.getAnum());//子类菜单
            Map<Menu,List<Menu>> map=new LinkedHashMap<Menu,List<Menu>>();
            for (Menu menu:flist) {
                map.put(menu,new ArrayList<Menu>());
                for (Menu menu1:clist) {
                    if(menu.getMid()==menu1.getPid()){
                        List<Menu> list=map.get(menu);
                        list.add(menu1);
                    }
                }
            }
            request.getSession().setAttribute("map",map);
            request.getSession().setAttribute("anum",admin.getAnum());
            return new ModelAndView("admin/index");
        }
        return  new ModelAndView("admin/login");
    }

    //登录的ajax
    @RequestMapping(value="/logajax", method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody
    String logajax(String anum){
        Admin admin=iAdminBiz.loginjax(anum);
        String messagq;
        if(null!=admin){
            messagq="1";
        }else{
            messagq="0";
        }
        return messagq;
    }

    //跳转界面
    @RequestMapping("/interface")
    public ModelAndView jump(String url,HttpServletRequest request,String role,String rid){
        System.out.println("//77477//"+url);
        if(url.equals("Usercontrol")||url.equals("enroll")||url.equals("role")||url.equals("rights")){
            List<Admin> list=iAdminBiz.findrole();
            request.getSession().setAttribute("list",list);
        }else if(url.equals("part")){
            request.getSession().setAttribute("rid",rid);
            request.getSession().setAttribute("role",role);
        }
        return new ModelAndView("admin/"+url);
    }

    //用户表格查询
    @RequestMapping(value="/listajax", method= RequestMethod.GET, produces="application/json;charset=utf-8")
    public @ResponseBody Map<String, Object> listajax(Integer page,Integer rid,String aname, HttpServletRequest request){
        this.rid=rid;
        Map<String,Object> conditions=new HashMap<String,Object>();
        conditions.put("page",(page-1)*paging);
        conditions.put("paging",paging);
        conditions.put("rid",rid);
        conditions.put("aname",aname);

        List<Admin> list = iAdminBiz.listajax(conditions);
        Map<String, Object> hashMap = new HashMap<String, Object>();
        Integer count = iAdminBiz.listcount(conditions);
        System.out.println(count+"//count//");
        hashMap.put("code", 0);
        hashMap.put("count", count);
        hashMap.put("msg", "");
        hashMap.put("data", list);
        return hashMap;
    }

    //删除
    @RequestMapping(value="/delect", method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody
    String editorajax(String anum){
        this.rid=rid;
        System.out.println(anum+"//77//");
        Map<String,Object> editor=new HashMap<String,Object>();
        editor.put("rid",rid);
        editor.put("anum",anum);
        Integer delete =iAdminBiz.deleteanum(editor);
        String msg="0";
        if(null!=delete){
            msg=String.valueOf(delete);
        }
        return msg;
    }

    //修改状态
    @RequestMapping(value="/upstate", method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody
    String upstate(Integer pid,String anum){
        System.out.println(pid+"/pid//"+anum+"//anum//");
        Map<String,Object> editor=new HashMap<String,Object>();
        editor.put("state",pid);
        editor.put("anum",anum);
        Integer upstate =iAdminBiz.upstate(editor);
        String msg="0";
        System.out.println(upstate+"upstate");
        if(null!=upstate){
            msg=String.valueOf(upstate);
        }
        return msg;
    }

    //重置密码
    @RequestMapping(value="/uppass", method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody
    String uppass(String anum){
        System.out.println(anum+"//anum//");
        Integer pid=17;
        Parameter parameter=iAdminBiz.findpass(pid);
        Map<String,Object> editor=new HashMap<String,Object>();
        editor.put("anum",anum);
        editor.put("apwd",parameter.getParam());
       Integer uppass =iAdminBiz.uppass(editor);
        String msg="0";
        System.out.println(uppass+"upstate");
        if(null!=uppass){
            msg=String.valueOf(uppass);
        }
        return msg;
    }


    //注册
    @RequestMapping(value="/fileact", method= RequestMethod.POST)
    @ResponseBody
    public String fileact(@Param("fileact") MultipartFile fileact, HttpServletRequest request, HttpServletResponse response,
                          String anum, String apwd, String aname,
                          Integer age, String sex, String phone, Integer rid, String area) throws IOException {
        String originalFilename = fileact.getOriginalFilename();
        System.out.println(originalFilename+"//"+anum+"//"+apwd+"//"+aname+"//"+age+"//"+sex+"//"+phone+"//"+rid+"//"+area+"//");
        String mes="0";
        PrintWriter out = response.getWriter();
        if(fileact!=null && originalFilename!=null && originalFilename.length()>0) {
            String filename = fileact.getOriginalFilename();
            System.out.println("获取到的文件名:" + filename);
            String path=request.getSession().getServletContext().getRealPath("/");

            try {
                path = path.substring(0,path.indexOf("\\",13));
                fileact.transferTo(new File(path+"/Transport/src/main/webapp/images/"+ filename));
                String image=path+"/Transport/src/main/webapp/images/"+ filename;
                Admin admin=new Admin(anum,apwd,aname,age,sex,phone,rid,area,2,image);
                Integer add=iAdminBiz.addAdmin(admin);
                if(null!=add){
                    mes="1";
                }
            } catch (IllegalStateException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return mes;
    }



    //验证角色是否存在
    @RequestMapping(value="/roleajax", method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody
    String roleajax(String role){
        String messagq;
        Role role1=iAdminBiz.roleajax(role);
        if(null!=role1){
            messagq="1";
        }else{
            messagq="0";
        }
        return messagq;
    }


    //修改角色
    @RequestMapping(value="/changerid", method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody
    String changerid(String rid,String role){
        System.out.println(rid+"//role//"+role);
        String msg="0";
        Map<String,Object> editor=new HashMap<String,Object>();
        editor.put("rid",rid);
        editor.put("role",role);
        Integer integer=iAdminBiz.uprole(editor);
        if(null!=integer){
            msg=String.valueOf(integer);
        }
        return msg;
    }

    //删除角色
    @RequestMapping(value="/delerole", method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody
    String delerole(Integer rid){
        Integer delete =iAdminBiz.deleterole(rid);
        String msg="0";
        if(null!=delete){
            msg=String.valueOf(delete);
        }
        return msg;
    }

    //新增角色addrole
    @RequestMapping(value="/addrole", method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody
    String addrole(String role){
        String msg="0";
        Integer addrole=iAdminBiz.addrole(role);
        if(null!=addrole){
            msg=String.valueOf(addrole);
        }
        System.out.println(msg);
        return msg;
    }


    //角色配置
    @RequestMapping(value="/power", method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody
    String power(Integer rid,HttpServletRequest request){
        String msg="0";
        List<Menu> menus=iAdminBiz.power(rid);
        if(null!=menus){
            msg=String.valueOf(menus);
            List<Menu> list=iAdminBiz.allpower();
            request.getSession().setAttribute("flist",list);
            request.getSession().setAttribute("clist",menus);
        }
        System.out.println(msg);
        return msg;
    }




}
