package net.lmq.student.frame;

import net.lmq.student.bean.Student;
import net.lmq.student.service.StudentService;
import net.lmq.student.service.impl.StudentServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.util.List;
import java.util.Vector;

public class FindStudentByIdFrame extends JFrame {
    //学号标签
    JLabel lbl_Id;
    //学号文本框
    JTextField txt_id;
    //面板
    JPanel panel,pnlSouth,pnlCenter,pnlNorth;
    //按钮
    JButton but_query,but_browseAll,but_print,but_exit;
    //记录行数
    private Vector rows;
    //表格标题
    private Vector<String> colHead;
    //表格
    private JTable table;
    //滚动面板
    JScrollPane scorller;
    //当前行记录
    int currentRow;
    //学生列表
    private List<Student> students;
    //创建学生服务对象
    private StudentService studentService;

    public FindStudentByIdFrame(String title) throws HeadlessException {
        super(title);
        initGUI();
    }

    private void initGUI() {
        //创建组件
        panel=(JPanel) getContentPane();
        pnlCenter=new JPanel();
        pnlNorth=new JPanel();
        pnlSouth=new JPanel();
        //行实例化
        rows=new Vector();
        colHead=new Vector();
        //标签与文本框
        lbl_Id=new JLabel("输入学号");
        txt_id=new JTextField(10);
        txt_id.setHorizontalAlignment(JTextField.CENTER);
        //按钮
        but_query=new JButton("查询");
        but_browseAll=new JButton("显示全部记录");
        but_print=new JButton("打印");
        but_exit=new JButton("退出");
        //添加组件
        panel.add(pnlNorth,"North");
        panel.add(pnlCenter,"Center");
        panel.add(pnlSouth,"South");

        pnlNorth.add(lbl_Id);
        pnlNorth.add(txt_id);
        pnlNorth.add(but_query);
        pnlNorth.add(but_browseAll);
        pnlSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnlSouth.add(but_print);
        pnlSouth.add(but_exit);
        pnlCenter.setLayout(new BorderLayout());
        //创建学生服务对象
        studentService=new StudentServiceImpl();
        students=studentService.findAllStudents();
        fillTableData();

        //设置窗口属性
        setVisible(true);
        setResizable(false);
        pack();
        setTitle("按学号查询学生");
        setLocationRelativeTo(null);


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
        //【查询】
        but_query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                doQuery();
            }
        });
        //【全部】
        but_browseAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                students=studentService.findAllStudents();
                fillTableData();
                but_print.setEnabled(false);
            }
        });
        //【退出】
        but_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });
        //【学号】
        txt_id.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    doQuery();
                }
            }
        });
        //表格单击事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //获取当前行数
                int row=table.rowAtPoint(e.getPoint());
                //选中鼠标单击的行
                table.setRowSelectionInterval(row,row);
                //设置文本框内容
                txt_id.setText(table.getValueAt(row,0).toString());

            }
        });
    }

    private void doQuery() {
        String id=txt_id.getText().trim();
        if (!id.equals("")){
            students.clear();
            Student student=studentService.findStudentById(id);
            if (student!=null) {
                students.add(student);
                //让删除键可用
            }
                fillTableData();
            }else{
                JOptionPane.showMessageDialog(this, "请输入待查学生学号！", "警告", JOptionPane.WARNING_MESSAGE);
                txt_id.requestFocus();

            }
        }


    private void fillTableData() {
        // 填充表头
        colHead.clear();
        colHead.add("学号");
        colHead.add("姓名");
        colHead.add("性别");
        colHead.add("年龄");
        colHead.add("系部");
        colHead.add("班级");
        colHead.add("电话");
        //填充表记录
        rows.clear();
        for (Student student :students){
            Vector<String> currentRow = new Vector<String>();
            currentRow.addElement(student.getId());
            currentRow.addElement(student.getName());
            currentRow.addElement(student.getSex());
            currentRow.addElement(student.getAge() + "");
            currentRow.addElement(student.getDepartment());
            currentRow.addElement(student.getClazz());
            currentRow.addElement(student.getTelephone());
            // 将当前行添加到记录行集
            rows.add(currentRow);
        }
        // 创建表格（参数1：记录集；参数2：表头）
        table = new JTable(rows, colHead);
        // 定义滚动面板
        scorller = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnlCenter.add(scorller,BorderLayout.CENTER);
        //重新绘制窗体
        repaint();
        //判断是否有行记录
        if (rows.isEmpty()){
            JOptionPane.showMessageDialog(this, "没有符合条件的记录！", "错误提示", JOptionPane.WARNING_MESSAGE);
            txt_id.setText("");
        }else {
            // 让滚动条移到最上方
            scorller.getVerticalScrollBar().setValue(0);
        }

    }

    public static void main(String[] args) {
        new FindStudentByIdFrame("");
    }
}
