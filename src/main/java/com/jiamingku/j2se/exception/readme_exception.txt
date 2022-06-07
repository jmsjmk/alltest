异常转译的好处: 异常返回值:

=============================
1.异常作用不用多说了,也没有那么复杂也就是一个java对象里面有数组.内部已经被jvm隐藏, 可以程序主动创建,或者代码发生异常之后产生.
  1.1 cause(Throwable 类型)里面的属性,没有指定就是当前异常
  1.2 StackTraceElement[](Throw)里的属性
  1.3 异常的几乎所有代码都是在throwable这类中进行维护的,Exception也没啥方法都是在父类中

2.重要非常重要的方法fillInStackTrace
   2.1 fillInStackTrace无参数的方法java方法
   2.2 一个参数方法就是native方法，这个对于java程序员来说就不用关心了,
   2.3 方法就是填充java异常的栈链信息,跟踪错误排查问题
   2.4 调用过程:
        异常产生
            ->fillInStackTrace()---java方法里面会触发调用下面
                ->fillInStackTrace(0)---这个会清空一次(stackTrace)--在使用UNASSIGNED_STACK赋予值这是本地方法做的事情


3.异常的构建过程-(super关键字要必须的了解, 不是指向父类对象,只是可以引用父类的属性跟方法)
   4.1 throwable对象中有个cause对象,在创建异常对象的时候 cause=this
   4.2 如果有异常明细消息的话就设置值
   4.3 最主要的就是设置数据栈链的相信信息，通过本地方法设置的
   4.4 StackTraceElement(打印栈信息就是这几个属性的值输出)
        4.1 declaringClass
        4.2 methodName
        4.3 fileName
        4.4 lineNumber
5.异常打印
   5.1 打印错误消息到标准的输出流
   5.2 private void printStackTrace(PrintStreamOrWriter s)
   5.3 cause 默认在创建的时候指向自己this, 但是在输出错误信息的时候如果cause=this，就不输出cause by了

==================上面是基本的使用异常==========构建的过程
扩展:从上面的对象来看，如果获取代码运行的行数呢？就是通过异常这种方式可以获取到