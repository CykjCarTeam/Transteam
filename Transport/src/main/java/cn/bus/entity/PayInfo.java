package cn.bus.entity;

public class PayInfo {
    private String WIDout_trade_no;//商户订单号
    private String WIDtotal_amount;//付款金额
    private String WIDsubject;//订单名称
    private String WIDbody;//商品描述

    public PayInfo() {
    }

    public String getWIDout_trade_no() {
        return WIDout_trade_no;
    }

    public void setWIDout_trade_no(String WIDout_trade_no) {
        this.WIDout_trade_no = WIDout_trade_no;
    }

    public String getWIDtotal_amount() {
        return WIDtotal_amount;
    }

    public void setWIDtotal_amount(String WIDtotal_amount) {
        this.WIDtotal_amount = WIDtotal_amount;
    }

    public String getWIDsubject() {
        return WIDsubject;
    }

    public void setWIDsubject(String WIDsubject) {
        this.WIDsubject = WIDsubject;
    }

    public String getWIDbody() {
        return WIDbody;
    }

    public void setWIDbody(String WIDbody) {
        this.WIDbody = WIDbody;
    }
}
