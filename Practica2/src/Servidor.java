import java.util.concurrent.ConcurrentHashMap;

import java.io.IOException;

public class Servidor implements Runnable {

    public static ConcurrentHashMap<String, MySocket> llistausuaris = new ConcurrentHashMap<>();
    public static boolean continuar = false;
    MySocket mysocket;
    String nick;

    public Servidor(String nick, MySocket mysocket) throws IOException {
        this.nick = nick;
        this.mysocket = mysocket;
    }

    public static void main(String[] args) throws Exception {
        MyServerSocket myserversocket = new MyServerSocket(8000);
        System.out.println("Server ready");

        while (true) {
            MySocket ms = myserversocket.accept();
            while (continuar == false) {
                ms.println("Introdueix el teu nom d'usuari: ");
                String possible_nick = ms.readLine();
                if (llistausuaris.containsKey(possible_nick)) {
                    ms.println("El nom d'usuari " + possible_nick + " ja existeix.");
                } else {
                    System.out.println("S'ha connectat " + possible_nick);
                    llistausuaris.put(possible_nick, ms);
                    new Thread(new Servidor(possible_nick, ms)).start();
                    continuar = true;
                }
            }
            continuar = false;
        }
    }
    
    @Override
    public void run() {
        String linia;

        while ((linia = mysocket.readLine()) != null) {
            boolean desconnectat = desconnexio(nick, linia);
            if (desconnectat == false) {
                for (MySocket s : llistausuaris.values()) {
                    if (s != mysocket) {
                        s.println(nick + ": " + linia);
                        System.out.println(nick + " diu: " + linia);
                    }

                }

            }
        }

    }

    public boolean desconnexio(String nick, String linia) {
        boolean desconnexio = false;
        if (linia.equals("Adeu")) {
            llistausuaris.remove(nick);

            for (MySocket s : llistausuaris.values()) {
                if (s != mysocket) {
                    s.println(nick + ": Adeu");
                    s.println(nick + ": ha abandonat el xat");
                    System.out.println(nick + " diu: Adeu");
                    System.out.println(nick + " s'ha desconnectat");
                    s.close();
                }

            }
            desconnexio = true;
        }
        return desconnexio;
    }



}
