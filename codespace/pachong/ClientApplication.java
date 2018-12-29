package pachong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * 查询角色
 * 学生职务对应的数量
 * 学生所学课程的数量|余豪
 * @author lenovo
 *
 */
public class ClientApplication
{

	private  Socket  socket;

	public ClientApplication()
	{
		System.out.println("----客户端启动向服务器发送请求的服务 ------");

		while(true)
		{
			try
			{
				//创建客户端请求服务器的套接字对象socket，描述了服务器ip,port
				socket = new Socket("127.0.0.1",8789);
				System.out.println("如果和服务器握手连接成功"+socket);

				System.out.println("请客户机输入消息:");

				//在控制台输入消息  字节流---转换成-------字符流
				BufferedReader  br =  new BufferedReader(new InputStreamReader(System.in));
				String  clientmsg=br.readLine();

				//发送给服务器端
				//PrintWriter可以构建字符流和字节流，高级流
				PrintWriter  pw=  new PrintWriter(socket.getOutputStream());
				pw.println(clientmsg);
				pw.flush();//立即发送到目标

				//客户机接受到服务器的消息
				BufferedReader  cbr =  new BufferedReader(new InputStreamReader(socket.getInputStream(),"gbk"));
				String  clientReceiverMsg=cbr.readLine();


				System.out.println("客户端接收到服务器端发送的消息为:"+clientReceiverMsg);
			}
			catch (UnknownHostException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


	public static void main(String[] args)
	{
		ClientApplication  client = new ClientApplication();
	}
}