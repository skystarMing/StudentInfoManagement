package net.lmq.student.frame;

import net.lmq.student.service.StudentService;
import net.lmq.student.service.impl.StudentServiceImpl;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.util.Vector;

public class CountStudnetBySexFrame extends JFrame {
    JPanel panel,pnlSouth,pnlCenter;
    //按钮
    JButton but_print,but_exit;
    //记录行数
    Vector rows;
    //表格标题
    Vector<String> colHead;
    //表格
    JTable table;
    //滚动面板
    JScrollPane scrollPane;
    //学生服务对象
    StudentService studentService;

    public CountStudnetBySexFrame(String title) throws HeadlessException {
        super(title);
        initCUI();
    }

    private void initCUI() {
        //面板
        panel=(JPanel) getContentPane();
        pnlCenter=new JPanel();
        pnlSouth=new JPanel();
        //行
        rows=new Vector();
        colHead=new Vector();
        //按钮
        but_print=new JButton("打印");
        but_print.setMnemonic(KeyEvent.VK_P);
        but_exit=new JButton("退出");
        but_exit.setMnemonic(KeyEvent.VK_X);
        //面板
        panel.add(pnlSouth,"South");
        panel.add(pnlCenter,"Center");
        pnlSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnlSouth.add(but_print);
        pnlSouth.add(but_exit);
        //添加表头
        TitledBorder tb=new TitledBorder("统计结果");
        pnlCenter.setBorder(tb);

        //创建学生服务对象
        studentService=new StudentServiceImpl();
        //获取结果按性别排序
        rows=studentService.findRowsBySex();
        //设置表头
        colHead.add("性别");
        colHead.add("人数");
        //绘制表
        table=new JTable(rows,colHead);
        scrollPane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnlCenter.add(scrollPane,BorderLayout.CENTER);
        //重新绘制窗口
        if (rows.isEmpty()){
            JOptionPane.showMessageDialog(this, "没有记录！", "错误提示", JOptionPane.WARNING_MESSAGE);
        }
        repaint();

        //窗口
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("按性别统计人数");

        //【退出】
        but_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        //【打印】
        but_print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    table.print();
                } catch (PrinterException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args) {
        new CountStudnetBySexFrame("");
    }
}
