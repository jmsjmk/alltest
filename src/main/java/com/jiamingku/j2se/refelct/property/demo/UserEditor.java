package com.jiamingku.j2se.refelct.property.demo;
 
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
 
/**
 * java自带的属性编辑器; PropertyEditor---继承 PropertyEditorSupport可以方便的上线属性继承
 *
 * 编辑器
 */
public class UserEditor extends PropertyEditorSupport {
   
   public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
   /**
    * 重写从一个字符串String变成bean的方法
    *
    * 1.传递过来一个字符串
    * 2.构建一个使用的对象,设置对应的属性
    * 3.将新构建的这个对象,筛入属性编辑器中.
    */
   @Override
   public void setAsText(String text) throws IllegalArgumentException{
      String[] tokens = text.split("\\|");
      User user = new User();
      System.out.println("user.hashCode() = " + user.hashCode());
      user.setName(tokens[0]);
      user.setEmail(tokens[1]);
      try{
         user.setDate(sdf.parse(tokens[2]));
      }catch(ParseException e){
         throw new IllegalArgumentException(e);
      }
      // --这个地方将属性值设置进入.----后面在使用的时候会相应的对应上
      setValue(user);
   }
}