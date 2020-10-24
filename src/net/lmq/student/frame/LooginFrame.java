package net.lmq.student.frame;

import net.lmq.student.app.Application;
import net.lmq.student.bean.Status;
import net.lmq.student.bean.Student;
import net.lmq.student.bean.User;
import net.lmq.student.service.StatusService;
import net.lmq.student.service.StudentService;
import net.lmq.student.service.UserService;
import net.lmq.student.service.impl.StatusServiceImpl;
import net.lmq.student.service.impl.StudentServiceImpl;
import net.lmq.student.service.impl.UserServiceImpl;
import net.lmq.student.app.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 功能：登录窗口
 * 时间：2019.6.20
 */


public class LooginFrame extends JFrame {
    private String username;
    private String password;

    private JLabel lbl_UserName;
    private JLabel lbl_PassWord;
    private JTextField txt_UserName;
    private JPasswordField txt_PassWord;

    private JButton btn_Ok;
    private JButton btn_Cancel;
    private JButton btn_Register;

    private JPanel panel,panel1,panel2,panel3;


    public LooginFrame(String title) throws HeadlessException {
        super(title);
        initGUI();
        
    }

    private void initGUI() {
        //实例化组件（面板与控件）
        panel=(JPanel) getContentPane();
        panel1=new JPanel();
        panel2=new JPanel();
        panel3=new JPanel();

        lbl_UserName=new JLabel("用户：");
        lbl_PassWord=new JLabel("密 码：");
        txt_UserName=new JTextField(15);
        txt_PassWord=new JPasswordField(15);
        btn_Ok=new JButton("确定[o]");
        btn_Cancel=new JButton("取消[c]");
        btn_Register=new JButton("注册[r]");

        //设置主面板为三行一列
        panel.setLayout(new GridLayout(3,1));
        //将控件添加到小面板
        panel1.add(lbl_UserName);
        panel1.add(txt_UserName);
        panel2.add(lbl_PassWord);
        panel2.add(txt_PassWord);
        panel3.add(btn_Ok);
        panel3.add(btn_Cancel);
        panel3.add(btn_Register);

        //将三个小面板依次添加到主面板
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);

        //设置按钮热键字母
        btn_Ok.setMnemonic(KeyEvent.VK_O);
        btn_Cancel.setMnemonic(KeyEvent.VK_C);
        btn_Register.setMnemonic(KeyEvent.VK_R);
        //设置密码框回显字符
        txt_PassWord.setEchoChar('#');

        //窗口基础设置
        setSize(350,200);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("用户登录");
        //设置窗口刚好装组件
        pack();
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                exitSystem();
            }
        });

        //[确定]按钮单击事件处理
        btn_Ok.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        //[确定]按钮按键事件
        btn_Ok.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //按下回车键，调用登陆方法
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    login();
                }
            }
        });



        //注册
        btn_Register.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        } );
    }

    private void exitSystem() {
        int choice=JOptionPane.showConfirmDialog(this,"是否退出","登录窗口",JOptionPane.YES_NO_OPTION);
        if (choice ==JOptionPane.YES_OPTION){
            System.exit(0);
        }else{
            dispose();
            Application.loginFrame=new LooginFrame("");
        }
    }

    //转到注册
    private void register() {
        dispose();
        Application.registerFrame =new RegisterFrame("注册窗口");
    }

//是：转到主窗口
    private void login() {
        //获取用户名与密码
        username=txt_UserName.getText().trim();
        password=new String(txt_PassWord.getPassword());

        //创建用户服务对象
        UserService userService=new UserServiceImpl();
        User user=userService.login(username,password);

        //判断
        if ( user !=null){
            //隐藏登录窗口
            Application.loginFrame.setVisible(false);
            //定义状态服务对象
            StatusService statusService=new StatusServiceImpl();
            //获取状态对象
            Status status=statusService.findStatusById(1);
            //保存登录用户的信息
            Application.id=user.getId();
            Application.username=user.getUsername();
            Application.password=user.getPassword();

            //提示用户登录成功
            JOptionPane.showMessageDialog(null,
                    "欢迎使用学生信息管理系统"+status.getVersion(),
            "用户登录",JOptionPane.INFORMATION_MESSAGE);

            //显示系统主窗口
            Application.mainFrame=new MainFrame("");
            //释放登录窗口
            Application.loginFrame.dispose();
        }else{
            //隐藏登录窗口
            Application.loginFrame.setVisible(false);
            //提示用户登录失败
            JOptionPane.showMessageDialog(null,
                    "用户或密码错误，请重新输入",
                    "用户登录",JOptionPane.ERROR_MESSAGE);
            Application.loginFrame.setVisible(true);
            //用户和密码文本框内容全选
            txt_UserName.selectAll();
            txt_PassWord.selectAll();

            txt_UserName.requestFocus();
        }




    }

    public static void main(String[] args) {

        Application.loginFrame =new LooginFrame("用户登陆");
    }
}
