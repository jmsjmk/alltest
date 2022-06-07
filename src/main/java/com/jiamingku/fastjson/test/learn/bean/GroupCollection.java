package com.jiamingku.fastjson.test.learn.bean;

import java.util.List;

/**
 * Created by jiamingku on 2017/4/11.
 */
public class GroupCollection {
    private String gpname;
    private String gpage;

    private List<Group> groups;


    public String getGpage() {
        return gpage;
    }

    public void setGpage(String gpage) {
        this.gpage = gpage;
    }

    public String getGpname() {
        return gpname;
    }

    public void setGpname(String gpname) {
        this.gpname = gpname;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
