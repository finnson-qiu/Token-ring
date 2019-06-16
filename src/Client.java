import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static java.lang.Thread.sleep;

public class Client {

    private static String Token  = "0";
    private static byte [] buffer= new byte[4096];

    public static void send(int port) {
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] buf = Token.getBytes();
            DatagramPacket outpacket = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), port);
            socket.send(outpacket);

            DatagramPacket inpacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(inpacket);
            buffer = inpacket.getData();
            String temp = new String(buffer, 0, inpacket.getLength());
            if(!temp.equals("")) {          //如果收到空，代表空令牌或忙令牌不需要任何操作，此时情况应该是空令牌经过没有数据要发送的主机，忙令牌经过非源主机
                if(temp.equals("etoken")){          //此时忙令牌经过了源目的主机，代表忙令牌带着数据饶了一圈回来了
                    Token = "0";                //数据帧移去，释放令牌，使令牌为空
                }
                else {
                    Token = temp;               //代表主机有东西要发，挂到令牌上
                }
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        int count =9000 + (int)(Math.random()*8);
        while(count == 0){
            count =9000 + (int) (Math.random()*8);
        }
        while(true) {
            send(count);        //调用send函数往各主机发送信息，模拟令牌传递过程
            try {
                sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            count ++;
            if(count == 9009){
                count = 9001;
            }
        }
    }
}
