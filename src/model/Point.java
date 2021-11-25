package model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Point {

    private int x;
    private int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void increaseX() {
        this.x++;
    }
    
    public void decreaseX() {
        this.x--;
    }
    
    public void increaseY() {
        this.y++;
    }
    
    public void decreaseY() {
        this.y--;
    }
    
    public static void writePoint(int x, int y, BufferedImage bi, Color color) {
        bi.setRGB(x, y, color.getRGB());
    }
    
    public static void writePoint(Point p, BufferedImage bi, Color color) {
        writePoint(p.getX(), p.getY(), bi, color);
    }

    @Override
    public String toString() {
        return "[" + this.getX() + ", " + this.getY() + "]";
    }
}
