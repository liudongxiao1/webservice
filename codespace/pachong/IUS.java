package pachong;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace="http://thzm.com/wsdl")
public interface IUS
{

	//查询角色的数据
		@WebMethod
		public String  queryRoleData();
		
		// 找出学生表各个职务的学生数量和职务名称
		@WebMethod
		public String  queryGroupByRoleCount();
		
		//-- **学生所学课程的数量
		@WebMethod
		public String  queryStuAndkmCount(String name);

		@WebMethod
		public String checkUserLogin(String username,String password);
		
		@WebMethod
		public String getdata();
		
		@WebMethod
		public String  getantv1();

}
