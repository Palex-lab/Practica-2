import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class MySocket {
   //declaración de atributos
   BufferedReader bf;
   PrintWriter pw;
   String nickname;
   Socket socket;

   // declaración de constructor
    public MySocket (String nickname, String host, int port) {
        try {
            socket = new Socket(host, port);
            bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        } catch (Exception e) {
        e.printStackTrace();
        }
    }


   
  //declaración de métodos
    public int read() {
        try {
            return bf.read();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public void write(String s) {
        pw.write(s);
    }
    public void close() {
        try {
            this.pw.close();
            this.bf.close();
            this.socket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public String readLine() {
        String s = null;
        try {
            s = this.bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public void writeLine(String s) {
        this.pw.write(s);
    }
    
    public Socket getSocket() {
        return this.socket;
    }
    
    public void sendUsername() {
        this.pw.println(this.nickname);
        System.out.println("Nickname sent is: " + this.nickname);
    }
    
    public String receiveNickname() {
        return this.readLine();
    }
    public String setNickname() {
        this.nickname = this.readLine();
        return this.nickname;
     }

  
}
  
