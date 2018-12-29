package pachong;


import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;



public class ClientWS {

    public static void main(String[] args) {

        try {
            // 1.构建访问的url
            URL url = new URL("http://127.0.0.1:8100/userdataservice/user");

            // 2. 构建访问的名称
            QName qname = new QName("http://thzm.com/wsdl", "USI");

            // 3.创建服务的对象 url
            Service service1 = Service.create(url, qname);

            // 4.得到服务对象的接口对象
            IUS user = (IUS) service1.getPort(IUS.class);

            String result = user.queryRoleData();

            System.out.println("-->java客户端访问queryRoleData的结果为:" + result);

            String result1 = user.queryGroupByRoleCount();

            System.out.println("java客户端访问queryGroupByRoleCount的结果为:" + result1);

            String result2 = user.queryStuAndkmCount("郑传磊");

            System.out.println("java客户端访问queryStuAndkmCount的结果为:" + result2);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}