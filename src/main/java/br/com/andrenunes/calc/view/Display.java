package br.com.andrenunes.calc.view;

import br.com.andrenunes.calc.model.Memory;
import br.com.andrenunes.calc.model.MemoryObserver;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Display extends JPanel implements MemoryObserver {


    private final JLabel label;
    private Font font1;


    public Font getFont() {

        String filename = "src/main/java/br/com/andrenunes/calc/font/fontDigit.ttf";
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
            font = font.deriveFont(Font.PLAIN, 45);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            JLabel l = new JLabel();
            l.setFont(font);
            font1 = font;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Deu ruim");
        }

        return font1;
    }

    public Display() {
        Memory.getInstance().addObserver(this);

        setBorder(BorderFactory.createMatteBorder(3, 2, 2, 2, Color.BLACK));
        //  setBorder(BorderFactory.createLineBorder(Color.BLUE, 10, true));
        setBackground(new Color(58, 58, 58));
        label = new JLabel(Memory.getInstance().getCurrentText());
        label.setForeground(new Color(26, 240, 0, 255));
        label.setFont(getFont());
        setLayout(new FlowLayout(FlowLayout.RIGHT, 7, 18));
        add(label);
    }


    @Override
    public void alternateValue(String newValue) {
        label.setText(newValue);
    }
}
