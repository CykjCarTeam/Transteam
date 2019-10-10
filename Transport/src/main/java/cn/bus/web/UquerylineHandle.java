package cn.bus.web;

import cn.bus.biz.IStatisticsBiz;
import cn.bus.biz.UserQueryBiz;
import cn.bus.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/uquerylineHandle")
public class UquerylineHandle
{
    @Resource
    private UserQueryBiz userQueryBizImp;
    @RequestMapping(value="/uqueryline.action", method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody
    List<Uqueryline> uqueryline(String line)
    {

        List<Uqueryline> uquerylines=userQueryBizImp.uqueryline(line);

        return uquerylines;
    }

    @RequestMapping(value="/uquerylinetime.action", method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody
    List<Uquerylinetime> uquerylinetime(String bid)
    {

        List<Uquerylinetime> uquerytimes=userQueryBizImp.uquerytime(bid);

        return uquerytimes;
    }

    @RequestMapping(value="/uquerystation.action", method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody
    List<Station> uquerystation()
    {

        List<Station> Stations=userQueryBizImp.uquerystation();

        return Stations;
    }
//换乘线路查询
    @RequestMapping(value="/uquerystation2.action")
    public @ResponseBody
    List uquerystation2(String fstation,String lstation)
    {

        List<Station> Station1=userQueryBizImp.uquerystation2(fstation);
        List<Station>  Station2=userQueryBizImp.uquerystation2(lstation);
        Station s1=null;
        Station s2=null;
        Station s3=null;
        Station s4=null;
        Uqueryline ul=null;
        Uqueryline ul2=null;
        List<Station> lines1=null;
        List<Station> lines2=null;
        List<Map> list=new ArrayList<>();
        int count=0;

        if(Station1.size()>0&&Station2.size()>0){
            a:for (int i=0;i<Station1.size();i++){

            s1=Station1.get(i);
            for (int j=0;j<Station2.size();j++){
                s2=Station2.get(j);
                if(s1.getLid()==s2.getLid())
                {
                    Map<String,Object> map=new HashMap<>();
                    map.put("mes",0);
                    map.put("lid",s1.getLid());
                    map.put("line",s1.getLine());
                    ul=userQueryBizImp.linestationname(s1.getLid());
                    map.put("ul",ul);
                    map.put("fstation",s1);
                    map.put("lstation",s2);
                    count++;
                    list.add(map);
                }
                if(count==0)
                {
                    b:for (int x=0;x<Station1.size();x++)
                    {

                        s1 = Station1.get(x);
                        for (int y = 0; y < Station2.size(); y++)
                        {
                            s2 = Station2.get(y);
                            lines1=userQueryBizImp.roleline(s1.getLid());
                            lines2=userQueryBizImp.roleline(s2.getLid());
                            for (int o=0;o<lines1.size();o++){
                                s3=lines1.get(o);
                                for (int p=0;p<lines2.size();p++)
                                {
                                    s4=lines2.get(p);
                                    if(s3.getSid()==s4.getSid())
                                    {
                                        Map<String,Object> map2=new HashMap<>();
                                        map2.put("mes",1);
                                        map2.put("flid",s3.getLid());
                                        map2.put("fline",s3.getLine());
                                        map2.put("llid",s4.getLid());
                                        map2.put("lline",s4.getLine());
                                        ul2=userQueryBizImp.linestationname(s1.getLid());
                                        map2.put("ul2",ul2);
                                        map2.put("hstation",s3.getStation());
                                        map2.put("fstation",s1);
                                        map2.put("lstation",s2);
                                        list.add(map2);
                                        break a;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            }
        }



        return list;
    }
    @RequestMapping(value="/linemap.action", method= RequestMethod.POST, produces="application/json;charset=utf-8")
    public @ResponseBody
    Map linemap(Integer flid,Integer llid,Integer lid)
    {
        Map<String,Object> map=new HashMap<>();
        List<Station> ls=null;
        List<Station> ls1=null;
        List<Station> ls2=null;
        if ((flid!=null)&&(llid!=null))
        {
            map.put("code",0);
            ls1=userQueryBizImp.ualongstation(flid+"");
            ls2=userQueryBizImp.ualongstation(llid+"");
            map.put("line1",ls1);
            map.put("line2",ls2);
        }else
        {
            map.put("code",1);
            ls=userQueryBizImp.ualongstation(lid+"");
            map.put("line",ls);
        }

        return map;
    }
}
