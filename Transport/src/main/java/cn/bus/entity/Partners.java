package cn.bus.entity;

public class Partners {
    private Integer mid;
    private  String mname;
    private  String msite;

    public Partners() {
    }

    public Partners(Integer mid, String mname, String msite) {
        this.mid = mid;
        this.mname = mname;
        this.msite = msite;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMsite() {
        return msite;
    }

    public void setMsite(String msite) {
        this.msite = msite;
    }
}
