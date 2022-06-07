package com.jiamingku.jvm.classloder.path;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import sun.misc.CompoundEnumeration;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * 1java获取资源,主要有下面的四个
 * 1.InputStream is = this.getClass().getClassLoader().getResourceAsStream("special_orders.txt”);
 * <p>
 * 2.URL url = this.getClass().getClassLoader().getResource("special_orders.txt");
 * <p>
 * 3.InputStream is = this.getClass().getResourceAsStream();
 * <p>
 * 4.URL url1 = this.getClass().getResource("/init.properties");
 * <p>
 * -----------------------------------------------------------------------------------------------
 * [getResourceAsStream=class,classLoader--方法名字相同,一个是class,一个是classloader]
 * 高度在上升点:
 * <p>
 * 1.calss.getResourceAsStream是站在类的基础上来进行加载.
 * <p>
 * 2.classLoader.getResourceAsStream是站在jvm的基础上.
 * <p>
 * 本质都是通过classLoader进行处理的,使用Class就是多一步参数的处理(路径处理),然后还是调用ClassLoader进行处理
 * <p>
 * [getResource]***** getResourceAsStream里面调的还是getResource就是多了一步URL.open()
 * <p>
 * [总结]
 * 获取资源的时候也有一种双亲委派模型在里面,在获取搜索的时候是一级级的遍历搜,
 * <p>
 * 类加载:有个双亲委派模型, 同理获取资源的时候，里面也有一种双亲委派模型
 * <p>
 * 类加载:loadClass-->parent.loadClass(name, false)--->findClass
 * <p>
 * 找资源:getRes   -->parent.getResource(name)      --->findResource
 * <p>
 * 所以最先生效的资源是顶级路径下面的资源(同名资源顶级目录优先)
 */
@SuppressWarnings("all")
public class ImportantGetResourcePath {

    /**
     * 1.[getResourceAsStream],本质都是通过classLoader进行加载的
     * 2.区别通过Class对象进行加载的,会进行java.lang.Class#resolveName(java.lang.String)'/'不进行处理,否者在类运行的路径进行处理
     * 3.classLoader不进行路径的处理
     */
    @SuppressWarnings("all")
    @Test
    public void testResourceAsStream() throws Exception {
        Properties prop = new Properties();

        /* 仔细观察路径*/
        InputStream inputStream1 = ImportantGetResourcePath.class.getClassLoader().getResourceAsStream("com/jiamingku/j2se/refelct/1.properties");
        Properties properties = new Properties();
        properties.load(inputStream1);
        Object o2 = properties.get("key");
        System.out.println("o2 = " + o2);

        /* 仔细观察路径*/
        InputStream inputStream2 = ImportantGetResourcePath.class.getClassLoader().getResourceAsStream("2.properties");
        properties = new Properties();
        properties.load(inputStream2);
        o2 = properties.get("key");
        System.out.println("o2 = " + o2);


        // 这个路径 -- class所在的路径
//         InputStream inputStream = classLoader.getResourceAsStream("/1.properties");
        // 1.properties--这个是错误的
        InputStream inputStream3 = ImportantGetResourcePath.class.getResourceAsStream("2.properties");

        properties = new Properties();
        properties.load(inputStream3);
        o2 = properties.get("key");
        System.out.println("o2 = " + o2);

        URL inputStream4 = ImportantGetResourcePath.class.getResource("2.properties");

        properties = new Properties();
        properties.load(inputStream4.openStream());
        o2 = properties.get("key");
        System.out.println("o2 = " + o2);

    }


    @Test
    public void testfjsldflskdfsdsdfdfs() {
        URL resource = ImportantGetResourcePath.class.getClassLoader().getResource("2.properties");

        System.out.println("resource = " + resource.getPath());
    }

    /**
     * 与GetStream()区别就是 就是 一个url.openStream()的区别
     */
    @SuppressWarnings("all")
    @Test
    public void testGetResource() throws Exception {
        URL url = this.getClass().getClassLoader().getResource("1.properties");
        System.out.println("key = " + url);


        InputStream inputStream2 = ImportantGetResourcePath.class.getClassLoader().getResourceAsStream("1.properties");
        Properties properties = new Properties();
        properties.load(inputStream2);
        Object o2 = properties.get("key");
        System.out.println("o2 = " + o2);

        /** 如果没传递-就获取类的包路径*/
        URL url1 = this.getClass().getResource("");
        System.out.println("url1 = " + url1);

        /** 如果是classLoader就获取跟路径.*/
        url1 = this.getClass().getClassLoader().getResource("");
        String path = url1.getPath();
        System.out.println("path = " + path);
        System.out.println("url1 = " + url1);

        System.out.println(" ===================================================== ");
        Enumeration<URL> resources = this.getClass().getClassLoader().getResources("");
        while (resources.hasMoreElements()) {
            URL url2 = resources.nextElement();
            System.out.println("url2 = " + url2);
        }

        System.out.println(" ===================================================== ");
        URLClassLoader o = (URLClassLoader)this.getClass().getClassLoader();
        for (URL urlo : o.getURLs()) {
            System.out.println("urlo = " + urlo);
        }

    }

    /**
     * 反斜杠开头的话会在classPath的路径中去寻找
     */

    @Test
    public void testClassGetResourceAsStream() {
        /**
         * 绝对路径
         */
        Properties prop = new Properties();
        InputStream inputStream = ImportantGetResourcePath.class.getResourceAsStream("/2.properties");
        try {
            prop.load(inputStream);
            Object o = prop.get("key");
            System.out.println("o = " + o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 相对路径
         */
        Properties prop1 = new Properties();
        InputStream inputStream1 = ImportantGetResourcePath.class.getResourceAsStream("2.properties");
        try {
            prop1.load(inputStream1);
            Object o1 = prop1.get("key");
            System.out.println("o = " + o1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testClassGetResource() throws Exception{
        URL url = this.getClass().getResource("1.properties");
        System.out.println("url = " + url);

        url = this.getClass().getResource("");
        System.out.println("url = " + url);

        url = this.getClass().getResource("/2.properties");
        System.out.println("url = " + url);


        URL com = ClassLoader.getSystemResource("com");
        System.out.println("com = " + com);

        System.out.println(" ========================= " );
        System.out.println(" ========================= " );
        System.out.println(" ========================= " );
        Enumeration<URL> com1 = ClassLoader.getSystemClassLoader().getResources("jiamingku");
        while (com1.hasMoreElements()) {
            URL url1 = com1.nextElement();
            System.out.println("url1 = " + url1);
        }


    }

    /**
     * 这种方式读取属性文件,会一级级的读取双亲委派,所以相同的名字读取到那个之后就使用那个。不会有覆盖的情况
     * <p>
     * 随便打一个包，里面包含2.properties, 放在extClassloader加载路径中
     *
     * @throws Exception
     */
    @SuppressWarnings("all")
    @Test
    public void testClassGetResourceContainSameNameFile() throws Exception {
        URL url = this.getClass().getResource("/2.properties");
        System.out.println("url = " + url);
        Properties prop = new Properties();
        prop.load(url.openStream());
        String o = prop.get("key").toString();
        System.out.println("o = " + o);
    }

    @Test
    public void testLoads() throws Exception {
        Enumeration<URL> resources = ImportantGetResourcePath.class.getClassLoader().getResources("2.properties");

        while(resources.hasMoreElements()) {
            URL url = resources.nextElement();
            System.out.println("url = " + url);
        }
    }

    public static void main(String[] args) {
        ImportantGetResourcePath t = new ImportantGetResourcePath();
    }

    // ----------------------------------------------------------------------------------------------------------------

    @SuppressWarnings("all")
    @Test
    public void testResourceAsStreamSSSSS() {
        Properties prop = new Properties();
        // InputStream inputStream1 = ImportantGetResourcePath.class.getClassLoader().getResourceAsStream("com/jiamingku/j2se/refelct/classloader/1.properties");

        try {
            int count = 0;
            Enumeration<URL> inputStream1 = ImportantGetResourcePath.class.getClassLoader().getResources("2.properties");
            while (inputStream1.hasMoreElements()) {
                count++;
                System.out.println(" inputStream1.getClass().getSimpleName() = " + inputStream1.getClass().getSimpleName());
                CompoundEnumeration<URL> compoundEnumeration = (CompoundEnumeration) inputStream1;
                while (compoundEnumeration.hasMoreElements()) {
                    URL url = compoundEnumeration.nextElement();
                    System.out.println(url);
                }

            }
            System.out.println("count:" + count);

            System.out.println("-----------------------");
            System.out.println("-----------------------");
            System.out.println("-----------------------");
            System.out.println("-----------------------");

            URL u = ImportantGetResourcePath.class.getClassLoader().getResource("2.properties");
            System.out.println(u.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testResourceAsStreamSSSSS11() {
        Properties prop = new Properties();
        // InputStream inputStream1 = ImportantGetResourcePath.class.getClassLoader().getResourceAsStream("com/jiamingku/j2se/refelct/classloader/1.properties");
        try {
            int count = 0;
            String path = ImportantGetResourcePath.class.getClassLoader().getResource("").getPath();
            System.out.println(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTTTT() {
        ClassLoader.getSystemResource("");


    }
    // ----------------------spring 测试::::
    @Test
    public void test() {
        Set<Resource> sets = new HashSet<>();
        addAllClassLoaderJarRoots(this.getClass().getClassLoader(), sets);
        sets.forEach(System.out::println);
    }

    private static Logger logger = LoggerFactory.getLogger("ddd");

    public static void addAllClassLoaderJarRoots(ClassLoader classLoader, Set<Resource> result) {
        if (classLoader instanceof URLClassLoader) {
            try {
                for (URL url : ((URLClassLoader) classLoader).getURLs()) {
                    try {
                        UrlResource jarResource = (ResourceUtils.URL_PROTOCOL_JAR.equals(url.getProtocol()) ?
                                new UrlResource(url) :
                                new UrlResource(ResourceUtils.JAR_URL_PREFIX + url + ResourceUtils.JAR_URL_SEPARATOR));
                        if (jarResource.exists()) {
                            result.add(jarResource);
                        }
                    } catch (MalformedURLException ex) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("Cannot search for matching files underneath [" + url +
                                    "] because it cannot be converted to a valid 'jar:' URL: " + ex.getMessage());
                        }
                    }
                }
            } catch (Exception ex) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Cannot introspect jar files since ClassLoader [" + classLoader +
                            "] does not support 'getURLs()': " + ex);
                }
            }
        }

        if (classLoader == ClassLoader.getSystemClassLoader()) {
            // "java.class.path" manifest evaluation...
            addClassPathManifestEntries(result);
        }

        if (classLoader != null) {
            try {
                // Hierarchy traversal...
                addAllClassLoaderJarRoots(classLoader.getParent(), result);
            } catch (Exception ex) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Cannot introspect jar files in parent ClassLoader since [" + classLoader +
                            "] does not support 'getParent()': " + ex);
                }
            }
        }
    }


    protected static void addClassPathManifestEntries(Set<Resource> result) {
        try {
            String javaClassPathProperty = System.getProperty("java.class.path");
            for (String path : StringUtils.delimitedListToStringArray(
                    javaClassPathProperty, System.getProperty("path.separator"))) {
                try {
                    String filePath = new File(path).getAbsolutePath();
                    int prefixIndex = filePath.indexOf(':');
                    if (prefixIndex == 1) {
                        // Possibly "c:" drive prefix on Windows, to be upper-cased for proper duplicate detection
                        filePath = StringUtils.capitalize(filePath);
                    }
                    UrlResource jarResource = new UrlResource(ResourceUtils.JAR_URL_PREFIX +
                            ResourceUtils.FILE_URL_PREFIX + filePath + ResourceUtils.JAR_URL_SEPARATOR);
                    // Potentially overlapping with URLClassLoader.getURLs() result above!
                    if (!result.contains(jarResource) && !hasDuplicate(filePath, result) && jarResource.exists()) {
                        result.add(jarResource);
                    }
                } catch (MalformedURLException ex) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Cannot search for matching files underneath [" + path +
                                "] because it cannot be converted to a valid 'jar:' URL: " + ex.getMessage());
                    }
                }
            }
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                logger.debug("Failed to evaluate 'java.class.path' manifest entries: " + ex);
            }
        }
    }


    private static boolean hasDuplicate(String filePath, Set<Resource> result) {
        if (result.isEmpty()) {
            return false;
        }
        String duplicatePath = (filePath.startsWith("/") ? filePath.substring(1) : "/" + filePath);
        try {
            return result.contains(new UrlResource(ResourceUtils.JAR_URL_PREFIX + ResourceUtils.FILE_URL_PREFIX +
                    duplicatePath + ResourceUtils.JAR_URL_SEPARATOR));
        } catch (MalformedURLException ex) {
            // Ignore: just for testing against duplicate.
            return false;
        }
    }


}
