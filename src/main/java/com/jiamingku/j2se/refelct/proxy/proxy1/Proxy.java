package com.jiamingku.j2se.refelct.proxy.proxy1;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

/**
 * 代理工厂创建代理对象
 */
public class Proxy {

    /**
     * @param infce 代理接口
     * @param h     对应我们的逻辑
     * @return 返回代理对象
     * @throws Exception
     */
    public static Object newProxyInstance(Class infce, InvocationHandler h) throws Exception {

        //JDK6 Complier API, CGLib, ASM
        String methodStr = "";
        String rt = "\r\n";

        Method[] methods = infce.getMethods();
        // 如果按照下面的方式写,那就是静态代码
        /*
        for(Method m : methods) {
			methodStr += "@Override" + rt + 
						 "public void " + m.getName() + "() {" + rt +
						 	"   long start = System.currentTimeMillis();" + rt +
							"   System.out.println(\"starttime:\" + start);" + rt +
							"   t." + m.getName() + "();" + rt +
							"   long end = System.currentTimeMillis();" + rt +
							"   System.out.println(\"time:\" + (end-start));" + rt +
						 "}";
		}
		*/
        // 下面属于一种动态的代理
        // 最简单的理解，就是
        for (Method m : methods) {
            methodStr += "@Override" + rt +
                    "public void " + m.getName() + "() {" + rt +
                    "    try {" + rt +
                    "    Method md = " + infce.getName() + ".class.getMethod(\"" + m.getName() + "\");" + rt +
                    "    h.invoke(this, md);" + rt +
                    "    }catch(Exception e) {e.printStackTrace();}" + rt +

                    "}";
        }

        String src =
                "package com.jiamingku.proxy.proxy1;" + rt +
                        "import java.lang.reflect.Method;" + rt +
                        "import com.jiamingku.j2se.refelct.proxy.proxy1.InvocationHandler;" + rt +
                        "public class test$Proxy1 implements " + infce.getName() + "{" + rt +
                        "    public test$Proxy1(InvocationHandler h) {" + rt +
                        "        this.h = h;" + rt +
                        "    }" + rt +


                        "    com.jiamingku.j2se.refelct.proxy.proxy1.InvocationHandler h;" + rt +

                        methodStr +
                        "}";
        String fileName =
                "/Users/jiamingku/test/com/jiamingku/proxy/proxy1/test$Proxy1.java";
        File f = new File(fileName);

        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();

        }
        FileWriter fw = new FileWriter(f);


        fw.write(src);
        fw.flush();
        fw.close();

        //compile
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
        Iterable units = fileMgr.getJavaFileObjects(fileName);
        CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
        t.call();
        fileMgr.close();

        //load into memory and create an instance
        URL[] urls = new URL[]{new URL("file://" + "/Users/jiamingku/test/")};
        URLClassLoader ul = new URLClassLoader(urls);
        // Class c = ul.loadClass("com.jiamingku.j2se.refelct.proxy.proxy1.test$Proxy1");
        Class c = ul.loadClass("com.jiamingku.proxy.proxy1.test$Proxy1");
        System.out.println(c);
        System.out.println("-----------------");

        Constructor ctr = c.getConstructor(InvocationHandler.class);
        Object m = ctr.newInstance(h);
        //m.move();

        return m;
    }
}
