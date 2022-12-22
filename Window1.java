package graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Player;
import game.Table;
import listener.Listener1;

public class Window1 extends JFrame {
    Table table = new Table();

    public Window1(Table table) {
        this.table = table;
        // LES JOUEURS
        this.table.getPlayer()[0] = new Player(1, "Ny Kanto");
        this.table.getPlayer()[1] = new Player(2, "Kaliana");

        // FENETRE
        setSize(640, 480);
        setTitle("Game of point");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(50, 50, 50));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setVisible(true);

        // TITRE LABEL
        JLabel title_label = new JLabel();
        title_label.setBackground(new Color(25, 25, 25));
        title_label.setForeground(new Color(25, 255, 0));
        title_label.setFont(new Font("Ink Free", Font.BOLD, 75));
        title_label.setHorizontalAlignment(JLabel.CENTER);
        title_label.setText("Game-Of-Points");
        title_label.setOpaque(true);

        // TITRE PANEL
        JPanel title_panel = new JPanel();
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 1366, 200);

        // BOUTTONS
        JPanel button_panel = new JPanel();
        button_panel.setLayout(new GridLayout(table.getWidth(), table.getHeight()));
        button_panel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < this.table.getButtons().length; i++) {
            for (int j = 0; j < this.table.getButtons().length; j++) {
                this.table.setButtons(new JButton(), i, j);
                button_panel.add(this.table.getButtons()[i][j]);
                this.table.getButtons()[i][j].setFont(new Font("MV Boli", Font.BOLD, 120));
                this.table.getButtons()[i][j].setFocusable(false);
                this.table.getButtons()[i][j].addActionListener(new Listener1(title_label,this.table));
            }
        }

        title_panel.add(title_label);
        this.add(title_panel,BorderLayout.NORTH);
        this.add(button_panel);

        // this.table.firstTurn();
    }
}