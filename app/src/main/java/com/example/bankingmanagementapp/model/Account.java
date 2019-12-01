package com.example.bankingmanagementapp.model;

public class Account {

    int id;
    int pinNo;
    String accountType;
    String address;
    String dob;
    String nationality;
    String cast;
    String phon;
    String sQus;
    String ans;
    String gender;

    Balance balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPinNo() {
        return pinNo;
    }

    public void setPinNo(int pinNo) {
        this.pinNo = pinNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getPhon() {
        return phon;
    }

    public void setPhon(String phon) {
        this.phon = phon;
    }

    public String getsQus() {
        return sQus;
    }

    public void setsQus(String sQus) {
        this.sQus = sQus;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", pinNo=" + pinNo +
                ", accountType='" + accountType + '\'' +
                ", address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                ", nationality='" + nationality + '\'' +
                ", cast='" + cast + '\'' +
                ", phon='" + phon + '\'' +
                ", sQus='" + sQus + '\'' +
                ", ans='" + ans + '\'' +
                ", gender='" + gender + '\'' +
                ", balance=" + balance +
                '}';
    }
}
