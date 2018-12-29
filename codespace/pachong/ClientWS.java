package pachong;


import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;



public class ClientWS {

    public static void main(String[] args) {

        try {
            // 1.�������ʵ�url
            URL url = new URL("http://127.0.0.1:8100/userdataservice/user");

            // 2. �������ʵ�����
            QName qname = new QName("http://thzm.com/wsdl", "USI");

            // 3.��������Ķ��� url
            Service service1 = Service.create(url, qname);

            // 4.�õ��������Ľӿڶ���
            IUS user = (IUS) service1.getPort(IUS.class);

            String result = user.queryRoleData();

            System.out.println("-->java�ͻ��˷���queryRoleData�Ľ��Ϊ:" + result);

            String result1 = user.queryGroupByRoleCount();

            System.out.println("java�ͻ��˷���queryGroupByRoleCount�Ľ��Ϊ:" + result1);

            String result2 = user.queryStuAndkmCount("֣����");

            System.out.println("java�ͻ��˷���queryStuAndkmCount�Ľ��Ϊ:" + result2);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}