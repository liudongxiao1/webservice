package pachong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DB
{
private Connection  conn;
static String  urlimg="";
 Map<String,String> m;
static
{
	urlimg=FilePropertiesUtils.getImageUtilPath();
}

	public  DB()
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ldxdb?user=root&password=&useUnicode=true&characterEncoding=utf-8&useSSL=false", 
					"root", "");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List   queryRoleData()
	{
		String sql="SELECT  *  FROM   t_role";
		
		List<Role>  lists  = new ArrayList<Role>();
		
		try {
			PreparedStatement  pstmt=conn.prepareStatement(sql);
		 
			ResultSet  rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				Role  role  = new Role();
				role.setRid(rs.getInt(1));
				role.setRname(rs.getString(2));
				
				lists.add(role);
			}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return lists;
	}
	public  List   queryRoleGroupCount()
	{
    String sql="SELECT  rname,COUNT(sjob)    FROM  t_stus  RIGHT  JOIN  t_role ON sjob=rid  GROUP BY  rname";
		
		List<StuAndRole>  lists  = new ArrayList<StuAndRole>();
		
		try {
			PreparedStatement  pstmt=conn.prepareStatement(sql);
		 
			ResultSet  rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				StuAndRole  crole  = new StuAndRole();
				crole.setRname(rs.getString(1));
				crole.setRcount(rs.getInt(2));
				
				lists.add(crole);
			}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return lists;
	}
	
	public  String   queryStuAndkmCount(String  stuName)
	{
       String sql="SELECT COUNT(kid),sname  FROM (SELECT   * FROM  t_stus  WHERE  sname=?) tmp INNER  JOIN   t_score  ON tmp.sid=t_score.sid  GROUP  BY sname";
		
       String data="";
		
		try {
			PreparedStatement  pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,stuName);
			ResultSet  rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				data=rs.getInt(1)+","+rs.getString(2);
			}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return data;
	}
	public  int   checkUserLogin(String name,String pwd)
	{
		String sql="SELECT  COUNT(*) FROM  t_stus  WHERE sname=? AND  spwd=?";
		
		PreparedStatement pstmt =null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			
			ResultSet   rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				return  rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return  020;
		
	}

         public List getdata()
         {
        	 List<Mume> list=new ArrayList<Mume>();
        	 String sql="select tid,tname,imgpath from t_menu";
        	 try
			{
				PreparedStatement p=conn.prepareStatement(sql);
				ResultSet r=p.executeQuery();
				while(r.next())
				{
					Mume m=new Mume();
					m.setTid(r.getInt(1));
					m.setTname(r.getString(2));
					
					m.setImgpath(urlimg+r.getString(3));
					list.add(m);
				}
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	 return list;
         }
       public  List getantv1()
       {
    	   String sql="select count(ssex),ssex from t_stus group by ssex";
    	   List<Data> listdata = new ArrayList<Data>();
    	   try
		{
			PreparedStatement p=conn.prepareStatement(sql);
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				Data d=new Data();
				d.setCount(r.getInt(1));
				d.setName(r.getString(2));
				listdata.add(d);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   
    	   return listdata;
    	    
    	   
       }
        
}
