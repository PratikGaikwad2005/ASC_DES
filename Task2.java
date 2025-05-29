package task2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task2 extends JFrame {
    private JTextField inputField;
    private JTextArea resultArea;
    private JRadioButton ascButton, descButton;

    public Task2() {
        setTitle("Number Sorter");
        setSize(550, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setResizable(false);
        setLayout(new BorderLayout(10, 10));

        // ---------- Input Panel ----------
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);

        JLabel label = new JLabel("Enter numbers (separated by space or comma):");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        inputPanel.add(label, gbc);

        inputField = new JTextField(30);
        gbc.gridy = 1;
        inputPanel.add(inputField, gbc);

        add(inputPanel, BorderLayout.NORTH);

        // ---------- Options Panel ----------
        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        ascButton = new JRadioButton("Ascending");
        descButton = new JRadioButton("Descending");
        ButtonGroup group = new ButtonGroup();
        group.add(ascButton);
        group.add(descButton);
        ascButton.setSelected(true);
        optionPanel.add(new JLabel("Sort Order:"));
        optionPanel.add(ascButton);
        optionPanel.add(descButton);
        add(optionPanel, BorderLayout.CENTER);

        // ---------- Button Panel ----------
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton sortButton = new JButton("Sort Numbers");
        sortButton.setPreferredSize(new Dimension(150, 30));
        sortButton.addActionListener(this::handleSort);
        buttonPanel.add(sortButton);
        add(buttonPanel, BorderLayout.EAST);

        // ---------- Output Panel ----------
        resultArea = new JTextArea(6, 40);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Sorted Output"));
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void handleSort(ActionEvent e) {
        String input = inputField.getText().trim();

        if (input.isEmpty()) {
            showError("Please enter some numbers.");
            return;
        }

        try {
            String[] tokens = input.split("[,\\s]+");
            List<Integer> numbers = new ArrayList<>();

            for (String token : tokens) {
                numbers.add(Integer.parseInt(token));
            }

            boolean ascending = ascButton.isSelected();
            if (ascending) {
                Collections.sort(numbers);
            } else {
                numbers.sort(Collections.reverseOrder());
            }

            resultArea.setText("Sorted (" + (ascending ? "Ascending" : "Descending") + "):\n" + numbers);
        } catch (NumberFormatException ex) {
            showError("Invalid input! Only integers separated by commas or spaces are allowed.");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Task2 app = new Task2();
            app.setVisible(true);
        });
    }
}
