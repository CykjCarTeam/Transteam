package cn.bus.entity;

import org.springframework.web.multipart.MultipartFile;

/**
* @Author:小星
* @Description:后台用户
* @Date:上午 9:52 2019/9/19 0019
*/
public class Admin {

  private Integer aid;
  private String anum;
  private String apwd;

  private MultipartFile head;//头像文件
    public Admin() {
    }

    public Admin(String anum, String apwd) {
        this.anum = anum;
        this.apwd = apwd;
    }

    public MultipartFile getHead() {
        return head;
    }

    public void setHead(MultipartFile head) {
        this.head = head;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
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
