package br.com.andrenunes.calc.view;

import br.com.andrenunes.calc.model.Memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Keyboard extends JPanel implements ActionListener {

    private final Color COLOR1 = new Color(87, 87, 87);
    private final Color COLOR2 = new Color(155, 155, 155);
    private final Color COLOR3 = new Color(43, 182, 0);

    public Keyboard() {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setBackground(Color.BLACK);
        setLayout(layout);
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        addbutton("AC", COLOR1, c, 0, 0);
        addbutton("±", COLOR1, c, 1, 0);
        addbutton("%", COLOR1, c, 2, 0);
        addbutton("/", COLOR3, c, 3, 0);

        addbutton("7", COLOR2, c, 0, 1);
        addbutton("8", COLOR2, c, 1, 1);
        addbutton("9", COLOR2, c, 2, 1);
        addbutton("*", COLOR3, c, 3, 1);

        addbutton("4", COLOR2, c, 0, 2);
        addbutton("5", COLOR2, c, 1, 2);
        addbutton("6", COLOR2, c, 2, 2);
        addbutton("-", COLOR3, c, 3, 2);

        addbutton("1", COLOR2, c, 0, 3);
        addbutton("2", COLOR2, c, 1, 3);
        addbutton("3", COLOR2, c, 2, 3);
        addbutton("+", COLOR3, c, 3, 3);

        c.gridwidth = 2;
        addbutton("0", COLOR2, c, 0, 4);
        c.gridwidth = 1;
        addbutton(",", COLOR2, c, 2, 4);
        addbutton("=", COLOR3, c, 3, 4);

    }

    private void addbutton(String label, Color color, GridBagConstraints c, int x, int y) {
        c.gridy = y;
        c.gridx = x;
        Buttons button = new Buttons(label, color);
        button.addActionListener(this);
        add(button, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            Memory.getInstance().processComand(button.getText());
        }

    }
}
