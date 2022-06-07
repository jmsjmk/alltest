package com.jiamingku.j2se.oop.getlineclassname;

import org.slf4j.Logger;

public final class LogUtils {
	
	private static class MsgAndArgs {
		public String msg;
		public Object[] args;
		public MsgAndArgs(String msg, Object[] args) {
			super();
			this.msg = msg;
			this.args = args;
		}
	}

	public static int getLineNumber() {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		String me = LogUtils.class.getName();
		int line = -1;
		String className = "";
		for (int i = 0, len = trace.length - 1; i < len; i++) {
			if (trace[i].getClassName().equals(me)) {
				line = trace[i + 1].getLineNumber();
				className = trace[i + 1].getClassName();
				int index = className.lastIndexOf('.');
				if(index > 0) {
					className = className.substring(index + 1);
				}
				break;
			}
		}
		return line;
	}
	
	private static MsgAndArgs get(StackTraceElement[] trace, Boolean b, String msg, Object... args) {
		if(args == null) {
			args = new Object[0];
		}
		if(msg == null) {
			msg = "";
		}
		String me = LogUtils.class.getName();
		Object[] newArr = new Object[args.length + 2];
		int line = -1;
		String className = "";
		for (int i = 0, len = trace.length - 1; i < len; i++) {
			if (trace[i].getClassName().equals(me)) {
				line = trace[i + 1].getLineNumber();
				className = trace[i + 1].getClassName();
				int index = className.lastIndexOf('.');
				if(index > 0) {
					className = className.substring(index + 1);
				}
				break;
			}
		}
		newArr[0] = className;
		newArr[1] = line;
		System.arraycopy(args, 0, newArr, 2, args.length);
		return new MsgAndArgs("Class: {}, Line {}: " + msg + (b == null ? "" : b.booleanValue() ? " (^-^)" : " (x_x)"), newArr);
	}

	public static void info(Logger log, String msg, Object... args) {
		MsgAndArgs obj = get(Thread.currentThread().getStackTrace(), null, msg, args);
		log.info(obj.msg, obj.args);
	}

	public static void warn(Logger log, String msg, Object... args) {
		MsgAndArgs obj = get(Thread.currentThread().getStackTrace(), null, msg, args);
		log.warn(obj.msg, obj.args);
	}

	public static void info(boolean b, Logger log, String msg, Object... args) {
		MsgAndArgs obj = get(Thread.currentThread().getStackTrace(), Boolean.valueOf(b), msg, args);
		log.info(obj.msg, obj.args);
	}

	public static void warn(boolean b, Logger log, String msg, Object... args) {
		MsgAndArgs obj = get(Thread.currentThread().getStackTrace(), Boolean.valueOf(b), msg, args);
		log.warn(obj.msg, obj.args);
	}

	public static void info(Logger log, Object... args) {
		int len = args.length;
		StringBuilder builder = new StringBuilder(len * 3);
		for (int i = 0; i < len; i++, builder.append("{}\n"));
		MsgAndArgs obj = get(Thread.currentThread().getStackTrace(), null, builder.toString(), args);
		log.info(obj.msg, obj.args);
	}

	public static void error(Logger log, String msg, Exception e) {
		MsgAndArgs obj = get(Thread.currentThread().getStackTrace(), null, msg, e);
		log.info(obj.msg, obj.args);
	}

	public static void warn(Logger log, Object... args) {
		int len = args.length;
		StringBuilder builder = new StringBuilder(len * 3);
		for (int i = 0; i < len; i++, builder.append("{}\n"));
		MsgAndArgs obj = get(Thread.currentThread().getStackTrace(), null, builder.toString(), args);
		log.warn(obj.msg, obj.args);
	}

}
