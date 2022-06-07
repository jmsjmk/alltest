package com.jiamingku.network.io;

import org.junit.Test;

import java.io.*;
import java.util.Random;

/**
 * 1.FileOutputStream:可以获取一个文件描述符
 * 2.两种方式都是系统调用-write(int b) write(int[] b, x,y)-buffer效率高就是减少了系统调用.
 * 3.包装类BufferedOutputStream,其实里面只是包装了一层自定义的byte[]数组
 * 在尺寸满的时候执行一次刷新数据-一样的道理就是减少系统调用
 * <p>
 * ============================================================
 * flush(一般都是带缓存的数据), 本质就是调用系统的写数组.
 * <p>
 * FileOutputStream
 * BufferedOutputStream
 * <p>
 * =====
 * FileWriter  其实就是带了一种编码器在里面(最终也是按照字节写出去-但是他里面有线程同步代码)
 * <p>
 * BufferedWriter ---都是带缓存的目的就是减少系统的调用
 */
public class FileOutputStreamTest {


    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("test.txt"));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        File writeName = new File("test");
        FileWriter writer = new FileWriter(writeName, true);
        writer.write("你");
        writer.write(10);
        char[] c = {'1', '2'};
        writer.write(c);
        BufferedWriter out = new BufferedWriter(writer);
        out.write("我会写入文件啦1"); // \r\n即为换行
        out.write(100);

        int a = 0;
        while (a < 1000) {
            fileOutputStream.write(127);
            byte[] bytes = new byte[1023];
            fileOutputStream.write(bytes);

            bufferedOutputStream.write(100);
            a++;
        }
        fileOutputStream.flush();
    }

    @Test
    public void deleteExceSuccess() throws Exception {
        File readName = new File("ddd");
        readName.delete();
        readName.renameTo(readName);
    }

    @Test
    public void filePath() throws IOException {
        File f = new File("createFile.txt");
        String absolutePath = f.getAbsolutePath();
        String path = f.getPath();
        if (!f.exists()) {
            f.createNewFile();
        }
        boolean b = !f.getParentFile().exists();
        // /Users/jiamingku/IdeaProjects/alltest/createFile.txt
        System.out.println("path = " + path);
        File f1 = new File("load/createFile.txt");
        if (!f.getParentFile().exists()) {
            System.out.println("f.getParentFile().getAbsolutePath() = " + f.getParentFile().getAbsolutePath());
            boolean mi = f.getParentFile().mkdirs();
            System.out.println("mi = " + mi);
            boolean b1 = f.createNewFile();
            System.out.println("b = " + b1);
        }
        // /Users/jiamingku/IdeaProjects/alltest/createFile.txt
        System.out.println("path = " + path);
    }


    /**
     * 写入TXT文件
     * 默认写文件非追加的方式
     */
    @Test
    public void writeFile() throws Exception {
        String path = "load/createFile.txt";
        String line = System.getProperty("line.separator");
        File writeName = new File(path); // 相对路径，如果没有则要建立一个新的output.txt文件
        if (!writeName.exists()) {
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
        }
        // true 追加与不追加的问题
        Random random = new Random();
        try {
            FileWriter writer = new FileWriter(writeName, true);
            BufferedWriter out = new BufferedWriter(writer);
            out.write("我会写入文件啦1" + line); // \r\n即为换行
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void processFile() {
        String DB_LOCAL_BAK = "/data/stock_db_exception_temp1.txt";
        String fileNme = "/data/stock_db_exception1.txt";
        long begin = System.currentTimeMillis();
        File readFile = new File(fileNme);
        File writeFile = new File(DB_LOCAL_BAK);
        BufferedReader reader = null;
        FileWriter writer = null;
        BufferedWriter out = null;
        String line;
        int successCnt = 0;
        int failureCnt = 0;
        try {
            reader = new BufferedReader(new FileReader(readFile));
            writer = new FileWriter(writeFile, true);
            out = new BufferedWriter(writer);
            out.write("333");
            out.flush();
            // 2.
//            synchronized (StockUtils.LOCK) {
            while ((line = reader.readLine()) != null) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 3.
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
