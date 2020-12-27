import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

    public static final int SERVER_PORT = 8000;
    public static final String SERVER_HOST = "localhost";

    public static void main(String[] args) throws IOException {
        MySocket socket = new MySocket(SERVER_HOST, SERVER_PORT);

        //Thread fluxe d'entrada
        new Thread() {
            public void run() {
                String lineaEntrada;
                BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in)); //Entrada per teclat

                try {
                    while((lineaEntrada = teclado.readLine()) != null){ //Sempre que hi hagi algo per llegir
                        socket.println(lineaEntrada); //Mètode que usa printWriter per escriure el contingut i fa flush
                    }
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();

        //Thread fluxe sortida
        new Thread() {
            public void run() {
                String lineaSortida;
                while ((lineaSortida = socket.readLine()) != null){
                    System.out.println(lineaSortida);
                }
                //socket.close();
            }
        }.start();
    }
    
}
