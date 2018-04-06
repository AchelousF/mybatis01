package com.achelous.beans;

/**
 * @Auther: fanJiang
 * @Date: Create in 16:09 2018/4/6
 */
public class Blog {


    private Integer bid;
    private String name;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "bid=" + bid +
                ", name='" + name + '\'' +
                '}';
    }
}
