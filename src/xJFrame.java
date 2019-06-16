
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static java.lang.Thread.sleep;

public class xJFrame {

    private  JFrame frame;
    private JPanel panel;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel5;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private int lox;
    private int loy;
    private String name;

    private void placeComponent(){
        panel.setLayout(null);
        Font fnt = new Font("楷体", Font.BOLD, 16);
        jLabel1 = new JLabel(name+"开始运行...");           //显示信息第一个label
        jLabel1.setBounds(10,10,480,50);
        jLabel1.setFont(fnt);
        panel.add(jLabel1);

        jLabel2 = new JLabel("等待消息...");                //显示信息第二个label
        jLabel2.setFont(fnt);
        jLabel2.setBounds(10,60,480,50);
        panel.add(jLabel2);

        Font fnt2 = new Font("楷体",Font.BOLD,20);    //数据文字label
        JLabel jLabel3 = new JLabel("数据：");
        jLabel3.setFont(fnt2);
        jLabel3.setBounds(10,120,70,40);
        panel.add(jLabel3);

        Font fnt3 = new Font("楷体",Font.PLAIN,20);
        jTextField1 = new JTextField();                            //接收数据text框
        jTextField1.setFont(fnt3);
        jTextField1.setBounds(70,125,125,35);
        jTextField1.setEnabled(false);
        panel.add(jTextField1);

        JLabel jLabel4 = new JLabel("目的主机：");           //目标主机文字label
        jLabel4.setFont(fnt2);
        jLabel4.setBounds(215,120,110,40);
        panel.add(jLabel4);

        jTextField2 = new JTextField();                          //接收目标主机号text框
        jTextField2.setFont(fnt3);
        jTextField2.setBounds(320,125,125,35);
        jTextField2.setEnabled(false);
        panel.add(jTextField2);

        button1 = new JButton("指定");
        button1.setFont(fnt2);
        button1.setBounds(50,190,80,35);
        button1.setBackground(Color.lightGray);
        panel.add(button1);

        button2 = new JButton("发送");
        button2.setFont(fnt2);
        button2.setBounds(195,190,80,35);
        button2.setBackground(Color.lightGray);
        button2.setEnabled(false);
        panel.add(button2);

        button3 = new JButton("随机");
        button3.setFont(fnt2);
        button3.setBounds(350,190,80,35);
        button3.setBackground(Color.lightGray);
        panel.add(button3);

        Font fnt5 = new Font("楷体", Font.BOLD, 14);
        jLabel5 = new JLabel("随机模式");
        jLabel5.setBounds(355,68,60,40);
        jLabel5.setFont(fnt5);
        jLabel5.setForeground(Color.blue);
        panel.add(jLabel5);
    }


    public void CreateJFrame(){
        frame = new JFrame(name);
        frame.setSize(500,300);
        frame.setLocation(lox,loy);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        frame.add(panel);
        placeComponent();
        frame.setVisible(true);
    }


    public void setjLabel1(String display1){
        jLabel1.setText("");
        try {
            sleep(40);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        if(display1.equals("")){
            jLabel1.setText(name+"运行ing...");
        }
        else {
            jLabel1.setText(display1);
        }
    }
    public void setjLabel2(String display2){
        jLabel2.setText("");
        try {
            sleep(40);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        if(display2.equals("")){
            jLabel2.setText("等待消息...");
        }
        else {
            jLabel2.setText(display2);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(int lox,int loy) {
        this.lox = lox;
        this.loy = loy;
    }

    public JButton getButton1() {
        return button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public JButton getButton3(){
        return button3;
    }

    public JTextField getjTextField1(){
        return jTextField1;
    }

    public JTextField getjTextField2(){
        return jTextField2;
    }

    public JLabel getjLable5(){
        return jLabel5;
    }

    public static void main(String [] args){
        xJFrame jf = new xJFrame();
        jf.setName("test");
        jf.CreateJFrame();
    }

}
