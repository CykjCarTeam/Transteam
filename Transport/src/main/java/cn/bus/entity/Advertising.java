package cn.bus.entity;

public class Advertising {
    private  Integer advid;
    private Integer mid;
    private  String operationtype;
    private  Integer cid;
    private  String content;
    private  String operationtime;

    public Advertising() {
    }

    public Advertising(Integer advid, Integer mid, String operationtype, Integer cid, String content, String operationtime) {
        this.advid = advid;
        this.mid = mid;
        this.operationtype = operationtype;
        this.cid = cid;
        this.content = content;
        this.operationtime = operationtime;
    }

    public Integer getAdvid() {
        return advid;
    }

    public void setAdvid(Integer advid) {
        this.advid = advid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getOperationtype() {
        return operationtype;
    }

    public void setOperationtype(String operationtype) {
        this.operationtype = operationtype;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(String operationtime) {
        this.operationtime = operationtime;
    }
}
