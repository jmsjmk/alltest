package com.jiamingku.collection.guava;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import org.junit.Test;

import java.util.*;

/**
 * Created by jiamingku on 2019/1/3.
 */
public class TableTest12 {


    @Test
    public void ttt() {
        TableTest12 s = (TableTest12)null;
        System.out.println(s);
    }

    // https://www.cnblogs.com/peida/p/3183505.html
    @Test
    public void TableTest1() {
        Table<String, Integer, String> aTable = HashBasedTable.create();
        for (char a = 'A'; a <= 'C'; ++a) {
            for (Integer b = 1; b <= 3; ++b) {
                aTable.put(Character.toString(a), b, String.format("%c%d", a, b));
            }
        }
        System.out.println("aTable = " + aTable);
        Map<String, String> maps = new HashMap<>();
        maps.put("a", "b");
        maps.put("c", "d");
        System.out.println("maps = " + maps);
        Map<String, Map> mapsss = new HashMap<>();
        mapsss.put("a", maps);
        System.out.println("mapsss = " + mapsss);
        System.out.println("================================ ");

        System.out.println(aTable.column(2));

        System.out.println("=================================");
        System.out.println(aTable.row("B"));
        System.out.println("=================================");
        System.out.println(aTable.get("B", 2));
        System.out.println("=================================");

        System.out.println(aTable.contains("D", 1));
        System.out.println(aTable.containsColumn(3));

        System.out.println(aTable.containsRow("C"));
        System.out.println(aTable.columnMap());

        System.out.println(aTable.rowMap());
        System.out.println(aTable.remove("B", 3));
        System.out.println(aTable.rowMap());
    }


    public static Table<String, String, Integer> getTable() {
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("黎明", "javase", 80);
        table.put("黎明", "oracle", 100);
        table.put("郭富城", "javase", 90);
        table.put("刘德华", "oracle", 95);
        return table;
    }


    /**
     * 只是便利一下
     */
    @Test
    public void test1() {
        Table<String, String, Integer> table = getTable();
        Set<Table.Cell<String, String, Integer>> cells = table.cellSet();
        //每行来输出STDOUT
        for (Table.Cell<String, String, Integer> c : cells) {
            System.out.println(c.getRowKey() + "----->" + c.getColumnKey() + "----->" + c.getValue());
        }
    }


    @Test
    public void test21() {
        Table<List<Integer>, String, String> table = HashBasedTable.create();
        List<Integer> list1 = Lists.newArrayList(1111110, 21, 12);
        List<Integer> list2 = Lists.newArrayList(22220, 21, 12);
        List<Integer> list3 = Lists.newArrayList(33333, 21, 12);

        table.put(list1, "in1", "业务描述111111111111111");
        table.put(list2, "in2", "业务描述222222222222");
        table.put(list3, "in3", "业务描述333333333333");

        Set<Table.Cell<List<Integer>, String, String>> cells = table.cellSet();
        //每行来输出STDOUT
        for (Table.Cell<List<Integer>, String, String> c : cells) {
            System.out.println(c.getRowKey() + "----->" + c.getColumnKey() + "----->" + c.getValue());
        }
    }

    /**
     * rowKeySet
     * Table<String, String, Integer> table = HashBasedTable.create();
     * table.put("黎明", "javase", 80);
     * table.put("黎明", "oracle", 100);
     * table.put("郭富城", "javase", 90);
     * table.put("刘德华", "oracle", 95);
     * return table;
     */
    @Test
    public void test2() {
        Table<String, String, Integer> table = getTable();
        Set<String> stus = table.rowKeySet();
        for (String temp : stus)
            System.out.print(temp + "\t");
        System.out.println();
        System.out.println("=============== ");

//		table.row("黎明").keySet()

        Set<String> courses = table.columnKeySet();
        for (String c : courses) {
            System.out.print(c + "\t");
            //获得学生和成绩得map，学生为键
            Map<String, Integer> scores = table.column(c);
            for (String stu : stus)
                System.out.print(stu + ":" + scores.get(stu) + "\t");
            System.out.println();
        }
    }

    /**
     * rowKeySet
     * Table<String, String, Integer> table = HashBasedTable.create();
     * table.put("黎明", "javase", 80);
     * table.put("黎明", "oracle", 100);
     * table.put("郭富城", "javase", 90);
     * table.put("刘德华", "oracle", 95);
     * return table;
     */
    public static void main(String[] args) {
        // 初始化
        Table<String, String, Integer> table = getTable();
        //原理同上
        System.out.println("==================课程对应的学生成绩===================");
        System.out.print("课程\t");
        Set<String> coures2 = table.columnKeySet();
        for (String temp : coures2)
            System.out.print(temp + "\t");
        System.out.println();

        Set<String> stus2 = table.rowKeySet();
        for (String s : stus2) {
            System.out.print(s + "\t");
            Map<String, Integer> scores = table.row(s);
            for (String c : coures2)
                System.out.print(c + ":" + scores.get(c) + "\t");
            System.out.println();
        }

        System.out.println("stus2 = ");
        System.out.println("stus2 = ");
        System.out.println("stus2 = ");
        System.out.println("stus2 = ");
        System.out.println("stus2 = ");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(null);
        boolean b = list.contains(null);
        System.out.println("b = " + b);
    }

}
