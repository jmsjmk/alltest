package com.jiamingku.network.io;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;


/**
 * 1.编码解码一致，但是显示乱码(数据没读全问题)
 *
 */
public class FileEncodingTest {

    private String tnofinal = "3";
    private final String tf = "3";

    @Test
    public void getJavaParameter() {
        System.out.println("java_vendor:" + System.getProperty("java.vendor"));
        System.out.println("java_vendor_url:" + System.getProperty("java.vendor.url"));
        System.out.println("java_home:" + System.getProperty("java.home"));
        System.out.println("java_class_version:" + System.getProperty("java.class.version"));
        System.out.println("java_class_path:" + System.getProperty("java.class.path"));
        System.out.println("os_name:" + System.getProperty("os.name"));
        System.out.println("os_arch:" + System.getProperty("os.arch"));
        System.out.println("os_version:" + System.getProperty("os.version"));
        System.out.println("user_name:" + System.getProperty("user.name"));
        System.out.println("user_home:" + System.getProperty("user.home"));
        System.out.println("user_dir:" + System.getProperty("user.dir"));
        System.out.println("java_vm_specification_version:" + System.getProperty("java.vm.specification.version"));
        System.out.println("java_vm_specification_vendor:" + System.getProperty("java.vm.specification.vendor"));
        System.out.println("java_vm_specification_name:" + System.getProperty("java.vm.specification.name"));
        System.out.println("java_vm_version:" + System.getProperty("java.vm.version"));
        System.out.println("java_vm_vendor:" + System.getProperty("java.vm.vendor"));
        System.out.println("java_vm_name:" + System.getProperty("java.vm.name"));
        System.out.println("java_ext_dirs:" + System.getProperty("java.ext.dirs"));
        System.out.println("file_separator:" + System.getProperty("file.separator"));
        System.out.println("path_separator:" + System.getProperty("path.separator"));
        System.out.println("line_separator:" + System.getProperty("line.separator"));
    }

    /**
     * 系统创建文件的位置就是uer.dir这个属性指定的目录，其实相当于pwd可以修改，但是的借助别的工具类，
     * 最终导致文件编码的东西就是你输出的内容
     */
    @Test
    public void testFileEncoding() {
        String dir = System.getProperty("user.dir");
        System.out.println("user.dir = " + dir);

        System.setProperty("user.dir", "/Users/jiamingku");
        String s = System.getProperty("user.dir");

        File file1 = new File("GBK.txt");
        File file2 = new File("UTF8.txt");
        try {
            FileOutputStream pw1 = new FileOutputStream(file1);
            FileOutputStream pw2 = new FileOutputStream(file2);
            System.out.println("中国");
            pw1.write("中国".getBytes("GBK")); //决定了文件打开的编码类型
            pw2.write("中国".getBytes("utf-8")); //决定了文件打开的编码类型
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t1() {
        String s = "严";
        try {
            byte[] b = s.getBytes();
            System.out.println("b.length = " + b.length);
            for (byte b1 : b) {
                System.out.println("b1 = " + b1);
                System.out.println("(int)b1 = " + (int) b1);
            }
            System.out.println("s1 = " + s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // http://www.mytju.com/classcode/tools/encode_gb2312.asp
        char s1 = '严';
        int a1 = s1;
        int a2 = 0X4F60;
        System.out.println("a2 = " + a2);
        System.out.println("s1 = " + a1);
        System.out.println("a2 = " + a2);
    }

    /**
     * 如果你按照英文字符集读取的时候，他会将读取 unicode里面的一个字节(一个字符读取一个字节)---长度是2
     * 如果你按照中文字符集读取的时候，他将会读取 unicode 里面的二个字节
     * 如果你按照 utf-8的字符集读取时候，他将会读取 unicode里面的三个字节
     * 也就是说不同的编码方式所用的字节不一样，一般来说你用什么编码，你就用什么解码应该是没错的,但是确保编码的字符集合能容纳被编码的字符串。比如中国 的“中” 你，
     */
    @Test
    public void testEncoding() {
        String str = "严";
        System.out.println("str = " + str);
        try {
            byte[] byte1 = str.getBytes("iso-8859-1");
            System.out.println("byte1.length = " + byte1.length);
            System.out.println("byte1[0] = " + byte1[0]);

            byte[] byte2 = str.getBytes("utf-8");
            System.out.println("byte2.length = " + byte2.length);
            System.out.println("byte1[0] = " + byte2[0]);
            System.out.println("byte1[0] = " + byte2[1]);
            System.out.println("byte1[0] = " + byte2[2]);
            byte[] byte3 = str.getBytes("gbk");
            System.out.println("byte3.length = " + byte3.length);
            System.out.println("byte3 = " + byte3[0]);
            System.out.println("byte3 = " + byte3[1]);
            //
            System.out.println(" =========================================== ");
            // 虽然编码解码一一致，但是还出现乱码的原因，
            // 编译时候你,好两个字都是unicode字符，但是运行时候，都是
            // utf-8的编码，在你按照iso8859-1的编码，时候他读取了三分一字节
            // 感觉上面有点不对，他的编码，好像都是unicode 转换对应的 gbk，或者 utf-8
            // 你从输出的字节就能看出来
            String s = new String(str.getBytes("iso-8859-1"), "iso-8859-1");
            System.out.println("s = " + s);
            byte[] bb = s.getBytes();
            System.out.println("bb[0] = " + bb[0]);

            String s1 = new String(str.getBytes("gbk"), "gbk");
            System.out.println("s1 = " + s1);
            String s3 = new String(str.getBytes("utf-8"), "utf-8");
            System.out.println("s3 = " + s3);

            String s4 = new String(str.getBytes("utf-16"), "utf-16");
            System.out.println("s4 = " + s4);

            System.out.println("Integer.toBinaryString(53711) = " + Integer.toBinaryString(53711));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    /**
     * 11100100 10111000 10100101  严的编码
     */
    @Test
    public void testEncodingRunTime() {
        String str = "严";
        System.out.println("str = " + str);
        try {
            byte[] b = str.getBytes("utf-8");
            System.out.println("b.length = " + b.length);
            // 11100100
            System.out.println("byte[0] = " + b[0]);
            // 10111000
            System.out.println("byte[1] = " + b[1]);
            // 10100101
            System.out.println("byte[2] = " + b[2]);

            System.out.println("str.toCharArray()[0] = " + (int) str.toCharArray()[0]);

            b = str.getBytes("gbk");
            System.out.println("b.length = " + b.length);
            System.out.println("byte[0] = " + b[0]);
            System.out.println("byte[1] = " + b[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
