package com.jiamingku.collection.guava;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;
import com.google.common.collect.Tables;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jiamingku on 2019/1/14.
 */
public class TableTest {

	/**
	 * 通过rowKey获取map对象
	 * <p>
	 * tables.put("a", "javase", 81); ----81会覆盖80
	 */
	@Test
	@SuppressWarnings("all")
	public void method1() {
		Table<String, String, Integer> tables = HashBasedTable.create();
		tables.put("a", "javase", 80);
		tables.put("b", "javaee", 90);
		tables.put("c", "javame", 100);
		tables.put("d", "guava", 70);
		tables.put("a", "javase1", 81);

		List<String> students = Arrays.asList("a", "b", "c", "d");

		for (String str : students) {
			Map<String, Integer> rowMap = tables.row(str);
			Set<Map.Entry<String, Integer>> setEntry = rowMap.entrySet();
			for (Map.Entry<String, Integer> entry : setEntry) {
				System.out.println(entry.getKey() + " " + entry.getValue());
			}
		}
	}

	@Test
	@SuppressWarnings("all")
	public void method2() {
		Table<String, String, Integer> tables = HashBasedTable.create();
		tables.put("a", "javase", 80);
		tables.put("a1", "javase", 80);
		tables.put("b", "javaee", 90);
		tables.put("c", "javame", 100);
		tables.put("d", "guava", 70);
		tables.put("a", "javase1", 81);

		List<String> courses = Arrays.asList("javase", "javaee", "javame", "guava");

		for (String str : courses) {
			Map<String, Integer> rowMap = tables.column(str);
			Set<Map.Entry<String, Integer>> setEntry = rowMap.entrySet();
			for (Map.Entry<String, Integer> entry : setEntry) {
				System.out.println(entry.getKey() + " " + entry.getValue());
			}
			System.out.println(" ===== ");
		}
	}

	@Test
	@SuppressWarnings("all")
	public void cellset2() {
		Table<String, String, Integer> table = HashBasedTable.create();
		table.put("a", "javase", 80);
		table.put("a", "javase", 81); // 多了一个81
		table.put("b", "javase", 90);
		table.put("a", "javame", 100);
		table.put("d", "guava", 70);

		//得到所有的行数据
		Set<Table.Cell<String, String, Integer>> cellset = table.cellSet();

		for (Cell<String, String, Integer> temp : cellset) {
			System.out.println(temp.getRowKey() + "--" + temp.getColumnKey() + "--" + temp.getValue());
		}
	}

	@Test
	@SuppressWarnings("all")
	public void transpose() {
		Table<String, String, Integer> table = HashBasedTable.create();
		table.put("a", "javase", 80);
		table.put("b", "javase", 90);
		table.put("a", "javame", 100);
		table.put("d", "guava", 70);

		System.out.println("-------rowKey和columnKey转换---------");
		Table<String, String, Integer> table1 = Tables.transpose(table);
		Set<Cell<String, String, Integer>> cellset1 = table1.cellSet();

		for (Cell<String, String, Integer> temp : cellset1) {
			System.out.println(temp.getRowKey() + "--" + temp.getColumnKey() + "--" + temp.getValue());
		}
	}

	@Test
	@SuppressWarnings("all")
	public void columnKeySet() {
		System.out.println("-------查询学生的成绩(课程维度)---------");
		Table<String, String, Integer> table = HashBasedTable.create();
		table.put("a", "javase", 80);
		table.put("b", "javase", 90);
		table.put("a", "javame", 100);
		table.put("d", "guava", 70);

		Set<String> cours = table.columnKeySet();
		for (String temp : cours) {
			System.out.println(temp + "\t");
		}
		System.out.println(" ======== " );
		Set<String> stu = table.rowKeySet();
		for (String temp : stu) {
			System.out.println("sku:" + temp);
			Map<String, Integer> map = table.row(temp);
			for (String temp1 : cours) {
				System.out.println( map.get(temp1));
			}
			System.out.println("===");
		}
	}




	public static void main(String[] args) {
		Table<String, String, Integer> table = HashBasedTable.create();
		table.put("a", "javase", 80);
		table.put("b", "javase", 90);
		table.put("a", "javame", 100);
		table.put("d", "guava", 70);

		System.out.println("-------学生维度---------");
		Set<String> stu1 = table.rowKeySet();
		for (String temp : stu1) {
			System.out.println(temp + "\t");
		}

		Set<String> cours1 = table.columnKeySet();
		for (String temp : cours1) {
			System.out.println(temp);
			Map<String, Integer> map1 = table.column(temp);
			for (String temp1 : stu1) {
				System.out.println(temp1 + "\t" + map1.get(temp1));
			}
			System.out.println();
		}
	}
}
