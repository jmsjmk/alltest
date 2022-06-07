package com.framework.model.observable.event.propertyeditorsupporttest;

import java.util.Date;

public class User {


   private String name;
   private String email;
   private Date date;


   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public Date getDate() {
      return date;
   }
   public void setDate(Date date) {
      this.date = date;
   }

   @Override
   public String toString() {
      final StringBuffer sb = new StringBuffer("User{");
      sb.append("date=").append(date);
      sb.append(", name='").append(name).append('\'');
      sb.append(", email='").append(email).append('\'');
      sb.append('}');
      return sb.toString();
   }
}
