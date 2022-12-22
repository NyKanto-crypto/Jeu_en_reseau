package game;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Table {
    int width;
    int height;
    Player[] player = new Player[2];
    boolean turn;
    int[][] point;
    JButton[][] buttons;

    public Table(int width, int height) {
        this.width = width;
        this.height = height;
        this.point = new int[height][width];
        this.buttons = new JButton[width][height];
    }

    public Table() {
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Player[] getPlayer() {
        return player;
    }

    public void setPlayer(Player[] player) {
        this.player = player;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int[][] getPoint() {
        return point;
    }

    public void setPoint(int[][] point) {
        this.point = point;
    }

    public JButton[][] getButtons() {
        return buttons;
    }
    public void setButtons(JButton buttons, int i, int j) {
        this.buttons[i][j] = buttons;
    }

    // NUMERIC TABLE OF THE GAME
    public int[][] table() {
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                this.getPoint()[i][j] = 0;
            }
        }
        return this.getPoint();
    }

    // FIRST TURN
    public void firstTurn() {
        Random random = new Random();

        if (random.nextInt(2) == 0) {
            this.setTurn(true);
        } else {
            this.setTurn(false);
        }
    }

    // PLAYER ADD POINT AT NUMERIC TABLE
    public void add_point(Player p, int x, int y) {
        this.getPoint()[y][x] = p.getId();
    }

    // VERIFY THE HORIZONTAL
    public void H(Player p, int x, int y, JLabel title_label) {
        int count = 0;
        int xp = x;
        int compte = 0;
        if (x >= 0 && x < this.getWidth() - 1) {
            while (this.point[y][x] == p.getId()) {
                x++;
                if (this.point[y][x - 1] == this.point[y][x]) {
                    count++;
                }
                if (x == this.getWidth() - 1) {
                    break;
                }
            }
        }
        if (xp < this.getWidth() && xp >= 1) {
            while (this.point[y][xp] == p.getId()) {
                xp--;
                if (this.point[y][xp + 1] == this.point[y][xp]) {
                    compte++;
                }
                if (xp == 0) {
                    break;
                }
            }
        }
        if (count + compte == 3) {
            System.out.println(p.nom + " a gagne");
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons.length; j++) {
                    this.buttons[i][j].setEnabled(false);
                }
            }
            title_label.setText(p.getNom() + " wins");
        }
    }

    // VERIFY THE VERTICAL
    public void V(Player p, int x, int y, JLabel title_label) {
        int count = 0;
        int yp = y;
        int compte = 0;
        if (y >= 0 && y < this.getHeight() - 1) {
            while (this.point[y][x] == p.getId()) {
                y++;
                if (this.point[y - 1][x] == this.point[y][x]) {
                    count++;
                }
                if (y == this.getHeight() - 1) {
                    break;
                }
            }
        }
        if (yp < this.getWidth() && yp >= 1) {
            while (this.point[yp][x] == p.getId()) {
                yp--;
                if (this.point[yp + 1][x] == this.point[yp][x]) {
                    compte++;
                }
                if (yp == 0) {
                    break;
                }
            }
        }
        if (count + compte == 3) {
            System.out.println(p.nom + " a gagne");
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons.length; j++) {
                    this.buttons[i][j].setEnabled(false);
                }
            }
            title_label.setText(p.getNom() + " wins");
        }
    }

    // VERIFY LEFT UP TO RIGHT DOWN
    public void OGD(Player p, int x, int y, JLabel title_label) {
        int count = 0;
        int yp = y;
        int compte = 0;
        int xp = x;
        if (x >= 0 && x < this.getWidth() - 1 && y >= 0 && y < this.getHeight() - 1) {
            while (this.point[y][x] == p.getId()) {
                y++;
                x++;
                if (this.point[y - 1][x - 1] == this.point[y][x]) {
                    count++;
                }
                if (y == this.getHeight() - 1 || x == this.getWidth() - 1) {
                    break;
                }
            }
        }
        if (xp < this.getWidth() && xp >= 1 && yp < this.getHeight() && yp >= 1) {
            while (this.point[yp][xp] == p.getId()) {
                yp--;
                xp--;
                if (this.point[yp + 1][xp + 1] == this.point[yp][xp]) {
                    compte++;
                }
                if (yp == 0 || xp == 0) {
                    break;
                }
            }
        }
        if (count + compte == 3) {
            System.out.println(p.nom + " a gagne");
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons.length; j++) {
                    this.buttons[i][j].setEnabled(false);
                }
            }
            title_label.setText(p.getNom() + " wins");
        }
    }

    // VERIFY RIGHT UP TO LEFT DOWN
    public void ODG(Player p, int x, int y, JLabel title_label) {
        int count = 0;
        int yp = y;
        int compte = 0;
        int xp = x;
        if (x < this.getWidth() && x >= 1 && y >= 0 && y < this.getHeight() - 1) {
            while (this.point[y][x] == p.getId()) {
                y++;
                x--;
                if (this.point[y - 1][x + 1] == this.point[y][x]) {
                    count++;
                }
                if (y == this.getHeight() - 1 || x == 0) {
                    break;
                }
            }
        }
        if (xp >= 0 && xp < this.getWidth() - 1 && yp < this.getHeight() && yp >= 1) {
            while (this.point[yp][xp] == p.getId()) {
                yp--;
                xp++;
                if (this.point[yp + 1][xp - 1] == this.point[yp][xp]) {
                    compte++;
                }
                if (yp == 0 || xp == this.getWidth() - 1) {
                    break;
                }
            }
        }
        if (count + compte == 3) {
            System.out.println(p.nom + " a gagne");
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons.length; j++) {
                    this.buttons[i][j].setEnabled(false);
                }
            }
            title_label.setText(p.getNom() + " wins");
        }
    }

}