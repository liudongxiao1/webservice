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
            System.out.println("����һ�����̱߳��ֶԿͻ�����Ӧ��,�̵߳�������:-->"+Thread.currentThread().getName());

            //���������ܵ��ͻ�������Ϣ
            BufferedReader br =  new BufferedReader(new InputStreamReader(socket.getInputStream(),"gbk"));
            String  serverReceiverMsg=br.readLine();

            System.out.println("�������õ��Ŀͻ��˷��͵���ϢΪ:"+serverReceiverMsg);
            String[]  msgs=null;
            //ѧ����ѧ�γ̵�����|����Т
            if(serverReceiverMsg.contains("|"))
            {
                  msgs= serverReceiverMsg.split("\\|");
                System.out.println(msgs[0]+","+msgs[1]);

            }

            /** System.out.println("�������������Ϣ**********");
             //������������Ϣ���Ϳͻ���
             BufferedReader  sbr =  new BufferedReader(new InputStreamReader(System.in));
             String  servermsg=sbr.readLine();**/

           // Thread.sleep(40*1000);

          //  String  servermsg=Thread.currentThread().getName()+", ��ɵ���������:"+serverReceiverMsg;
            String   servermsg="";
            if(serverReceiverMsg.contains("��ѯ��ɫ"))
            {
                //1.�������ʵ�url
                URL url  = new URL("http://127.0.0.1:8100/userdataservice/user");

                //2. �������ʵ�����
                QName qname = new QName("http://thzm.com/wsdl","USI");
                //3.��������Ķ���  url
                Service service  = Service.create(url,qname);
                //4.�õ��������Ľӿڶ���
                IUS user=(IUS) service.getPort(IUS.class);
                   servermsg=user.queryRoleData();

                System.out.println("-->java�ͻ��˷���queryRoleData�Ľ��Ϊ:"+servermsg);

                //

            }//ѧ�������ְ���ѧ��������ְ������
            else  if(serverReceiverMsg.contains("ѧ��ְ���Ӧ������"))
            {
                //1.�������ʵ�url
                URL url  = new URL("http://127.0.0.1:8100/userdataservice/user");

                //2. �������ʵ�����
                QName qname = new QName("http://thzm.com/wsdl","USI");
                //3.��������Ķ���  url
                Service service  = Service.create(url,qname);
                //4.�õ��������Ľӿڶ���
                IUS user=(IUS) service.getPort(IUS.class);
                servermsg=user.queryGroupByRoleCount();

                System.out.println("-->java�ͻ��˷���queryGroupByRoleCount�Ľ��Ϊ:"+servermsg);
            }//**ѧ����ѧ�γ̵�����
            else  if(msgs[0].contains("ѧ����ѧ�γ̵�����"))
            {

                //1.�������ʵ�url
                URL url  = new URL("http://127.0.0.1:8100/userdataservice/user");

                //2. �������ʵ�����
                QName qname = new QName("http://thzm.com/wsdl","USI");
                //3.��������Ķ���  url
                Service service  = Service.create(url,qname);
                //4.�õ��������Ľӿڶ���
                IUS user=(IUS) service.getPort(IUS.class);
                servermsg=user.queryStuAndkmCount(msgs[1]);

                System.out.println("-->java�ͻ��˷���queryStuAndkmCount�Ľ��Ϊ:"+servermsg);

            }
            //�������ͣ
            Thread.sleep(30*1000);

            //���͸��ͻ��˶�
            //PrintWriter���Թ����ַ������ֽ������߼���
            PrintWriter pw=  new PrintWriter(socket.getOutputStream());

            pw.println(servermsg);
            pw.flush();//�������͵�Ŀ��
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }



    }
}