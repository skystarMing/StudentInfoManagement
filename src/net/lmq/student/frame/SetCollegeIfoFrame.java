package net.lmq.student.frame;


import net.lmq.student.bean.College;
import net.lmq.student.service.CollegeService;
import net.lmq.student.service.impl.CollegeServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SetCollegeIfoFrame extends JFrame {
    //学校服务
    private CollegeService collegeService;
    private College college;
    private int id=1;
    //标签
     JLabel name;
     JLabel president;
     JLabel starttime;
     JLabel telephone;
     JLabel email;
     JLabel address;
     JLabel profile;
    //文本框
     JTextField txt_name;
     JTextField txt_president;
     JTextField txt_starttime;
     JTextField txt_telephone;
     JTextField txt_email;
     JTextField txt_address;
     JTextArea txt_profile;
     //滚动面板
    JScrollPane scrollPane;
    //按钮
    JButton but_save,but_exit;
    //面板
    JPanel panel,pnlNorth,panel1,panel2,panel3,panel4,panel5,pnlCenter,pnlSouth;

    public SetCollegeIfoFrame(String title) throws HeadlessException {
        super(title);
        initGUI();
    }

    private void initGUI() {
        //实例化面板
        panel=(JPanel) getContentPane();
        panel1=new JPanel();
        panel2=new JPanel();
        panel3=new JPanel();
        panel4=new JPanel();
        panel5=new JPanel();
        pnlCenter=new JPanel();
        pnlNorth=new JPanel();
        pnlSouth=new JPanel();
        //标签设置
        name=new JLabel(" 学   校：");
        president=new JLabel("校    长：");
        starttime=new JLabel("建校时间");
        telephone=new JLabel("联系电话");
        email=new JLabel("电子邮箱");
        address=new JLabel("通讯地址");
        profile=new JLabel("学校介绍");
        //文本框设置
        txt_name=new JTextField(29);
        txt_president=new JTextField(11);
        txt_starttime=new JTextField(12);
        txt_telephone=new JTextField(11);
        txt_email=new JTextField(12);
        txt_address=new JTextField(29);
        txt_profile=new JTextArea(5,37);
        txt_profile.setLineWrap(true);
        //滚动面板设置
        scrollPane=new JScrollPane(txt_profile,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        //按钮
        but_save=new JButton("保存");
        but_save.setMnemonic(KeyEvent.VK_S);
        but_exit=new JButton("退出");
        but_exit.setMnemonic(KeyEvent.VK_X);

        //面板设置
        panel.setLayout(new BorderLayout());
        panel.add(pnlNorth,"North");
        panel.add(pnlCenter,"Center");
        panel.add(pnlSouth,"South");
        //上面面板的设置，表格式，5、1
        pnlNorth.setLayout(new GridLayout(5,1));
        pnlNorth.add(panel1);
        pnlNorth.add(panel2);
        pnlNorth.add(panel3);
        pnlNorth.add(panel4);
        pnlNorth.add(panel5);
        //将标签和文本框添加进小面板
        panel1.add(name);
        panel1.add(txt_name);
        panel2.add(president);
        panel2.add(txt_president);
        panel2.add(starttime);
        panel2.add(txt_starttime);
        panel3.add(telephone);
        panel3.add(txt_telephone);
        panel3.add(email);
        panel3.add(txt_email);
        panel4.add(address);
        panel4.add(txt_address);
        panel5.add(profile);
        //将滚动面板添加到中面板
        pnlCenter.add(scrollPane);
        //将按钮添加到南面板
        pnlSouth.add(but_exit);
        pnlSouth.add(but_save);

        //添加学校对象，调用College的add
        collegeService=new CollegeServiceImpl();
        college=collegeService.findCollegeById(id);
        //设置对应的值
        txt_name.setText(college.getName());
        txt_president.setText(college.getPresident());
        txt_starttime.setText(college.getStartTime().toString());
        txt_telephone.setText(college.getTelephone());
        txt_email.setText(college.getEmail());
        txt_address.setText(college.getAddress());
        txt_profile.setText(college.getProfile());



        //设置窗口
        setSize(400,500);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        pack();
        setTitle("设置学校信息");



        //[退出]按钮
        but_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        //[保存]按钮
        but_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //修改学校对象属性
                    college.setName(txt_name.getText());
                    college.setPresident(txt_president.getText());
                    college.setStartTime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(txt_starttime.getText().toString()).getTime()));
                    college.setTelephone(txt_telephone.getText());
                    college.setEmail(txt_email.getText());
                    college.setAddress(txt_address.getText());
                    college.setProfile(txt_profile.getText());
                    //更新学校信息
                    collegeService.updateCollege(college);
                    JOptionPane.showMessageDialog(null,"数据更新成功");
                } catch (ParseException e1) {
                    JOptionPane.showMessageDialog(null,"数据更新失败");
                }
            }
        });

    }

    public static void main(String[] args) {

        new SetCollegeIfoFrame("");
    }
}
