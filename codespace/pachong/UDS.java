package pachong;

import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;


//ʹ��webserviceͳһ�������ݷ��ʽӿ��м��
@SOAPBinding(style=SOAPBinding.Style.RPC)
public class UDS {

	public static void main(String[] args) {
		System.out.println("webservice  service  is  start...");

		Endpoint.publish("http://127.0.0.1:8200/userdataservice/user",
				new USI());
		
	

		System.out.println("UserDataService  ���񷢲��ɹ�....");

	}

}
