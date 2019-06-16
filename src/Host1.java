

public class Host1 {

    public static void main(String [] args){
        xJFrame jf = new xJFrame();
        jf.setName("主机1");
        jf.setLocation(-7,400);
        jf.CreateJFrame();
        Server server = new Server();
        server.setNowPort(9001);
        server.setJframe(jf);
        SomeOperate someOperate = new SomeOperate();
        someOperate.setJf(jf);
        someOperate.setServer(server);
        someOperate.operate();
        server.server();
    }
}
