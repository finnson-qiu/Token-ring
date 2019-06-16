
import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static java.lang.Thread.sleep;

public class Server {

    private String mymessage = "";
    private String message = "";
    private int randnum;
    private int NowPort;
    private int DesPort;
    private String display1 = "";
    private String display2 = "";
    private String temp;
    private xJFrame jf;
    private boolean flag = false;

    public void setNowPort(int nowport){
        this.NowPort = nowport;
    }

    public void setJframe(xJFrame jf){
        this.jf = jf;
    }

    public void setFlag(boolean flag){      //如果选择指定模式，flag置位true，数据清空，不再随机产生数据
        this.flag = flag;
        if(flag){
           message = "";
        }
    }

    public void setDesPort(int desPort) {
        DesPort = desPort;
    }

    public void setMyMessage(String message) {
        this.mymessage = message;
    }

    public void server() {
        try {
            DatagramSocket socket = new DatagramSocket(NowPort);
            while (true) {
                byte[] buf = new byte[2048];
                DatagramPacket inpacket = new DatagramPacket(buf, buf.length);
                socket.receive(inpacket);
                byte[] data = inpacket.getData();
                String msg = new String(data, 0, inpacket.getLength());
                if(msg.equals("0") ) {
                    if((message.equals("") && !flag) || (flag && mymessage.equals(""))) {       //随机模式信息和指定模式信息为空，没有数据要发送，空令牌经过
                        display1 = "空令牌经过，本主机没数据需要发送";
                        display2 = "令牌空闲";
                        temp = "";
                    }
                    else{
                        if(!flag) {                     //指定模式有数据发送时显示要发送的信息
                            display1 = "向主机" + DesPort % 9000 + "发送数据" + "发送的数据为:Hello Baby" + message;
                        }
                        else{                           //随机模式中有数据要发送时显示发送的信息
                            display1 = "向主机" + DesPort % 9000 + "发送数据" + "发送的数据为:Hello Baby" + mymessage;
                            jf.getjLable5().setText("发送中");
                            jf.getjLable5().setForeground(Color.pink);
                        }
                        display2 = "主机" + NowPort%9000 + "获得令牌，开始传送数据";         //显示该主机已经获得令牌要传输数据了
                        if(!flag) {
                            message = "1" + ":" + NowPort + ":" + DesPort + ":" + "Hello Baby" + message;       //有随机数据要发送，令牌置忙，数据挂上令牌
                        }
                        else{
                            message = "1" + ":" + NowPort + ":" + DesPort + ":" + "Hello Baby" + mymessage;     //有指定数据要发送，令牌置忙，数据挂上令牌
                        }
                        temp = message;         //准备发送
                    }
                }
                else {                                                      //如果收到非空令牌，上面有数据，解析数据，本机地址和目的地址比较
                    String [] strarray = msg.split( ":");
                    int sourceport = Integer.parseInt(strarray[1]);     //解析数据报来自那台主机端口
                    int destport = Integer.parseInt(strarray[2]);       //解析目的端口号
                    //String temp;
                    if(destport == NowPort) {                   //如果该主机地址就是数据要到达的目的主机地址，接收数据显示
                        display1 = "主机"+NowPort%9000+"收到来自主机" + sourceport%9000 + "的信息---" + strarray[3];
                        display2 = "主机" + sourceport%9000 + "正在占用令牌...";
                        temp = "";
                    }
                    else{                                       //如果目的地址不是该主机，显示忙令牌经过本主机，并显示哪个主机在占用令牌
                        display1 = "忙令牌经过本主机";
                        display2 = "主机" + sourceport%9000 + "正在占用令牌...";
                        temp = "";          //代表该主机不是目的主机，给信号代表令牌只是经过
                    }
                    if(sourceport == NowPort) {         //如果源主机地址等于该主机地址，则显示发送的数据已经回到源主机，并且释放置空令牌的信号
                        if(flag) {
                            jf.getjLable5().setText("发送完毕");
                            jf.getjLable5().setForeground(Color.green);
                        }
                        display2 = "发送的数据回到该源主机，令牌释放";
                        mymessage = "";
                        message = "";               //该主机发送的数据已经送达，释放令牌，释放数据
                        temp = "etoken";            //传递信息代表传送完成回到原主机，释放令牌
                    }
                }
                System.out.println("display1"+display1);
                System.out.println("display2"+display2);

                jf.setjLabel1(display1);            //这个标签显示前面要显示的数据
                jf.setjLabel2(display2);

                byte[] temp1 = temp.getBytes();         //数据往client端发送，模拟令牌
                DatagramPacket outpacket = new DatagramPacket(temp1,temp1.length,InetAddress.getLocalHost(),inpacket.getPort());
                socket.send(outpacket);

                if(!flag) {                                     //默认flag为false，此时产生数据，随机按钮也可以置flag为false，产生随机数据
                    int randjudge = (int) (Math.random() * 10);
                    if (randjudge % 2 == 1) {                           //一半机会可以产生数据
                        int randtime = (int) (Math.random() * 6000);      //随机一个六秒内时间
                        sleep(randtime);            //睡眠6000毫秒以内
                        randnum = (int) (Math.random() * 1000);
                        message = "" + randnum;
                        DesPort = 9000 + (int) (Math.random() * 8);
                        while (DesPort == 9000 || DesPort == NowPort) {
                            DesPort = 9000 + (int) (Math.random() * 8);
                        }
                    } else {
                        message = "";
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
