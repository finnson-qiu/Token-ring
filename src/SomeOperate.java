import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SomeOperate {

    private xJFrame jf;
    private Server server;
    private static JTextField jTextField1;
    private static JTextField jTextField2;
    private static JButton button2;
    private static JLabel jLabel;

    public void setJf(xJFrame jf) {
        this.jf = jf;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void operate(){
        jTextField1 = jf.getjTextField1();
        jTextField2 = jf.getjTextField2();
        jLabel = jf.getjLable5();
        JButton button1 = jf.getButton1();
        addAimActionListener(button1,server);
        button2 = jf.getButton2();
        addSendActionListener(button2,server);
        JButton button3 = jf.getButton3();
        addRandomActionListener(button3,server);
    }

    private static void addAimActionListener(JButton button1, Server server) {
        // 为指定按钮绑定监听器
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField1.setEnabled(true);
                jTextField2.setEnabled(true);
                button2.setEnabled(true);
                jLabel.setText("指定模式");
                jLabel.setForeground(Color.blue);
                server.setFlag(true);
            }
        });
    }
    private static void addSendActionListener(JButton button2, Server server) {
        // 为发送按钮绑定监听器
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = jTextField1.getText();
                String hostnum = jTextField2.getText();
                int port = 9000 + Integer.parseInt(hostnum);
                server.setDesPort(port);
                server.setMyMessage(message);
                jLabel.setText("准备发送");
                jLabel.setForeground(Color.red);
            }
        });
    }
    private static void addRandomActionListener(JButton button3, Server server) {
        // 为回到随机按钮绑定监听器
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField1.setText("");
                jTextField2.setText("");
                jTextField1.setEnabled(false);
                jTextField2.setEnabled(false);
                button2.setEnabled(false);
                jLabel.setText("随机模式");
                jLabel.setForeground(Color.blue);
                server.setFlag(false);
            }
        });
    }
}
