import javax.swing.*;

public class Host7 {
    private static JTextField jTextField1;
    private static JTextField jTextField2;

    public static void main(String [] args){

        xJFrame jf = new xJFrame();
        jf.setName("主机7");
        jf.setLocation(476,700);
        jf.CreateJFrame();
        Server server = new Server();
        server.setNowPort(9007);
        server.setJframe(jf);
        SomeOperate someOperate = new SomeOperate();
        someOperate.setJf(jf);
        someOperate.setServer(server);
        someOperate.operate();
        server.server();

    }
}
