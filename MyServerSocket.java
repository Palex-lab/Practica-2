import java.io.IOException;
import java.net.ServerSocket;

public class MyServerSocket extends ServerSocket {

    MySocket serverSocket;

    public MyServerSocket(int port) throws IOException {
        super(port);
    }
    
    public MySocket accept(){
        try {
            serverSocket = new MySocket(super.accept());
            return serverSocket;
        } catch (IOException e) {
            return null;
        }
    }

    public void close(){
        try {
            super.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
