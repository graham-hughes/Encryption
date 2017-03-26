package com.grahamhughes.caesar;

/**
 * Created by grahamhughes on 3/24/17.
 * Referenced blog.udemy.com/java-gui-tutorial/ for GUI help
 * <p>
 * Basic GUI for Caesar encryption
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GraphicalUserInterface extends JFrame {
    private static final int HEIGHT = 200;
    private static final int WIDTH = 700;
    private JLabel shift;
    private JTextField shiftBy;
    private JTextArea input, output;
    private JButton encrypt, decrypt;
    private EncryptButtonHandler encryptHandler;
    private DecryptButtonHandler decryptHandler;
    private ShiftByHandler shiftByHandler;

    private Caesar caesar;

    public GraphicalUserInterface() {
        shift = new JLabel("Shifts by: 0");
        output = new JTextArea("Output");
        input = new JTextArea("Input (ALL CAPS)");
        shiftBy = new JTextField("Shift by integer");
        encrypt = new JButton("Encrypt");
        decrypt = new JButton("Decrypt");
        encryptHandler = new EncryptButtonHandler();
        decryptHandler = new DecryptButtonHandler();
        shiftByHandler = new ShiftByHandler();

        input.setLineWrap(true);
        output.setLineWrap(true);
        input.setWrapStyleWord(true);
        output.setWrapStyleWord(true);
        output.setEditable(false);

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

        caesar = new Caesar(0); // Defaults to zero shift
    }

    private class EncryptButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                output.setText("Encrypted Message: " + caesar.encrypt(input.getText()));
            } catch (IllegalArgumentException exception) {
                input.setText(exception.getMessage());
            }
        }
    }

    private class DecryptButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                output.setText("Decrypted Message: " + caesar.decrypt(input.getText()));
            } catch (IllegalArgumentException exception) {
                input.setText(exception.getMessage());
            }
        }
    }

    private class ShiftByHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int shiftFactor = Integer.parseInt(shiftBy.getText());
                shift.setText("Shifts by: " + shiftFactor);
                caesar = new Caesar(shiftFactor);
            } catch (NumberFormatException exception) {
                shift.setText("Shifts by: " + "Error please input integer value");
            }
        }
    }
}
