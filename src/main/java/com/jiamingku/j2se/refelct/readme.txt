1.常用的api搞定就可以,通过反射进行操作就行.简单的理解就是通过Class对象去操作RTTI可以使用的所有操作.

2.泛型可以使用的位置:
1.类,接口
2.方法参数,泛型方法
3.字段
4.构造器
5.数组

----------------------------------------------
就拿注解来说: 注解可以修饰在方法, 构造器, 字段, 类 ,所以这些组建都应该有获取注解的能力,或者判断是否有这个注解
  所以他们都实现了AnnotatedElement.这个接口中有跟注解相关的的方法.


----------------------------------------------
同样java还提供了一套api可以操作跟反射类似的功能.(参考子包property)

https://blog.csdn.net/shenchaohao12321/article/details/80295371
https://blog.csdn.net/weixin_42069143/article/details/82119724

JavaBean规范通过java.beans.PropertyEditor定义了设置JavaBean属性的方法，
通过BeanInfo描述了JavaBean哪些属性是可定制的，此外还描述了可定制属性与PropertyEditor的对应关系。

----------------------------------------
PropertyEditor:
1.任何实现java.beans.PropertyEditor接口的类都是属性编辑器。属性编辑器的主要功能就是将外部的设置值转换为JVM内部的对应类型，
所以属性编辑器其实就是一个类型转换器。

2.Java为PropertyEditor提供了一个方便的实现类：PropertyEditorSupport，该类实现了PropertyEditor接口并提供默认实现，
一般情况下，用户可以通过扩展这个方便类设计自己的属性编辑器。

BeanInfo:


PropertyDescriptor:


Introspector:

