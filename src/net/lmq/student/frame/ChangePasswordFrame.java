package net.lmq.student.frame;

import jdk.nashorn.internal.scripts.JO;
import net.lmq.student.app.Application;
import net.lmq.student.bean.User;
import net.lmq.student.service.UserService;
import net.lmq.student.service.impl.UserServiceImpl;
import sun.plugin2.message.JavaObjectOpMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ChangePasswordFrame extends JFrame {
    //面板
    JPanel panel,panel1,panel2,panel3,panel4,panel5;

    //标签
    JLabel lbl_userName,lbl_passWord,lbl_passWord1,lbl_passWord2;

    //文本框
    JTextField txt_userName;
    JPasswordField txt_passWord,txt_passWord1,txt_passWord2;

    //按钮
    JButton but_ok,but_cancel;

    //用户对象
    UserService userService;


    public ChangePasswordFrame(String title) throws HeadlessException {
        super(title);
        initGUI();

    }

    private void initGUI() {
        //面板实例化
        panel=(JPanel) getContentPane();
        panel1=new JPanel();
        panel2=new JPanel();
        panel3=new JPanel();
        panel4=new JPanel();
        panel5=new JPanel();
        //标签
        lbl_userName=new JLabel("用户名：");
        lbl_passWord=new JLabel("旧密码：");
        lbl_passWord1=new JLabel("新密码：");
        lbl_passWord2=new JLabel("确    定：");
        //文本框
        txt_userName=new JTextField(20);
        txt_passWord=new JPasswordField(20);
        txt_passWord1=new JPasswordField(20);
        txt_passWord2=new JPasswordField(20);
        //按钮
        but_ok=new JButton("提交");
        but_cancel=new JButton("取消");
        //设置面板
        panel.setLayout(new GridLayout(5,1));
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);
        //将控件放到面板
        panel1.add(lbl_userName);
        panel1.add(txt_userName);
        panel2.add(lbl_passWord);
        panel2.add(txt_passWord);
        panel3.add(lbl_passWord1);
        panel3.add(txt_passWord1);
        panel4.add(lbl_passWord2);
        panel4.add(txt_passWord2);
        panel5.add(but_ok);
        panel5.add(but_cancel);

        //设置窗口
        setResizable(false);
        pack();
        setVisible(true);
        setTitle("修改用户密码");
        setLocationRelativeTo(null);


        //设置控件属性
        txt_userName.setText(Application.username);//放入用户名
        txt_userName.setEditable(false);

        //【确定】按钮
        but_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                changePassword();
            }
        });
        //【取消】按钮
        but_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //【用户名】文本框按键处理事件
        txt_userName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    txt_userName.requestFocus();
                }
            }
        });
        //【密码】
        txt_passWord.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    txt_passWord.requestFocus();
                }
            }
        });
        //【新密码】
        txt_passWord1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    txt_passWord1.requestFocus();
                }
            }
        });
        //【确定】
        txt_passWord2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    txt_passWord2.requestFocus();
                }
            }
        });
    }

    private void changePassword() {
        //获取用户信息
        int id=Application.id;
        String userName=Application.username;
        //保存输入的旧密码及两个新密码
        String passWord=new String(txt_passWord.getPassword());
        String passWord1=new String(txt_passWord1.getPassword());
        String password2=new String(txt_passWord2.getPassword());
        //创建用户服务对象
        userService=new UserServiceImpl();

        //判断密码
        if (userService.login(userName,passWord)==null){
            JOptionPane.showConfirmDialog(null,"亲，你的旧密码错误，重新输入哦",
                    "错误提示",JOptionPane.ERROR_MESSAGE);
            txt_passWord.requestFocus();
            txt_passWord.selectAll();
        }else if (passWord1.equals("")){
            JOptionPane.showMessageDialog(null,"亲，新密码不为空呀","错误提示",
                    JOptionPane.ERROR_MESSAGE);
            txt_passWord1.requestFocus();
        }else if(passWord1.equals(passWord)){
            JOptionPane.showMessageDialog(null,"亲，新旧密码一样哦","友情提示",
                    JOptionPane.ERROR_MESSAGE);
            txt_passWord1.requestFocus();
        }
        else if (password2.equals("")){
            JOptionPane.showMessageDialog(null,"拜托，确定密码也不能为空","错误错误",
                    JOptionPane.ERROR_MESSAGE);
            txt_passWord2.requestFocus();
        }else if (!passWord1.equals(password2)){
            JOptionPane.showMessageDialog(null,"两次密码输入不一致，亲，要重新输入","错误提示",
                    JOptionPane.ERROR_MESSAGE);
            txt_passWord1.setText("");
            txt_passWord2.setText("");
        }else{
            //按标识符获取用户
            User user=userService.findUserById(id);
            //修改用户的密码属性
            user.setPassword(passWord1);
            //调用用户服务对象的更新方法，更新用户信息
            int count=userService.updateUser(user);
            if (count>0){
                JOptionPane.showMessageDialog(null,"修改密码成功了","设置密码",
                        JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null,"抱歉，修改密码失败！","设置密码",
                        JOptionPane.WARNING_MESSAGE);
            }

        }

    }

    public static void main(String[] args) {
        Application.id=1;
        Application.username="李刚";
        new ChangePasswordFrame("");

    }
}
