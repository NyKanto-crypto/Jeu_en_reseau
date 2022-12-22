package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) {

        try {
            String line1;
            String line2;

            // TRY TO OPEN A SERVER SOCKET ON PORT 9999
            // Note that we can't choose a port less than 1023
            // privileged users (root)

            System.out.println("Server is waiting to accept user ...");

            ServerSocket listener = new ServerSocket(9999);

            Socket socketOfServer1 = listener.accept();

            BufferedReader is1 = new BufferedReader(new InputStreamReader(socketOfServer1.getInputStream()));
            BufferedWriter os1 = new BufferedWriter(new OutputStreamWriter(socketOfServer1.getOutputStream()));

            Socket socketOfServer2 = listener.accept();

            BufferedReader is2 = new BufferedReader(new InputStreamReader(socketOfServer2.getInputStream()));
            BufferedWriter os2 = new BufferedWriter(new OutputStreamWriter(socketOfServer2.getOutputStream()));

            System.out.println("Accept all clients !");

            while (true) {
                line1 = is1.readLine();
                line2 = is2.readLine();

                if (line1 != null) {
                    System.out.println(line1);
                    try {
                        os2.write(line1+"\n");

                        os2.flush();
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                }

                if (line2 != null) {
                    System.out.println(line2);
                    try {
                        os1.write(line2+"\n");

                        os1.flush();
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}