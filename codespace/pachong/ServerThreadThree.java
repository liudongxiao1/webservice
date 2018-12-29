package pachong;





import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

public class ServerThreadThree   extends  Thread {

    Socket socket;
    public ServerThreadThree( Socket socket)
    {
        this.socket=socket;
    }
    public void  run()
    {
        try
        {
            System.out.println("产生一个子线程保持对客户机的应答,线程的名字是:-->"+Thread.currentThread().getName());

            //服务器接受到客户机的消息
            BufferedReader br =  new BufferedReader(new InputStreamReader(socket.getInputStream(),"gbk"));
            String  serverReceiverMsg=br.readLine();

            System.out.println("服务器得到的客户端发送的消息为:"+serverReceiverMsg);
            String[]  msgs=null;
            //学生所学课程的数量|刘冬孝
            if(serverReceiverMsg.contains("|"))
            {
                  msgs= serverReceiverMsg.split("\\|");
                System.out.println(msgs[0]+","+msgs[1]);

            }

            /** System.out.println("请服务器构建消息**********");
             //服务器构建消息发送客户机
             BufferedReader  sbr =  new BufferedReader(new InputStreamReader(System.in));
             String  servermsg=sbr.readLine();**/

           // Thread.sleep(40*1000);

          //  String  servermsg=Thread.currentThread().getName()+", 完成的任务名称:"+serverReceiverMsg;
            String   servermsg="";
            if(serverReceiverMsg.contains("查询角色"))
            {
                //1.构建访问的url
                URL url  = new URL("http://127.0.0.1:8100/userdataservice/user");

                //2. 构建访问的名称
                QName qname = new QName("http://thzm.com/wsdl","USI");
                //3.创建服务的对象  url
                Service service  = Service.create(url,qname);
                //4.得到服务对象的接口对象
                IUS user=(IUS) service.getPort(IUS.class);
                   servermsg=user.queryRoleData();

                System.out.println("-->java客户端访问queryRoleData的结果为:"+servermsg);

                //

            }//学生表各个职务的学生数量和职务名称
            else  if(serverReceiverMsg.contains("学生职务对应的数量"))
            {
                //1.构建访问的url
                URL url  = new URL("http://127.0.0.1:8100/userdataservice/user");

                //2. 构建访问的名称
                QName qname = new QName("http://thzm.com/wsdl","USI");
                //3.创建服务的对象  url
                Service service  = Service.create(url,qname);
                //4.得到服务对象的接口对象
                IUS user=(IUS) service.getPort(IUS.class);
                servermsg=user.queryGroupByRoleCount();

                System.out.println("-->java客户端访问queryGroupByRoleCount的结果为:"+servermsg);
            }//**学生所学课程的数量
            else  if(msgs[0].contains("学生所学课程的数量"))
            {

                //1.构建访问的url
                URL url  = new URL("http://127.0.0.1:8100/userdataservice/user");

                //2. 构建访问的名称
                QName qname = new QName("http://thzm.com/wsdl","USI");
                //3.创建服务的对象  url
                Service service  = Service.create(url,qname);
                //4.得到服务对象的接口对象
                IUS user=(IUS) service.getPort(IUS.class);
                servermsg=user.queryStuAndkmCount(msgs[1]);

                System.out.println("-->java客户端访问queryStuAndkmCount的结果为:"+servermsg);

            }
            //有意的暂停
            Thread.sleep(30*1000);

            //发送给客户端端
            //PrintWriter可以构建字符流和字节流，高级流
            PrintWriter pw=  new PrintWriter(socket.getOutputStream());

            pw.println(servermsg);
            pw.flush();//立即发送到目标
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }



    }
}