package br.com.andrenunes.calc.view;

import javax.swing.*;
import java.awt.*;

public class Buttons extends JButton {
    public Buttons(String label, Color color) {
        setText(label);
        setFont(new Font("courier", Font.PLAIN, 20));
        setOpaque(true);
        setBackground(color);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.black, 2, true));

    }

}
