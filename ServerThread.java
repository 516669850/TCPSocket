package Socket;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    Socket socket = null;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;
        try{
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String info = null;
            while((info = bufferedReader.readLine()) != null){
                System.out.println("我是服务器，客户端说：" + info);
            }
            socket.shutdownInput();

            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            printWriter.write("欢迎！");
            printWriter.flush();
            socket.shutdownOutput();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(printWriter != null)
                    printWriter.close();
                if(outputStream != null)
                    outputStream.close();
                if(bufferedReader != null)
                    bufferedReader.close();
                if(inputStreamReader != null)
                    inputStreamReader.close();
                if(inputStream != null)
                    inputStream.close();
                if(socket != null)
                    socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
