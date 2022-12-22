package listener;

import game.Table;
import graph.Window1;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.NoSuchElementException;

public class Listener1 implements ActionListener {
    JLabel title_label;
    Table table;

    public Listener1(JLabel title_label, Table table) {
        this.title_label = title_label;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

        for (int i = 0; i < this.table.getButtons().length; i++) {
            for (int j = 0; j < this.table.getButtons().length; j++) {
                if (arg0.getSource() == this.table.getButtons()[i][j]) {
                    this.table.getButtons()[i][j].setForeground(new Color(255, 0, 0));
                    this.table.getButtons()[i][j].setText("O");
                    // this.table.setTurn(false);
                    this.table.add_point(this.table.getPlayer()[0], j, i);
                    System.out.println("x = " + j);
                    System.out.println("y = " + i);
                    this.table.H(this.table.getPlayer()[0], j, i, this.title_label);
                    this.table.V(this.table.getPlayer()[0], j, i, this.title_label);
                    this.table.OGD(this.table.getPlayer()[0], j, i, this.title_label);
                    this.table.ODG(this.table.getPlayer()[0], j, i, this.title_label);

                    // title_label.setText("J2 turn");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, NoSuchElementException {
        Table table = new Table(5, 5);
        String line;
        Socket SocketOfClient;
        JLabel title_label = new JLabel();

        new Window1(table);

        BufferedWriter os;
        BufferedReader is;

        // Send a request to connect to the server listening
        // on machine 'localhost' port 9999
        SocketOfClient = new Socket("127.0.0.1", 9999);

        // Create output stream at the client (to send data to the server)
        os = new BufferedWriter(new OutputStreamWriter(SocketOfClient.getOutputStream()));
        is = new BufferedReader(new InputStreamReader(SocketOfClient.getInputStream()));

        while (true) {
            try {
                StringBuilder text = new StringBuilder();

                for (int k = 0; k < table.getWidth(); k++) {
                    for (int k2 = 0; k2 < table.getHeight(); k2++) {
                        if (k2 == table.getHeight() - 1) {
                            text.append(table.getPoint()[k][k2]);
                        } else {
                            text.append(table.getPoint()[k][k2]).append(" ");
                        }
                    }
                    if (k < table.getWidth() - 1) {
                        text.append("//");
                    }
                }

                // Write data to the output stream of the Client Socket
                os.write(text + "\n");

                // Flush data
                os.flush();

                line = is.readLine();

                if (line != null) {
                    String[] lsplit = line.split("//");
                    String[][] cplit = new String[table.getWidth()][table.getHeight()];
                    int[][] num = new int[table.getWidth()][table.getHeight()];

                    for (int i = 0; i < lsplit.length; i++) {
                        cplit[i] = lsplit[i].split(" ");
                    }

                    for (int i = 0; i < table.getWidth(); i++) {
                        for (int j = 0; j < table.getHeight(); j++) {
                            num[i][j] = Integer.parseInt(cplit[i][j]);
                        }
                    }

                    table.setPoint(num);

                    for (int i = 0; i < num.length; i++) {
                        for (int j = 0; j < num[0].length; j++) {
                            System.out.print(table.getPoint()[i][j] + " ");
                        }
                        System.out.println();
                    }

                    for (int i = 0; i < table.getWidth(); i++) {
                        for (int j = 0; j < table.getHeight(); j++) {
                            if (table.getPoint()[i][j] == 2) {
                                table.getButtons()[i][j].setText("X");
                                table.getButtons()[i][j].setEnabled(false);
                                table.H(table.getPlayer()[0], j, i, title_label);
                                table.V(table.getPlayer()[0], j, i, title_label);
                                table.OGD(table.getPlayer()[0], j, i, title_label);
                                table.ODG(table.getPlayer()[0], j, i, title_label);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
