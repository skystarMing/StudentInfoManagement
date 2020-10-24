package net.lmq.student.app;

/**
 * 功能：应用程序类
 *      存放全局数据
 * 时间：2019.6.19
 */

import net.lmq.student.frame.MainFrame;
import net.lmq.student.frame.LooginFrame;
import net.lmq.student.frame.RegisterFrame;

public class Application {
    //登录用户标识符
    public static int id;
    //登录用户名
    public static String username;
    //登录密码
    public static String password;
    //登录窗口
    public static LooginFrame loginFrame;
    //主窗口
    public static MainFrame mainFrame;
    //注册窗口
    public static RegisterFrame registerFrame;

}
