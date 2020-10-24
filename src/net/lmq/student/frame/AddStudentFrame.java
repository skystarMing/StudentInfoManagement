package net.lmq.student.frame;

import net.lmq.student.bean.Student;
import net.lmq.student.service.StudentService;
import net.lmq.student.service.impl.StudentServiceImpl;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class AddStudentFrame extends JFrame {
    //面板
    JPanel panel,pnlCenter,pnlSouth,pnlR1,pnlR2,pnlR3,pnlR4,pnlR5,pnlR6,pnlR7;
    //标签
    JLabel lbl_id,lbl_name,lbl_sex,lbl_age,lbl_department,lbl_class,lbl_telephone;
    //文本框
    JTextField txt_id,txt_name,txt_sex,txt_age,txt_department,txt_class,txt_telephone;
    //按钮

    JButton but2_exit,but2_ok,but2_canael;
    //当前记录行号
    int currentRow;
    //学生列表
    private List<Student> students;



    public AddStudentFrame(String title) throws HeadlessException {
        super(title);
        initGUI();
    }

    private void initGUI() {
        //面板
        panel=(JPanel) getContentPane();
        pnlSouth=new JPanel();
        pnlCenter=new JPanel();
        pnlR1=new JPanel();
        pnlR2=new JPanel();
        pnlR3=new JPanel();
        pnlR4=new JPanel();
        pnlR5=new JPanel();
        pnlR6=new JPanel();
        pnlR7=new JPanel();


        //标签
        lbl_id=new JLabel("学号");
        lbl_name=new JLabel("姓名");
        lbl_sex=new JLabel("性别");
        lbl_age=new JLabel("年龄");
        lbl_department=new JLabel("系部");
        lbl_class=new JLabel("班级");
        lbl_telephone=new JLabel("电话");
        //文本框
        txt_id=new JTextField(40);
        txt_name=new JTextField(40);
        txt_sex=new JTextField(40);
        txt_age=new JTextField(40);
        txt_department=new JTextField(40);
        txt_class=new JTextField(40);
        txt_telephone=new JTextField(40);
        //按钮
        but2_exit=new JButton("退出");
        but2_ok=new JButton("确定");
        but2_canael=new JButton("取消");

        //面板属性
        panel.setLayout(new BorderLayout());
        panel.add(pnlCenter,BorderLayout.CENTER);
        panel.add(pnlSouth,BorderLayout.SOUTH);
        pnlCenter.setLayout(new GridLayout(7,1));
        //将控件放入小面板
        pnlR1.add(lbl_id);
        pnlR1.add(txt_id);
        pnlR2.add(lbl_name);
        pnlR2.add(txt_name);
        pnlR3.add(lbl_sex);
        pnlR3.add(txt_sex);
        pnlR4.add(lbl_age);
        pnlR4.add(txt_age);
        pnlR5.add(lbl_department);
        pnlR5.add(txt_department);
        pnlR6.add(lbl_class);
        pnlR6.add(txt_class);
        pnlR7.add(lbl_telephone);
        pnlR7.add(txt_telephone);
        //将小面板放入中面板
        pnlCenter.add(pnlR1);
        pnlCenter.add(pnlR2);
        pnlCenter.add(pnlR3);
        pnlCenter.add(pnlR4);
        pnlCenter.add(pnlR5);
        pnlCenter.add(pnlR6);
        pnlCenter.add(pnlR7);
        pnlSouth.add(but2_ok);
        pnlSouth.add(but2_canael);
        pnlSouth.add(but2_exit);

        //设置窗口属性
        setVisible(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);


        //【确定】
        but2_ok.addActionListener(new ActionListener() {
            @Override
            //id数字验证
            public void actionPerformed(ActionEvent e) {
                if (txt_id.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "学号不能为空！", "增加学生记录", JOptionPane.WARNING_MESSAGE);
                    txt_id.requestFocus();
                    return;

                }
                if (!isNumber(txt_age.getText().trim())){
                    JOptionPane.showMessageDialog(null, "注意：年龄全由数字构成！", "增加学生记录", JOptionPane.WARNING_MESSAGE);
                    txt_age.requestFocus();
                    return;
                }
                //设置属性
                Student student=new Student();
                student.setId(txt_id.getText().trim());
                student.setName(txt_name.getText().trim());
                student.setSex(txt_sex.getText().trim());
                student.setAge(Integer.parseInt(txt_age.getText()));
                student.setDepartment(txt_department.getText().trim());
                student.setClazz(txt_class.getText().trim());
                student.setTelephone(txt_telephone.getText().trim());
                //实例化学生服务对象
                StudentService studentService=new StudentServiceImpl();
                int count = studentService.addStudent(student);
                if (count >0){
                    JOptionPane.showMessageDialog(null, "添加记录成功！", "增加学生记录", JOptionPane.INFORMATION_MESSAGE);
                    txt_id.setText("");
                    txt_name.setText("");
                    txt_sex.setText("");
                    txt_age.setText("");
                    txt_department.setText("");
                    txt_class.setText("");
                    txt_telephone.setText("");
                    txt_id.requestFocus();
                }else {
                    JOptionPane.showMessageDialog(null, "添加记录失败！", "增加学生记录", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // 【取消】按钮单击事件处理
        but2_canael.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt_id.setText("");
                txt_name.setText("");
                txt_sex.setText("");
                txt_age.setText("");
                txt_department.setText("");
                txt_class.setText("");
                txt_telephone.setText("");
                txt_id.requestFocus();
            }
        });
        //【退出】
        but2_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        //【学号】文本框按键事件
        txt_id.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    txt_name.requestFocus();
                }
            }
        });
        //【姓名】文本框按键事件
        txt_name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    txt_sex.requestFocus();
                }
            }
        });//【性别】文本框按键事件
        txt_sex.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    txt_age.requestFocus();
                }
            }
        });//【年龄】文本框按键事件
        txt_age.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    txt_department.requestFocus();
                }
            }
        });//【系部】文本框按键事件
        txt_department.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    txt_class.requestFocus();
                }
            }
        });
        //【班级】文本框按键事件
        txt_class.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    txt_telephone.requestFocus();
                }
            }
        });




    }

    private boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new AddStudentFrame("");
    }
}
