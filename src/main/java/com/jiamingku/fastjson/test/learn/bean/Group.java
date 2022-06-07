package com.jiamingku.fastjson.test.learn.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by jiamingku on 2017/4/11.
 */
public class Group {
    List<User> users;
    Map<String, User> maps;
    private String name;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Date create;

    public Map<String, User> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, User> maps) {
        this.maps = maps;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Group{");
        sb.append("create=").append(create);
        sb.append(", users=").append(users);
        sb.append(", maps=").append(maps);
        sb.append(", name='").append(name).append('\'');
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
