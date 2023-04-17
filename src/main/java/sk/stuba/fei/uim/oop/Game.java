package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class Game extends JFrame {

    JButton resetButton;
    JButton checkButton;
    Label counter;
    JLabel size;
    JSlider slider;

    public Game() {
        this.getContentPane().setLayout(new BorderLayout());

        JPanel bottomMenu = new JPanel();
        bottomMenu.setSize(462, 538);
        bottomMenu.setLayout(new BorderLayout());
        bottomMenu.setVisible(true);

        GameLogic gameLogic = new GameLogic(this);
        this.addKeyListener(gameLogic);



        this.counter = new Label("U suck penis");
        bottomMenu.add(counter,BorderLayout.WEST);

        resetButton = new JButton("RESTART");
        resetButton.setFocusable(false);
        resetButton.setActionCommand("resetButton");
        resetButton.addActionListener(gameLogic);
        bottomMenu.add(resetButton,BorderLayout.EAST);

        checkButton = new JButton("Check");
        checkButton.setFocusable(false);
        checkButton.setActionCommand("checkButton");
        checkButton.addActionListener(gameLogic);
        bottomMenu.add(checkButton,BorderLayout.SOUTH);

        slider = new JSlider(JSlider.HORIZONTAL,8,12,8);
        slider.setMajorTickSpacing(2);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);

        Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
        labelTable.put(8, new JLabel("8"));
        labelTable.put(10, new JLabel("10"));
        labelTable.put(12, new JLabel("12"));
        slider.setLabelTable(labelTable);
        slider.setPaintLabels(true);
        slider.addChangeListener(gameLogic);
        bottomMenu.add(slider,BorderLayout.CENTER);


        bottomMenu.add(gameLogic.getSizeLabel(),BorderLayout.PAGE_START);



       // this.add(board,BorderLayout.CENTER);
        this.add(bottomMenu,BorderLayout.SOUTH);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
        this.setTitle("WaterPipes");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,800);

    }



}
// zmena slidera bude vytvarat novy board