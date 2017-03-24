package com.company;

/**
 * Created by grahamhughes on 3/24/17.
 * Referenced blog.udemy.com/java-gui-tutorial/ for GUI help
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GraphicalUserInterface extends JFrame {
    private static final int HEIGHT = 200;
    private static final int WIDTH = 700;
    private JLabel shift, output;
    private JTextField input, shiftBy;
    private JButton encrypt, decrypt;
    private EncryptButtonHandler encryptHandler;
    private DecryptButtonHandler decryptHandler;
    private ShiftByHandler shiftByHandler;

    private Caesar caesar;

    public GraphicalUserInterface() {
        shift = new JLabel("Shifts by: ");
        output = new JLabel("Output");
        input = new JTextField("Input (ALL CAPS)");
        shiftBy = new JTextField("Shift by integer");
        encrypt = new JButton("Encrypt");
        decrypt = new JButton("Decrypt");
        encryptHandler = new EncryptButtonHandler();
        decryptHandler = new DecryptButtonHandler();
        shiftByHandler = new ShiftByHandler();

        encrypt.addActionListener(encryptHandler);
        decrypt.addActionListener(decryptHandler);
        shiftBy.addActionListener(shiftByHandler);

        Container pane = getContentPane();
        pane.setLayout(new GridLayout(3, 2));

        pane.add(shift);
        pane.add(shiftBy);
        pane.add(encrypt);
        pane.add(decrypt);
        pane.add(input);
        pane.add(output);

        setTitle("Caesar Encryption");
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private class EncryptButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            output.setText(caesar.encrypt(input.getText()));
        }
    }

    private class DecryptButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            output.setText(caesar.decrypt(input.getText()));
        }
    }

    private class ShiftByHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int shiftFactor = Integer.parseInt(shiftBy.getText());
            shift.setText("Shifts by: " + shiftFactor);
            caesar = new Caesar(shiftFactor);
        }
    }
}
