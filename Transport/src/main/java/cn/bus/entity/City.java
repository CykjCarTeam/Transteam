package cn.bus.entity;
/**
* @Author:小星
* @Description:城市
* @Date:下午 9:31 2019/9/24 0024
*/
public class City {

    private Integer cid;
    private String province; //省份
    private String city;//城市

    public City() {
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
