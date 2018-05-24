package Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        try{
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = null;
            int count = 0;
            System.out.println("*****服务器启动，等待客户端连接*****");
            while(true){
                socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
                count++;
                System.out.println("客户端数量：" + count);
                System.out.println("客户端地址：" + socket.getLocalAddress());
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
