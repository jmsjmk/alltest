package com.framework.model.observable.event.propertyeditorsupporttest.base;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * java bean的事件模型
 */
public class PropertyChangeSupportNote {

    public static void main(String[] args) {
        // 创建对象
        User mUser = new User();
        // 添加一个监听器
        mUser.addPropertyChangeListener(new UserPropertyChangeListener(mUser));
        mUser.setUsername("username1");
        mUser.setPassword("password1");
        System.out.println(mUser);
    }

    /**
     * 属性改变监听器
     *
     * @author Administrator
     */
    protected static class UserPropertyChangeListener implements PropertyChangeListener {
        private User user;
        public UserPropertyChangeListener(User user) {
            super();
            this.user = user;
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            System.out.println(evt.getPropertyName());
            if ("username".equals(evt.getPropertyName())) {
                System.out.println("事件监听器::" + this.user.getUsername() == evt.getOldValue());
                System.out.println("事件监听器::" + this.user.getUsername() == evt.getNewValue());
            }
        }
    }

    /**
     * javaBean
     */
    protected static class User {
        private final PropertyChangeSupport support = new PropertyChangeSupport(this);
        private int id;
        private String username;
        private String password;

        public void addPropertyChangeListener(PropertyChangeListener listener) {
            support.addPropertyChangeListener(listener);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            String oldUsername = this.username;
            this.username = username;
            // 触发属性改变事件
            support.firePropertyChange("username", oldUsername, this.username);
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            String oldPassword = this.password;
            this.password = password;
            // 这个是非常关键的触发事件
            support.firePropertyChange("password", oldPassword, this.password);
        }

        @Override
        public String toString() {
            return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
        }

    }

}