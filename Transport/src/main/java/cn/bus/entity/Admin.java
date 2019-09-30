package cn.bus.entity;

public class Admin {
  private int aid;//id
  private String anum;//账户
  private String apwd;//密码
  private String aname;
  private Integer age;
  private String sex;
  private Integer account;
  private String phone;
  private Integer rid;
  private String area;
  private Integer state;
  private String image;
  private Integer lid;
  private String role;
  private Parameter parameter;
  private Menu menu;//菜单

    public Admin() {
    }

    public Admin(String anum, String apwd) {
        this.anum = anum;
        this.apwd = apwd;
    }

    public Admin(String anum, String apwd, String aname, Integer age, String sex, String phone, Integer rid, String area, Integer state, String image) {
        this.anum = anum;
        this.apwd = apwd;
        this.aname = aname;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
        this.rid = rid;
        this.area = area;
        this.state = state;
        this.image = image;
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

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }
}
