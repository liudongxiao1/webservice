package pachong;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerThree {

    //服务器端的socket
    private Socket socket;


    //服务器端
    private ServerSocket serverSocket;

    public   ServerThree()
    {

        System.out.println("ServerThree服务器启动服务..........");

        try {
            //保持对客户机的监听，是主线程main
            serverSocket = new ServerSocket(8789);


            //jdk自带的线程池  创建一个定长线程池，可控制线程最大并发数
            ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

            while(true)
            {
                //全阻塞,如果客户端有请求，会建立连接，并返回socket对象
                socket=serverSocket.accept();

                System.out.println("客户机和服务器建立了连接，可以进行通信");

                /**
                 * 采用线程，来一个客户机的请求，产生一个线程
                 * 保持对客户机的应答
                 */

                //1.0版本
                //由主线程产生子线程保持对各个客户机的应答
                //客户端一个请求，服务器端就要创建一个线程，这个对服务器的创建线程对象的开销太大。
                // new ServerThread(socket).start();

                //线程的开销很大，就应该在实际的生产环境中使用线程池

                //1.JDK本来自身的线程池//2. 自定义线程池
                fixedThreadPool.execute(new ServerThreadThree(socket));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public  static void  main(String[]  args)
    {
        ServerThree serverthree = new ServerThree();

    }
}
    
