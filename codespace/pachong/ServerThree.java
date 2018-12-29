package pachong;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerThree {

    //�������˵�socket
    private Socket socket;


    //��������
    private ServerSocket serverSocket;

    public   ServerThree()
    {

        System.out.println("ServerThree��������������..........");

        try {
            //���ֶԿͻ����ļ����������߳�main
            serverSocket = new ServerSocket(8789);


            //jdk�Դ����̳߳�  ����һ�������̳߳أ��ɿ����߳���󲢷���
            ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

            while(true)
            {
                //ȫ����,����ͻ��������󣬻Ὠ�����ӣ�������socket����
                socket=serverSocket.accept();

                System.out.println("�ͻ����ͷ��������������ӣ����Խ���ͨ��");

                /**
                 * �����̣߳���һ���ͻ��������󣬲���һ���߳�
                 * ���ֶԿͻ�����Ӧ��
                 */

                //1.0�汾
                //�����̲߳������̱߳��ֶԸ����ͻ�����Ӧ��
                //�ͻ���һ�����󣬷������˾�Ҫ����һ���̣߳�����Է������Ĵ����̶߳���Ŀ���̫��
                // new ServerThread(socket).start();

                //�̵߳Ŀ����ܴ󣬾�Ӧ����ʵ�ʵ�����������ʹ���̳߳�

                //1.JDK����������̳߳�//2. �Զ����̳߳�
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
    
