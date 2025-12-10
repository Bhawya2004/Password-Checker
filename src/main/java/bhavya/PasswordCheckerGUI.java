package bhavya;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class PasswordCheckerGUI extends JFrame {
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;
    private JLabel strengthLabel;
    private JLabel scoreLabel;
    private JProgressBar strengthBar;
    private JPanel criteriaPanel;
    private JLabel lengthLabel, length12Label, caseLabel, numberLabel, specialLabel;

    public PasswordCheckerGUI() {
        setTitle("Password Strength Checker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);
        setLocationRelativeTo(null);

        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 245));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setPreferredSize(new Dimension(500, 80));
        JLabel titleLabel = new JLabel("🔐 Password Strength Checker");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 240, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Password Input Section
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBackground(new Color(240, 240, 245));
        JLabel inputLabel = new JLabel("Enter Password:");
        inputLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setPreferredSize(new Dimension(400, 35));

        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setBackground(new Color(240, 240, 245));
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('•');
            }
        });

        inputPanel.add(inputLabel, BorderLayout.NORTH);
        inputPanel.add(passwordField, BorderLayout.CENTER);
        inputPanel.add(showPasswordCheckBox, BorderLayout.SOUTH);
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Strength Display Section
        JPanel strengthPanel = new JPanel();
        strengthPanel.setLayout(new BoxLayout(strengthPanel, BoxLayout.Y_AXIS));
        strengthPanel.setBackground(Color.WHITE);
        strengthPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        strengthLabel = new JLabel("Strength: -");
        strengthLabel.setFont(new Font("Arial", Font.BOLD, 18));
        strengthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        scoreLabel = new JLabel("Score: 0/5");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        scoreLabel.setForeground(new Color(100, 100, 100));
        scoreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        strengthBar = new JProgressBar(0, 5);
        strengthBar.setValue(0);
        strengthBar.setStringPainted(false);
        strengthBar.setPreferredSize(new Dimension(400, 25));
        strengthBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        strengthBar.setAlignmentX(Component.LEFT_ALIGNMENT);

        strengthPanel.add(strengthLabel);
        strengthPanel.add(Box.createVerticalStrut(5));
        strengthPanel.add(scoreLabel);
        strengthPanel.add(Box.createVerticalStrut(10));
        strengthPanel.add(strengthBar);

        mainPanel.add(strengthPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Criteria Panel
        criteriaPanel = new JPanel();
        criteriaPanel.setLayout(new BoxLayout(criteriaPanel, BoxLayout.Y_AXIS));
        criteriaPanel.setBackground(Color.WHITE);
        criteriaPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        JLabel criteriaTitle = new JLabel("Security Criteria:");
        criteriaTitle.setFont(new Font("Arial", Font.BOLD, 14));
        criteriaTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        criteriaPanel.add(criteriaTitle);
        criteriaPanel.add(Box.createVerticalStrut(10));

        lengthLabel = createCriteriaLabel("❌ At least 8 characters");
        length12Label = createCriteriaLabel("❌ At least 12 characters");
        caseLabel = createCriteriaLabel("❌ Uppercase and lowercase letters");
        numberLabel = createCriteriaLabel("❌ Contains numbers");
        specialLabel = createCriteriaLabel("❌ Contains special characters");

        criteriaPanel.add(lengthLabel);
        criteriaPanel.add(Box.createVerticalStrut(5));
        criteriaPanel.add(length12Label);
        criteriaPanel.add(Box.createVerticalStrut(5));
        criteriaPanel.add(caseLabel);
        criteriaPanel.add(Box.createVerticalStrut(5));
        criteriaPanel.add(numberLabel);
        criteriaPanel.add(Box.createVerticalStrut(5));
        criteriaPanel.add(specialLabel);

        mainPanel.add(criteriaPanel);

        add(mainPanel, BorderLayout.CENTER);

        // Add document listener for real-time checking
        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                checkPassword();
            }

            public void removeUpdate(DocumentEvent e) {
                checkPassword();
            }

            public void insertUpdate(DocumentEvent e) {
                checkPassword();
            }
        });
    }

    private JLabel createCriteriaLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private void checkPassword() {
        String pwd = new String(passwordField.getPassword());
        int score = 0;

        boolean len8 = pwd.length() >= 8;
        boolean len12 = pwd.length() >= 12;
        boolean hasCase = pwd.matches(".*[A-Z].*") && pwd.matches(".*[a-z].*");
        boolean hasNumber = pwd.matches(".*\\d.*");
        boolean hasSpecial = pwd.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");

        if (len8)
            score++;
        if (len12)
            score++;
        if (hasCase)
            score++;
        if (hasNumber)
            score++;
        if (hasSpecial)
            score++;

        // Update criteria labels
        updateCriteriaLabel(lengthLabel, len8, "At least 8 characters");
        updateCriteriaLabel(length12Label, len12, "At least 12 characters");
        updateCriteriaLabel(caseLabel, hasCase, "Uppercase and lowercase letters");
        updateCriteriaLabel(numberLabel, hasNumber, "Contains numbers");
        updateCriteriaLabel(specialLabel, hasSpecial, "Contains special characters");

        // Update strength
        String strength;
        Color color;
        if (score <= 2) {
            strength = "WEAK";
            color = new Color(220, 53, 69);
        } else if (score == 3) {
            strength = "MEDIUM";
            color = new Color(255, 193, 7);
        } else {
            strength = "STRONG";
            color = new Color(40, 167, 69);
        }

        strengthLabel.setText("Strength: " + strength);
        strengthLabel.setForeground(color);
        scoreLabel.setText("Score: " + score + "/5");
        strengthBar.setValue(score);
        strengthBar.setForeground(color);
    }

    private void updateCriteriaLabel(JLabel label, boolean met, String text) {
        if (met) {
            label.setText("✅ " + text);
            label.setForeground(new Color(40, 167, 69));
        } else {
            label.setText("❌ " + text);
            label.setForeground(new Color(100, 100, 100));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordCheckerGUI());
    }
}
