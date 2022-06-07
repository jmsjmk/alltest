package com.framework.model.observable.event.propertyeditorsupporttest;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 编辑器(事件发送者)
 */
public class UserEditor extends PropertyEditorSupport {
   
   public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

   /**
    * 重写从一个字符串String变成bean的方法
    */
   @Override
   public void setAsText(String text) throws IllegalArgumentException{
      String[] tokens = text.split("\\|");
      User user = new User();
      user.setName(tokens[0]);
      user.setEmail(tokens[1]);
      try{
         user.setDate(sdf.parse(tokens[2]));
      }catch(ParseException e){
         throw new IllegalArgumentException(e);
      }
      setValue(user);
   }
}
