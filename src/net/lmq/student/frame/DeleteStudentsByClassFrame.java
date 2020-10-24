package net.lmq.student.frame;

import net.lmq.student.bean.Student;
import net.lmq.student.service.StudentService;
import net.lmq.student.service.impl.StudentServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;

public class DeleteStudentsByClassFrame extends JFrame {
    //班级标签
    JLabel lbl_class;
    //班级文本框
    JTextField txt_class;
    //面板
    JPanel panel,pnlSouth,pnlCenter,pnlNorth;
    //按钮
    JButton but_query,but_browseAll,but_delete,but_exit;
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

    public DeleteStudentsByClassFrame(String title) throws HeadlessException {
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
        lbl_class=new JLabel("输入班级");
        txt_class=new JTextField(10);
        txt_class.setHorizontalAlignment(JTextField.CENTER);
        //按钮
        but_query=new JButton("查询");
        but_browseAll=new JButton("显示全部记录");
        but_delete=new JButton("删除查询的记录");
        but_exit=new JButton("退出");
        //添加组件
        panel.add(pnlNorth,"North");
        panel.add(pnlCenter,"Center");
        panel.add(pnlSouth,"South");

        pnlNorth.add(lbl_class);
        pnlNorth.add(txt_class);
        pnlNorth.add(but_query);
        pnlNorth.add(but_browseAll);
        pnlSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnlSouth.add(but_delete);
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
        setTitle("按班级删除学生");
        setLocationRelativeTo(null);


        //【删除】
        but_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!rows.isEmpty()){
                    long choice = JOptionPane.showConfirmDialog(null, "是否要删除记录？");
                    if (choice==JOptionPane.OK_OPTION){
                        String clazz =txt_class.getText().trim();
                        int count=studentService.deleteStudentsByClass(clazz);
                        if (count>0){
                            JOptionPane.showMessageDialog(null, "记录删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                            // 重新获取全部学生列表
                            students = studentService.findAllStudents();
                            //清空
                            txt_class.setText("");
                            //填充
                            fillTableData();
                            //按钮不可用
                            but_delete.setEnabled(false);
                        }else {
                            JOptionPane.showMessageDialog(null, "记录删除失败！", "警告", JOptionPane.WARNING_MESSAGE);
                        }
                    }
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
                but_delete.setEnabled(false);
            }
        });
        //【退出】
        but_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        //【班级】
        txt_class.addKeyListener(new KeyAdapter() {
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
                txt_class.setText(table.getValueAt(row,5).toString());

            }
        });
    }

    private void doQuery() {
        String clazz=txt_class.getText().trim();
        if (!clazz.equals("")){
            students=  studentService.findStudentsByClass(clazz);
            if (students.size()>0){
                //让删除键可用
                but_delete.setEnabled(true);
            }
                fillTableData();
            }else{
                JOptionPane.showMessageDialog(this, "请输入待查班级！", "警告", JOptionPane.WARNING_MESSAGE);
                txt_class.requestFocus();

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
            txt_class.setText("");
        }else {
            // 让滚动条移到最上方
            scorller.getVerticalScrollBar().setValue(0);
        }

    }

    public static void main(String[] args) {
        new DeleteStudentsByClassFrame("");
    }
}
