package pachong;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace="http://thzm.com/wsdl")
public interface IUS
{

	//��ѯ��ɫ������
		@WebMethod
		public String  queryRoleData();
		
		// �ҳ�ѧ�������ְ���ѧ��������ְ������
		@WebMethod
		public String  queryGroupByRoleCount();
		
		//-- **ѧ����ѧ�γ̵�����
		@WebMethod
		public String  queryStuAndkmCount(String name);

		@WebMethod
		public String checkUserLogin(String username,String password);
		
		@WebMethod
		public String getdata();
		
		@WebMethod
		public String  getantv1();

}
