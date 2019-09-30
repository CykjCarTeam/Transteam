package cn.bus.entity;

public class Driver {
    //数据库字段
    private  Integer aid;


    private String anum;
    private  String aname;
    private  String phone;
    private  String rid;
    private  Integer account;

    private  int lid;
    private  Line lin;

    private  String line;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Driver(int aid, String anum, String aname, String phone, String rid, String line) {
        this.aid = aid;
        this.anum = anum;
        this.aname = aname;
        this.phone = phone;
        this.rid = rid;
        this.line = line;
    }

    private  int page;
    private  int limit;
    //查询字段
    private String dname;
    private String dphone;
    private String dsite;


    public Driver() {

    }



    public Driver(String anum, String aname, String phone, int lid, Line lin) {
        this.anum = anum;
        this.aname = aname;
        this.phone = phone;
        this.lid = lid;
        this.lin = lin;
    }
//
    public Driver(int aid, String anum, String aname, String phone, String rid, int lid, Line lin) {
        this.aid = aid;
        this.anum = anum;
        this.aname = aname;
        this.phone = phone;
        this.rid = rid;
        this.lid = lid;
        this.lin = lin;
    }
//工资
    public Driver(Integer aid, String anum, String aname, String phone, String rid, Integer account) {
        this.aid = aid;
        this.anum = anum;
        this.aname = aname;
        this.phone = phone;
        this.rid = rid;
        this.account = account;
    }
    //    public Driver(Integer aid, String anum, String aname, String phone, String rid, int lid, Line lin) {
//        this.aid = aid;
//        this.anum = anum;
//        this.aname = aname;
//        this.phone = phone;
//        this.rid = rid;
//        this.lid = lid;
//        this.lin = lin;
//    }


        public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }


//    public Integer getAid() {
//        return aid;
//    }
//
//    public void setAid(Integer aid) {
//        this.aid = aid;
//    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getAnum() {
        return anum;
    }

    public void setAnum(String anum) {
        this.anum = anum;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public Line getLin() {
        return lin;
    }

    public void setLin(Line lin) {
        this.lin = lin;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDphone() {
        return dphone;
    }

    public void setDphone(String dphone) {
        this.dphone = dphone;
    }

    public String getDsite() {
        return dsite;
    }

    public void setDsite(String dsite) {
        this.dsite = dsite;
    }
}
