package net.lmq.student.frame;

import net.lmq.student.app.Application;
import net.lmq.student.bean.Status;
import net.lmq.student.service.StatusService;
import net.lmq.student.service.impl.StatusServiceImpl;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * 功能：主窗口
 *      通过菜单系统
 *      调用各个功能模块
 * 时间：2019.6.20
 */
public class MainFrame extends JFrame {
    private Status status;
    private StatusService statusService;
    //菜单
    JMenuBar mnb_main;
    //设置菜单
    JMenu mn_set;
    JMenuItem mni_setCollegeInfo,mni_setStatusBar,mni_changePassword,mni_exit;
    //操作菜单
    JMenu mn_operate;
    JMenuItem mni_addStudent,mni_browseStudent,mni_editStudent;
    //删除菜单
    JMenu mn_delStu;
    JMenuItem mni_delStudentById,mni_delStudentByClass,mni_delStudentByDepartment;
    //查询菜单
    JMenu mn_Find;
    JMenuItem mni_findStudentById,mni_findStudentByName,mni_findStudentByClass,mni_findStudentByDepartment;
    //统计菜单
    JMenu mn_count;
    JMenuItem mni_countStudentBySex,mni_countStudentByClass,mni_countStudentByDepartment;
    //帮助菜单
    JMenu mn_help;
    JMenuItem mni_help,mni_about;
    //面板
    JPanel panel,pnlCenter,pnlSouth;
    //状态标签
    JLabel lbl_statusBar;
    //背景标签
    JLabel lbl_background;
    //图标对象
    ImageIcon img_college,img_exit,img_passWord,img_query,img_browse,img_count,img_background;
    //工具栏
    JToolBar toolBar;
    //按钮
    JButton but_setCollege,but_changePassword,but_FindStudentById,but_exit,but_briwseStudent,but_countByDeparment;



 //窗口界面
    public MainFrame(String title) throws HeadlessException {
        super(title);
        initGUI();
    }

//初始化界面
    private void initGUI() {
        //创建主菜单
        mnb_main=new JMenuBar();
        //创建【设置】菜单及菜单项
        mn_set=new JMenu("系统设置[s]");
        mn_set.setMnemonic(KeyEvent.VK_S);
        mni_setCollegeInfo=new JMenuItem("学校信息");
        mni_setStatusBar=new JMenuItem("状态栏信息");
        mni_changePassword=new JMenuItem("修改密码");
        mni_exit=new JMenuItem("退出系统");

        //创建【操作】菜单及菜单选项
        mn_operate=new JMenu("数据操作[o]");
        mn_operate.setMnemonic(KeyEvent.VK_O);
        mni_addStudent=new JMenuItem("添加学生表记录");
        mn_delStu=new JMenu("删除学生记录表");
        mni_editStudent=new JMenuItem("编辑学生表记录");
        mni_browseStudent=new JMenuItem("浏览学生表记录");

        //创建【删除学生表记录】的子菜单
        mni_delStudentById=new JMenuItem("按学号删除");
        mni_delStudentByClass=new JMenuItem("按班级删除");
        mni_delStudentByDepartment=new JMenuItem("按系部删除");

        //创建【查询】菜单及菜单选项
        mn_Find=new JMenu("查询学生[Q]");
        mn_Find.setMnemonic(KeyEvent.VK_Q);
        mni_findStudentByClass=new JMenuItem("按班级查询");
        mni_findStudentByDepartment=new JMenuItem("按系部查询");
        mni_findStudentById=new JMenuItem("按学号查询");
        mni_findStudentByName=new JMenuItem("按姓名查询");

        //创建【统计】菜单
        mn_count=new JMenu("人数统计[C]");
        mn_count.setMnemonic(KeyEvent.VK_C);
        mni_countStudentBySex=new JMenuItem("按性别统计");
        mni_countStudentByClass=new JMenuItem("按班级统计");
        mni_countStudentByDepartment=new JMenuItem("按系部统计");

        //创建【帮助】菜单
        mn_help=new JMenu("帮助[H]");
        mn_help.setMnemonic(KeyEvent.VK_H);
        mni_help=new JMenuItem("帮助");
        mni_about=new JMenuItem("关于");

        //创建图标对象
        img_college = new ImageIcon("images/college.png");
        img_passWord = new ImageIcon("images/password.png");
        img_query = new ImageIcon("images/query.png");
        img_browse = new ImageIcon("images/browse.png");
        img_count = new ImageIcon("images/count.png");
        img_exit = new ImageIcon("images/exit.png");



        //工具栏
        toolBar=new JToolBar();
        but_setCollege=new JButton("设置学校",img_college);
        but_setCollege.setToolTipText("设置学校信息");
        but_setCollege.setVerticalTextPosition(AbstractButton.BOTTOM);
        but_setCollege.setHorizontalTextPosition(AbstractButton.CENTER);
        but_changePassword = new JButton("修改密码", img_passWord);
        but_changePassword.setToolTipText("修改用户密码");
        but_changePassword.setVerticalTextPosition(AbstractButton.BOTTOM);
        but_changePassword.setHorizontalTextPosition(AbstractButton.CENTER);
        but_briwseStudent = new JButton("浏览学生", img_browse);
        but_briwseStudent.setToolTipText("浏览学生记录");
        but_briwseStudent.setVerticalTextPosition(AbstractButton.BOTTOM);
        but_briwseStudent.setHorizontalTextPosition(AbstractButton.CENTER);
        but_FindStudentById = new JButton("查询学生", img_query);
        but_FindStudentById.setToolTipText("按学号查询学生记录");
        but_FindStudentById.setVerticalTextPosition(AbstractButton.BOTTOM);
        but_FindStudentById.setHorizontalTextPosition(AbstractButton.CENTER);
        but_countByDeparment = new JButton("统计人数", img_count);
        but_countByDeparment.setToolTipText("按系部统计学生人数");
        but_countByDeparment.setVerticalTextPosition(AbstractButton.BOTTOM);
        but_countByDeparment.setHorizontalTextPosition(AbstractButton.CENTER);
        but_exit = new JButton("退出系统", img_exit);
        but_exit.setToolTipText("退出系统");
        but_exit.setVerticalTextPosition(AbstractButton.BOTTOM);
        but_exit.setHorizontalTextPosition(AbstractButton.CENTER);
        toolBar.add(but_setCollege);
        toolBar.add(but_changePassword);
        toolBar.add(but_briwseStudent);
        toolBar.add(but_FindStudentById);
        toolBar.add(but_countByDeparment);
        toolBar.add(but_exit);

        //创建面板
        panel=(JPanel) getContentPane();
        pnlCenter=new JPanel();
        pnlSouth=new JPanel();
        pnlSouth.setLayout(new FlowLayout(FlowLayout.LEFT));

        //图片放入
        img_background=new ImageIcon("images/background.jpg");
        lbl_background=new JLabel(img_background);
        lbl_statusBar=new JLabel();

        //设置菜单栏
        setJMenuBar(mnb_main);
        //添加【设置】菜单
        mnb_main.add(mn_set);
        mn_set.add(mni_setCollegeInfo);
        mn_set.add(mni_setStatusBar);
        mn_set.add(mni_changePassword);
        mn_set.addSeparator();
        mn_set.add(mni_exit);
        //添加【查询】菜单
        mnb_main.add(mn_operate);
        mn_operate.add(mni_addStudent);
        mn_operate.add(mni_editStudent);
        mn_operate.add(mn_delStu);
        mn_operate.add(mni_browseStudent);
        //添加【查询】菜单
        mnb_main.add(mn_Find);
        mn_Find.add(mni_findStudentById);
        mn_Find.add(mni_findStudentByName);
        mn_Find.add(mni_findStudentByClass);
        mn_Find.add(mni_findStudentByDepartment);
        //添加【统计】菜单
        mnb_main.add(mn_count);
        mn_count.add(mni_countStudentBySex);
        mn_count.add(mni_countStudentByClass);
        mn_count.add(mni_countStudentByDepartment);
        //添加【帮助】菜单
        mnb_main.add(mn_help);
        mn_help.add(mni_help);
        mn_help.add(mni_about);

        //添加面板
        panel.setLayout(new BorderLayout());
        panel.add(toolBar,"North");
        panel.add(pnlCenter,"Center");
        panel.add(pnlSouth,"South");
        pnlCenter.add(lbl_background);
        pnlSouth.add(lbl_statusBar);

        //非管理员不能设置状态栏
        if (!Application.username.equals("admin")){
            mni_setStatusBar.setEnabled(false);
            mni_addStudent.setEnabled(false);
            mn_delStu.setEnabled(false);
            mni_editStudent.setEnabled(false);
        }


        //创建【统计】菜单及菜单选项
        mn_help=new JMenu("帮助[H]");
        mni_help=new JMenuItem("帮助");
        mni_about=new JMenuItem("关于");

        //创建状态服务对象
        statusService=new StatusServiceImpl();
        status=statusService.findStatusById(1);

        //设置窗口尺寸
        setSize(800,600);
        //设置窗口可见
        setVisible(true);
        //窗口居中
        setLocationRelativeTo(null);
        //窗口标题
        setTitle("学生管理系统"+status.getVersion());

        //设置菜单【设置学校信息】
        mni_setCollegeInfo.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SetCollegeIfoFrame("");
            }
        });
        //【状态信息栏】
        mni_setStatusBar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SetStatusBarFrame("");
            }
        });
        // 【修改密码】菜单项单击事件
        mni_changePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePasswordFrame("");
            }
        });

        // 【退出系统】菜单项单击事件
        mni_exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitSystem();
            }
        });

        // 查询菜单
        // 【按学号查询】菜单项单击事件
        mni_findStudentById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindStudentByIdFrame("");
            }
        });

        // 【按姓名查询】菜单项单击事件
        mni_findStudentByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindStudentByNameFrame("");
            }
        });

        // 【按班级查询】菜单项单击事件
        mni_findStudentByClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindStudentByClassFrame("");
            }
        });

        // 【按系部查询】菜单项单击事件
        mni_findStudentByDepartment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindStudnetByDepartmentFrame("");
            }
        });

        // 统计菜单
        // 【按性别统计人数】菜单项单击事件
        mni_countStudentBySex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CountStudnetBySexFrame("");
            }
        });

        // 【按班级统计人数】菜单项单击事件
        mni_countStudentByClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CountStudnetByClassFrame("");
            }
        });

        // 【按系部统计人数】菜单项单击事件
        mni_countStudentByDepartment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CountStudnetByClassFrame("");
            }
        });

        // 【增加学生记录】菜单项单击事件
        mni_addStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddStudentFrame("");
            }
        });

        // 【按学号删除学生记录】菜单项单击事件
        mni_delStudentById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteStudentFrame("");
            }
        });

        // 【按班级删除学生记录】菜单项单击事件
        mni_delStudentByClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteStudentsByClassFrame("");
            }
        });

        // 【按系部删除学生记录】菜单项单击事件
        mni_delStudentByDepartment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteStudentByDepartmentFram("");
            }
        });

        // 【编辑学生记录】菜单项单击事件
        mni_editStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EditStudentFrame("");
            }
        });

        // 【浏览学生记录】菜单项单击事件
        mni_browseStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BrowseStudentFrame("");
            }
        });

        // 【帮助】菜单单击事件
        mni_help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("cmd /c start help/帮助文档.chm");
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "学生信息管理系统", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 【关于】菜单单击事件
        mni_about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "开发人员：" + status.getCollege() + "_" + status.getAuthor() + "\n联系电话：" + status.getTelephone()
                                + "\n电子邮箱：" + status.getEmail(),
                        "学生信息管理系统" + status.getVersion(), JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // 工具栏按钮单击事件
        // 【设置学校信息】按钮
        but_setCollege.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SetCollegeIfoFrame("");
            }
        });

        // 【修改密码】按钮
        but_changePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePasswordFrame("");
            }
        });

        // 【浏览】按钮
        but_briwseStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BrowseStudentFrame("");
            }
        });

        // 【查询】按钮
        but_FindStudentById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindStudentByIdFrame("");
            }
        });

        // 【统计】按钮
        but_countByDeparment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CountStudnetByDepartmentFrame("");
            }
        });
        but_exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                 Application.loginFrame=new LooginFrame("");
            }
        });





        //注册窗口监听器,继承窗口适配器，编写事件处理ff
        addWindowListener(new WindowAdapter() {
            //adapter,空实现了所有方法，调用时，改需要的方法
            @Override
            public void windowClosing(WindowEvent e) {
                exitSystem();
            }

        });
    }
    public void setStatusBar() {
        // 创建状态服务对象
        statusService = new StatusServiceImpl();
        // 获取状态栏对象
        status = statusService.findStatusById(1);
        // 设置状态栏标签
        lbl_statusBar.setText(
                status.getCollege() + "学生信息管理系统" + status.getVersion() + "      作者：" + status.getAuthor() + "      地址："
                        + status.getAddress() + "      电话：" + status.getTelephone() + "      邮箱：" + status.getEmail());
    }



//推出系统，询问用户是否推出
    private void exitSystem() {
        int choice=JOptionPane.showConfirmDialog(this,"您是否要推出?",
                "学生信息管理系统",JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_OPTION){
            dispose();
            Application.loginFrame= new LooginFrame("");
        }else{
            //卸载当前窗口
            dispose();
            //重新显示窗口
            Application.mainFrame =new MainFrame("学生信息管理系统"+status.getVersion());
        }
    }


//主方法
    public static void main(String[] args){
        StatusService statusService=new StatusServiceImpl();
        Status status=statusService.findStatusById(1);
        Application.mainFrame= new MainFrame("学生信息管理系统"+status.getVersion());
    }
}
