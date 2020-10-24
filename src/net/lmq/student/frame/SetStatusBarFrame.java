package net.lmq.student.frame;

import net.lmq.student.app.Application;
import net.lmq.student.bean.Status;
import net.lmq.student.service.StatusService;
import net.lmq.student.service.impl.StatusServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SetStatusBarFrame extends JFrame {
    //面板
    JPanel panel,panel1,panel2,panel3,panel4,panel5;
    //标签
    JLabel lbl_college,lbl_version,lbl_author,lbl_telephone,lbl_address,lbl_email;
    //文本框
    JTextField txt_college,txt_version,txt_author,txt_telephone,txt_address,txt_email;
    //按钮
    JButton but_save,but_exit;
    //状态服务对象
    StatusService statusService;


    public SetStatusBarFrame(String title) throws HeadlessException {
        super(title);
        initGUI();
    }

    private void initGUI() {
        //创建组件
        panel=(JPanel) getContentPane();
        panel1=new JPanel();
        panel2=new JPanel();
        panel3=new JPanel();
        panel4=new JPanel();
        panel5=new JPanel();
        //标签
        lbl_college=new JLabel("校名");
        lbl_version=new JLabel("版本");
        lbl_author=new JLabel("作者");
        lbl_telephone=new JLabel("电话");
        lbl_address=new JLabel("地址");
        lbl_email=new JLabel("邮件");
        //文本框
        txt_college=new JTextField(12);
        txt_version=new JTextField(12);
        txt_author=new JTextField(12);
        txt_telephone=new JTextField(12);
        txt_address=new JTextField(28);
        txt_email=new JTextField(28);
        //按钮
        but_exit=new JButton("推出【s】");
        but_save=new JButton("保存【c】");
        //添加面板
        panel.setLayout(new GridLayout(5,1));
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);
        //放进面板
        panel1.add(lbl_college);
        panel1.add(txt_college);
        panel1.add(lbl_version);
        panel1.add(txt_version);
        //2
        panel2.add(lbl_author);
        panel2.add(txt_author);
        panel2.add(lbl_telephone);
        panel2.add(txt_telephone);
        //3、4
        panel3.add(lbl_address);
        panel3.add(txt_address);
        panel4.add(lbl_email);
        panel4.add(txt_email);
        //5
        panel5.add(but_save);
        panel5.add(but_exit);

        //创建窗口服务对象
        statusService=new StatusServiceImpl();
        Status status=statusService.findStatusById(1);
        if (status !=null){
            txt_college.setText(status.getCollege());
            txt_version.setText(status.getVersion());
            txt_author.setText(status.getAuthor());
            txt_address.setText(status.getAddress());
            txt_telephone.setText(status.getTelephone());
            txt_email.setText(status.getEmail());

        }


        //设置窗口
        setVisible(true);
        setTitle("");
        setResizable(false);
        setLocationRelativeTo(null);
        pack();


        //【推出】
        but_exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        //【保存】
        but_save.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Status status=statusService.findStatusById(1);
                //修改属性
                txt_college.setText(status.getCollege());
                txt_version.setText(status.getVersion());
                txt_author.setText(status.getAuthor());
                txt_address.setText(status.getAddress());
                txt_telephone.setText(status.getTelephone());
                txt_email.setText(status.getEmail());
                int count=statusService.updateStstus(status);
                if (count >0){
                    Application.mainFrame.setStatusBar();
                    Application.mainFrame.setTitle("学生管理系统"+status.getVersion());
                }

            }
        });

    }

    public static void main(String[] args) {
        new SetStatusBarFrame("");
    }
}
