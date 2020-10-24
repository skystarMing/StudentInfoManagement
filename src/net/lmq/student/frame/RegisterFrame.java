package net.lmq.student.frame;

import net.lmq.student.app.Application;
import net.lmq.student.bean.User;
import net.lmq.student.service.UserService;
import net.lmq.student.service.impl.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 功能：注册窗口
 * 时间：2019.6.20
 */
public class RegisterFrame extends JFrame {
    //存放的数据
    private String userName;
    private String passWord;
    private String telephone;
    private Date registerTime;
    //标签
    private JLabel lbl_userName;
    private JLabel lbl_passWord;
    private JLabel lbl_telehpone;
    //文本框
    private JTextField txt_userName;
    private JTextField txt_telehpone;
    private JPasswordField txt_passWord;

    //按钮
    private JButton but_Subimt;
    private JButton but_Cancel;
    private JButton but_Login;
    //面板
    private JPanel panel,panel1,panel2,panel3,panel4;

//构造方法
    public RegisterFrame(String title) throws HeadlessException {
        super(title);
        initGUI();
    }
//初始化用户图形界面
    private void initGUI() {
        //创建组件
        panel=(JPanel) getContentPane();
        panel1=new JPanel();
        panel2=new JPanel();
        panel3=new JPanel();
        panel4=new JPanel();

        lbl_userName=new JLabel("用户名：");
        lbl_passWord=new JLabel("密  码：");
        lbl_telehpone=new JLabel("电  话：");
        txt_userName=new JTextField(15);
        txt_passWord=new JPasswordField(15);
        txt_telehpone=new JTextField(15);
        but_Subimt=new JButton("提交");
        but_Cancel=new JButton("取消");
        but_Login=new JButton("登录");
        //面板
        panel1.add(lbl_userName);
        panel1.add(txt_userName);
        panel2.add(lbl_passWord);
        panel2.add(txt_passWord);
        panel3.add(lbl_telehpone);
        panel3.add(txt_telehpone);
        panel4.add(but_Subimt);
        panel4.add(but_Cancel);
        panel4.add(but_Login);

        panel.setLayout(new GridLayout(4,1));
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);


        //窗口设置
        setSize(400,350);
        setLocationRelativeTo(null);
        setResizable(false);
        //窗口标题
        setTitle("用户注册");
        pack();
        setVisible(true);

        //关闭，
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //卸载当前ck
                dispose();
                //让登录窗口可见
                Application.loginFrame.setVisible(true);
            }
        });

        //[登录]按钮
        but_Login.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        //[提交]按钮
        but_Subimt.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });

        //[取消]按钮
        but_Cancel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //显示登录窗口
                Application.loginFrame=new LooginFrame("");
            }
        });

        //[用户名]文本框按键事件处理

    }

    private void register() {
       // 获取用户名
        userName = txt_userName.getText().trim();
        // 获取密码
        passWord = new String(txt_passWord.getPassword());
        // 获取电话
        telephone = txt_telehpone.getText().trim();
        // 定义当前时间为注册时间
        registerTime = new Timestamp(new Date().getTime());

        // 定义用户服务对象
        UserService userService = new UserServiceImpl();
        // 创建用户
        User user = new User();
        user.setUsername(userName);
        user.setPassword(passWord);
        user.setTelephone(telephone);
        user.setRegisterTime(registerTime);

        // 添加用户
        int count = userService.addUser(user);

        // 判断是否添加成功
        if (count > 0) {
            setVisible(false);
            JOptionPane.showMessageDialog(null, "恭喜！注册成功！", "学生信息管理系统", JOptionPane.INFORMATION_MESSAGE);
            setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "遗憾！注册失败！", "学生信息管理系统", JOptionPane.INFORMATION_MESSAGE);
        }



    }

    private void login() {
        dispose();
        Application.loginFrame=new LooginFrame("");
    }

    public static void main(String[] args){

        Application.registerFrame =new RegisterFrame("");
    }
}
