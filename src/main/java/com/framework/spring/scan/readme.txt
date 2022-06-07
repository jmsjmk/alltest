
 https://blog.csdn.net/u012325167/article/details/75388990     ** / *
——后面两种其实是一样的,一句话 *只能匹配目录并且是一级目录
base-package="controller.*"时，可见packageSearchPath为"classpath*:controller/*/**/*.class”：
base-package="controller"时，可见packageSearchPath为"classpath*:controller/**/*.class”：
base-package="controller.**"时，可见packageSearchPath为"classpath*:controller/**/**/*.class"：
com.jms.erp.SpringTestScanPackage#t     all test有一样的代码