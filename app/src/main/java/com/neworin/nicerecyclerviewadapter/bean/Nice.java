package com.neworin.nicerecyclerviewadapter.bean;

/**
 * Created by NewOrin Zhang on 2016/9/6.
 * E-Mail : NewOrinZhang@Gmail.com
 */
public class Nice {

    private int nice_type;
    private String nice_name;

    public Nice() {
    }

    public Nice(int nice_type, String nice_name) {
        this.nice_type = nice_type;
        this.nice_name = nice_name;
    }

    public int getNice_type() {
        return nice_type;
    }

    public void setNice_type(int nice_type) {
        this.nice_type = nice_type;
    }

    public String getNice_name() {
        return nice_name;
    }

    public void setNice_name(String nice_name) {
        this.nice_name = nice_name;
    }

}
