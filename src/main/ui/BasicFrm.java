package ui;

import javax.swing.*;
import java.awt.*;

public abstract class BasicFrm extends JFrame {
    private int width, height;

    public BasicFrm(int width, int height,String title){
        super(title);
        this.width = width;
        this.height = height;
        init();
        arrange();
        inflate();
    }

    public abstract void init();
    public abstract void arrange();
    public void inflate() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width,height);
        setLocationRelativeTo(this);
        setVisible(true);
    }
}