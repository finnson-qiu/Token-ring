import javax.swing.*;

public class Host4 {
    private static JTextField jTextField1;
    private static JTextField jTextField2;

    public static void main(String [] args){

        xJFrame jf = new xJFrame();
        jf.setName("主机4");
        jf.setLocation(1442,400);
        jf.CreateJFrame();
        Server server = new Server();
        server.setNowPort(9004);
        server.setJframe(jf);
        SomeOperate someOperate = new SomeOperate();
        someOperate.setJf(jf);
        someOperate.setServer(server);
        someOperate.operate();
        server.server();

    }
}
