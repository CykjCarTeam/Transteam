package cn.bus.entity;
/**
* @Author:小星
* @Description:后台用户
* @Date:上午 9:52 2019/9/19 0019
*/
public class Admin {
  private int aid;
  private String anum;
  private String apwd;
  private Menu menu;
    public Admin() {
    }

    public Admin(String anum, String apwd) {
        this.anum = anum;
        this.apwd = apwd;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAnum() {
        return anum;
    }

    public void setAnum(String anum) {
        this.anum = anum;
    }

    public String getApwd() {
        return apwd;
    }

    public void setApwd(String apwd) {
        this.apwd = apwd;
    }
}
