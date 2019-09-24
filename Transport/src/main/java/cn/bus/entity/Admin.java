package cn.bus.entity;

public class Admin {

  private int aid;
  private String anum;
  private String apwd;

    public Admin() {
    }

    public Admin(String anum, String apwd) {
        this.anum = anum;
        this.apwd = apwd;
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
