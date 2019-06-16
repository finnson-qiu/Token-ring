import javax.swing.*;

public class Host5 {
    private static JTextField jTextField1;
    private static JTextField jTextField2;

    public static void main(String [] args){

        xJFrame jf = new xJFrame();
        jf.setName("主机5");
        jf.setLocation(1442,700);
        jf.CreateJFrame();
        Server server = new Server();
        server.setNowPort(9005);
        server.setJframe(jf);
        SomeOperate someOperate = new SomeOperate();
        someOperate.setJf(jf);
        someOperate.setServer(server);
        someOperate.operate();
        server.server();

    }
}
