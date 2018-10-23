package Utilities;

import java.awt.*;

public class Pixel {
    private int x;
    private int y;
    private Color col;

    public Pixel(int x, int y, Color col) {
        this.x = x;
        this.y = y;
        this.col = col;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getCol() {
        return col;
    }

    public void setCol(Color col) {
        this.col = col;
    }
}
