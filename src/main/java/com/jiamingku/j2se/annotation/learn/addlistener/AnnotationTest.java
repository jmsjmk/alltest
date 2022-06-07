package com.jiamingku.j2se.annotation.learn.addlistener;

import com.jiamingku.j2se.annotation.learn.v2.UseCase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnnotationTest {
    private JFrame mainWin = new JFrame("使用注解绑定事件监听器");
    // 使用Annotation为ok按钮绑定事件监听器
    @ActionListenerFor(listener = OkListener.class)
    private JButton ok = new JButton("确定");
    // 使用Annotation为cancel按钮绑定事件监听器
    @ActionListenerFor(listener = CancelListener.class)
    private JButton cancel = new JButton("取消");


    @UseCase(id = 48)
    private String s = "ddds";

    public void init() {
        // 初始化界面的方法
        JPanel jp = new JPanel();
        jp.add(ok);
        jp.add(cancel);
        mainWin.add(jp);
        ActionListenerInstaller.processAnnotations(this);     // ①
        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWin.pack();
        mainWin.setVisible(true);
    }

    public static void main(String[] args) {
        new AnnotationTest().init();
    }
}

// 定义ok按钮的事件监听器实现类
class OkListener implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "单击了确认按钮");
    }
}

// 定义cancel按钮的事件监听器实现类
class CancelListener implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "单击了取消按钮");
    }
}

// 范型的强大
class CancelListener1 {
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "单击了取消按钮");
    }
}