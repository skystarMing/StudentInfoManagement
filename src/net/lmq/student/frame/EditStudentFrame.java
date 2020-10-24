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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EditStudentFrame extends JFrame {
    //面板
    JPanel panel,pnlCenter,pnlSouth,pnlSouth1,pnlSouth2,pnlR1,pnlR2,pnlR3,pnlR4,pnlR5,pnlR6,pnlR7;
    //标签
    JLabel lbl_id,lbl_name,lbl_sex,lbl_age,lbl_department,lbl_class,lbl_telephone;
    //文本框
    JTextField txt_id,txt_name,txt_sex,txt_age,txt_department,txt_class,txt_telephone;
    //按钮
    JButton but_top,but_previous,but_next,but_bottom,but_exit;
    JButton but2_edit,but2_ok,but2_canael;
    //当前记录行号
    int currentRow;
    //学生列表
    private List<Student> students;
    //学生服务对象
    StudentService studentService;

    public EditStudentFrame(String title) throws HeadlessException {
        super(title);
        initGUI();
    }

    private void initGUI() {
        //面板实例化
        panel=(JPanel) getContentPane();
        pnlSouth=new JPanel();
        pnlSouth1=new JPanel();
        pnlSouth2=new JPanel();
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
        txt_id.setEditable(false);
        txt_name=new JTextField(40);
        txt_sex=new JTextField(40);
        txt_age=new JTextField(40);
        txt_department=new JTextField(40);
        txt_class=new JTextField(40);
        txt_telephone=new JTextField(40);
        txt_id.setEditable(false);
        txt_name.setEditable(false);
        txt_sex.setEditable(false);
        txt_age.setEditable(false);
        txt_department.setEditable(false);
        txt_class.setEditable(false);
        txt_telephone.setEditable(false);
        //按钮
        but_top=new JButton("第一条");
        but_previous=new JButton("上一条");
        but_next=new JButton("下一条");
        but_bottom=new JButton("最后一条");
        but_exit=new JButton("退出");
        but2_edit=new JButton("编辑");
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
        //南面板
        pnlSouth.setLayout(new GridLayout(2,1));
        pnlSouth.add(pnlSouth1);
        pnlSouth.add(pnlSouth2);
        //按钮放南面板
        pnlSouth1.add(but_top);
        pnlSouth1.add(but_previous);
        pnlSouth1.add(but_next);
        pnlSouth1.add(but_bottom);
        pnlSouth1.add(but_exit);
        pnlSouth2.add(but2_edit);
        pnlSouth2.add(but2_ok);
        pnlSouth2.add(but2_canael);


        //设置窗口属性
        setVisible(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);


        //获取学生服务对象和学生列表
        studentService=new StudentServiceImpl();
        students=studentService.findAllStudents();
        if (students.size() > 0){
            currentRow=1;
            setTitle("浏览学生表记录"+"  当前记录"+currentRow);
            fillFrameData(currentRow);
        }else {
            JOptionPane.showMessageDialog(null,"表中没有记录","浏览学生表",JOptionPane.WARNING_MESSAGE);
            but_top.setEnabled(false);
            but_previous.setEnabled(false);
            but_next.setEnabled(false);
            but_bottom.setEnabled(false);

        }
        //按钮【第一条】
        but_top.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentRow=1;
                fillFrameData(currentRow);
            }
        });
        //【上一条】
        but_previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentRow>0){
                    //设置当前记录号
                    currentRow--;
                    fillFrameData(currentRow);
                }else {
                    JOptionPane.showMessageDialog(null,"已经到第一条","浏览学生信息",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        //【下一条】
        but_next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentRow<students.size()-1){
                    //设置当前记录号
                    currentRow++;
                    fillFrameData(currentRow);
                }else{
                    JOptionPane.showMessageDialog(null,"已经到最后一条","浏览学生信息",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        //【最后一条】
        but_bottom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentRow=students.size()-1;
                fillFrameData(currentRow);
            }
        });
        //【退出】
        but_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //【编辑】
        but2_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //设置窗口
                setTitle("编辑学生表记录"+"  当前记录:"+currentRow);
                txt_name.setEnabled(true);
                txt_sex.setEnabled(true);
                txt_department.setEnabled(true);
                txt_class.setEnabled(true);
                txt_age.setEnabled(true);
                txt_telephone.setEnabled(true);
                but2_ok.setEnabled(true);
                but2_canael.setEnabled(true);

            }
        });
        //【确定】
        but2_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student student=students.get(currentRow);
                if (isNumber(txt_age.getText())) {
                    if (isLegalTelephone(txt_telephone.getText())) {
                        // 修改学生实体属性
                        student.setName(txt_name.getText());
                        student.setSex(txt_sex.getText());
                        student.setAge(Integer.parseInt(txt_age.getText()));
                        student.setDepartment(txt_department.getText());
                        student.setClazz(txt_class.getText());
                        student.setTelephone(txt_telephone.getText());
                        // 更新学生信息
                        int count = studentService.updateStudent(student);
                        if (count > 0) {
                            JOptionPane.showMessageDialog(null, "更新记录成功！", "编辑学生记录", JOptionPane.INFORMATION_MESSAGE);
                            but2_ok.setEnabled(false);
                            but2_canael.setEnabled(false);
                            but2_edit.setEnabled(true);
                            txt_name.setEditable(false);
                            txt_sex.setEditable(false);
                            txt_age.setEditable(false);
                            txt_department.setEditable(false);
                            txt_class.setEditable(false);
                            txt_telephone.setEditable(false);
                            // 重新获取全部学生列表
                            students = studentService.findAllStudents();

                        } else {
                            JOptionPane.showMessageDialog(null, "更新记录失败！", "编辑学生记录", JOptionPane.ERROR_MESSAGE);
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "非法手机号！", "编辑学生记录", JOptionPane.ERROR_MESSAGE);
                        txt_telephone.selectAll();
                        txt_telephone.requestFocus();
                    }

                }else{
                    JOptionPane.showMessageDialog(null, "年龄必须是数字！", "编辑学生记录", JOptionPane.ERROR_MESSAGE);
                    txt_age.selectAll();
                    txt_age.requestFocus();

                }
            }
        });
        //【取消】
        but2_canael.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                but2_ok.setEnabled(false);
                but2_canael.setEnabled(false);
                but2_edit.setEnabled(true);
                txt_name.setEditable(false);
                txt_sex.setEditable(false);
                txt_age.setEditable(false);
                txt_department.setEditable(false);
                txt_class.setEditable(false);
                txt_telephone.setEditable(false);
                //恢复文本框修改前的数据
                txt_name.setText(students.get(currentRow).getName());
                txt_sex.setText(students.get(currentRow).getSex());
                txt_age.setText(students.get(currentRow).getAge() + "");
                txt_department.setText(students.get(currentRow).getDepartment());
                txt_class.setText(students.get(currentRow).getClazz());
                txt_telephone.setText(students.get(currentRow).getTelephone());
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

    private void fillFrameData(int currentRow) {
        if (currentRow >0) {
            setTitle("浏览学生信息表" + "  当前记录:" + currentRow);
            txt_id.setText(students.get(currentRow).getId());
            txt_name.setText(students.get(currentRow).getName());
            txt_sex.setText(students.get(currentRow).getSex());
            txt_age.setText(students.get(currentRow).getAge() + " ");
            txt_department.setText(students.get(currentRow).getDepartment());
            txt_class.setText(students.get(currentRow).getClazz());
            txt_telephone.setText(students.get(currentRow).getTelephone());
        }
    }

    private boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }
    private boolean isLegalTelephone(String telephone) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(telephone);
        return m.matches();
    }




    public static void main(String[] args) {
        new EditStudentFrame("");
    }
}
