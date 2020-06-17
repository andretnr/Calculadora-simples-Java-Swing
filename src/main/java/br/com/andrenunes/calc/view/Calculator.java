package br.com.andrenunes.calc.view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Calculator extends JFrame {


    public Calculator() throws IOException, FontFormatException {

        layoutOrganize();

        setSize(232, 335);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void layoutOrganize() throws IOException, FontFormatException {
        setLayout(new BorderLayout());


        Display disp = new Display();
        disp.setPreferredSize(new Dimension(233, 70));
        add(disp, BorderLayout.NORTH);

        Keyboard kb = new Keyboard();
        add(kb, BorderLayout.CENTER);
    }

    public static void main(String[] args) throws IOException, FontFormatException {

        new Calculator();
    }
}
