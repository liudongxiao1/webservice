package pachong;

import java.util.List;

import javax.jws.WebService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebService(portName="userservice",
serviceName="USI",
targetNamespace="http://thzm.com/wsdl",
endpointInterface="pachong.IUS")
public class USI implements IUS
{

	public String queryRoleData()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryRoleData  start...  ");

		DB db = new DB();

		List<Role> lists = db.queryRoleData();

		System.out.println("--->" + lists.size());

		// webservice发布的数据应该是各个平台和语言统一能够解析的数据格式:
		// json [{},{},{}]

		JSONArray array = new JSONArray();
		for (Role role : lists) {

			JSONObject obj = new JSONObject();
			obj.put("id", role.getRid());
			obj.put("rname", role.getRname());
			array.add(obj);
		}
		System.out.println("JSON-->" + array.toString());

		return array.toString();

		
	}

	public String queryGroupByRoleCount()
	{
		// TODO Auto-generated method stub
System.out.println("UserServiceImpl  is queryGroupByRoleCount  start...  ");
		
		DB db = new DB();
		
		List<StuAndRole> lists = db.queryRoleGroupCount();

		System.out.println("--->" + lists.size());
		JSONArray array = new JSONArray();
		for (StuAndRole crole : lists) {

			JSONObject obj = new JSONObject();
			obj.put("rname", crole.getRname());
			obj.put("rcount", crole.getRcount());
			array.add(obj);
		}
		System.out.println("JSON-->" + array.toString());

		return array.toString();

	}

	public String queryStuAndkmCount(String name)
	{
		// TODO Auto-generated method stub
System.out.println("UserServiceImpl  is queryGroupByRoleCount  start...  ");
		
		DB db = new DB();
		
		String  data=db.queryStuAndkmCount(name);
		
		System.out.println("data-->"+data);
		return data;

	}

	public String checkUserLogin(String username, String password)
	{
		// TODO Auto-generated method stub
		DB db=new DB();
		int a=db.checkUserLogin(username, password);
		System.out.println("UserServiceImpl  is checkUserLogin  start...  ");
         System.out.println(a);
		if(a>0)
		{
			return "登录成功";
		}
		return "登录失败";

	
	}

	public String getdata()
	{
		System.out.println("UserServiceImpl  is getdata  start... ");
		// TODO Auto-generated method stub
		DB db=new DB();
		List<Mume> list=db.getdata();
		System.out.println("--->" + list.size());
		String strJson = com.alibaba.fastjson.JSONArray.toJSONString(list);
		System.out.println("strJson-->" + strJson);

		return strJson;

		
		
	}

	public String getantv1()
	{
		System.out.println("UserServiceImpl  is getantv1  start...");
		DB db=new DB();
		List<Data> list=db.getantv1();
		String str=com.alibaba.fastjson.JSONArray.toJSONString(list);
		System.out.println("str-------->"+str);
		// TODO Auto-generated method stub
		return str;
	}
	
	
	public static void main(String[] args)
	{
		USI u=new USI();
		String str=u.getantv1();
		System.out.println(str);
	}

}
