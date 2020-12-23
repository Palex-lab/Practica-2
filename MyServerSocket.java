package Sockets;

import Sockets.MySocket;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;

public class MyServerSocket extends ServerSocket {


    public MyServerSocket(int port) throws IOException {
        super(port);
    }

    //Listens for a connection to be made to this socket and accepts it.
    public Socket accept(){
        try{
            return super.accept();
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public MySocket myAccept(){
        try{
            Socket socket = super.accept();
		    return new MySocket(socket);
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
    //Close the current Server Socket
    public void close(){
        try{
            super.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    /*Binds the socket to a specific address (IP+PORT)*/
    public void bind(SocketAddress endpoint, int backlog){
        try{
            super.bind(endpoint, backlog);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public ServerSocketChannel getChannel(){
        return super.getChannel();
    }

    public InetAddress getInetAddress(){
            return super.getInetAddress();
    }
    public boolean isClosed(){
        return super.isClosed();
    }
    public void setReceiveBufferSize(int size){
        try{
            super.setReceiveBufferSize(size);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setSoTimeout(int timeout){
        try{
            super.setSoTimeout(timeout);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}