注解可以写的位置,Class, Method, Constract, Field.所以要获取这些信息这几个类必须有相应的方法.
JDK1.5之后扩展了返回接口，上面的类都现实了AnnotatedElement.也就是说上面那几个类就具有了获取注解的能力.

========================================================================
java注解是对原数据(metadata)的支持.可以把注解理解为参数的一种(注解来传递参数)

注解的运行时机:(retention) :在编译：在运行：类加载：
================================
@Retention用来定义该注解在哪一个级别可用，在源代码中[SURCE],类文件中[CLASS]，或者运行[RUNTIME]
@Retention，表示需要在什么级别保存该注解信息。可选的RetentionPolicy参数包括：
–SOURCE，注解将被编译器丢弃 ---javac -processor 可以获取注解信息

–CLASS，注解在编译成class文件的过程中可用,载入JVM丢弃[不读入内存]

–RUNTIME, VM将在运行期也保留注解，因此可以通过反射机制读取注解的信息,在jvm装载*.class文件时候读取保存在class文件中的annotation
=================================
1.apt: annotation process tool 注解处理工具
2.java 还提供了注解的注解(metadata annotation)，修饰注解的注解
为什么可以修饰注解？自己可以定义源注解么？当然可以原因就是@target 修饰的就是可以修饰的类型，如果你是annotation_type的话就只能修饰注解

就是修饰注解的注解，常用的有4个(只能修饰注解)

@retention            ：控制访问的时机,属性设置class的时候在编译的时候会获取对应的信息,进行处理就像
@Inherited            ：翻译过来叫继承的意识
@target               ：修饰那个属性，方法，字段。类。
@Documented           ：在生成doc的时候会有用

=================================
#多属性赋值
@AliasFor(value = "classes", annotation = ContextConfiguration.class)
#数组赋值
@Target({ ElementType.TYPE, ElementType.METHOD })
=================================
rt.jar(包含java.lang)
所有的注解都继承java.lang.Annotation接口
所有的枚举都继承java.lang.Enum的子类,所以枚举有很多方法


