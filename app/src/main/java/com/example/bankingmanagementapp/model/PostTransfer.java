package com.example.bankingmanagementapp.model;

public class PostTransfer {




    int id;
    int transferAmount ;
    int dRtotalAmount ;
    String selecterAccountNo;
    int cRcurrentAcBalance;
    int cRAcTotalBalance ;
    String drAccountNo ;


    public PostTransfer(int id, int transferAmount, int dRtotalAmount, String selecterAccountNo, int cRcurrentAcBalance, int cRAcTotalBalance, String drAccountNo) {
        this.id = id;
        this.transferAmount = transferAmount;
        this.dRtotalAmount = dRtotalAmount;
        this.selecterAccountNo = selecterAccountNo;
        this.cRcurrentAcBalance = cRcurrentAcBalance;
        this.cRAcTotalBalance = cRAcTotalBalance;
        this.drAccountNo = drAccountNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PostTransfer() {
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(int transferAmount) {
        this.transferAmount = transferAmount;
    }

    public int getdRtotalAmount() {
        return dRtotalAmount;
    }

    public void setdRtotalAmount(int dRtotalAmount) {
        this.dRtotalAmount = dRtotalAmount;
    }

    public String getSelecterAccountNo() {
        return selecterAccountNo;
    }

    public void setSelecterAccountNo(String selecterAccountNo) {
        this.selecterAccountNo = selecterAccountNo;
    }

    public int getcRcurrentAcBalance() {
        return cRcurrentAcBalance;
    }

    public void setcRcurrentAcBalance(int cRcurrentAcBalance) {
        this.cRcurrentAcBalance = cRcurrentAcBalance;
    }

    public int getcRAcTotalBalance() {
        return cRAcTotalBalance;
    }

    public void setcRAcTotalBalance(int cRAcTotalBalance) {
        this.cRAcTotalBalance = cRAcTotalBalance;
    }

    public String getDrAccountNo() {
        return drAccountNo;
    }

    public void setDrAccountNo(String drAccountNo) {
        this.drAccountNo = drAccountNo;
    }

    @Override
    public String toString() {
        return "PostTransfer{" +
                "id=" + id +
                ", transferAmount=" + transferAmount +
                ", dRtotalAmount=" + dRtotalAmount +
                ", selecterAccountNo='" + selecterAccountNo + '\'' +
                ", cRcurrentAcBalance=" + cRcurrentAcBalance +
                ", cRAcTotalBalance=" + cRAcTotalBalance +
                ", drAccountNo='" + drAccountNo + '\'' +
                '}';
    }
}
