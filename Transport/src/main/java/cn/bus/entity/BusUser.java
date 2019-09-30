package cn.bus.entity;

import org.springframework.web.multipart.MultipartFile;

public class BusUser {
    private Integer uid;
    private String uname;
    private String sex;
    private String company_addr;
    private String home_addr;
    private String phone;
    private Integer fee;//账户
    private String header;//头像

    private MultipartFile head;//头像文件

    public BusUser() {
    }
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCompany_addr() {
        return company_addr;
    }

    public void setCompany_addr(String company_addr) {
        this.company_addr = company_addr;
    }

    public String getHome_addr() {
        return home_addr;
    }

    public void setHome_addr(String home_addr) {
        this.home_addr = home_addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public MultipartFile getHead() {
        return head;
    }

    public void setHead(MultipartFile head) {
        this.head = head;
    }

}
