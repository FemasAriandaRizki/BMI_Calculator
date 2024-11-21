import javax.swing.*;
import java.awt.*;

public class BMICalculator extends JFrame {

    private static final Font FONT = new Font("Merriweather Sans", Font.BOLD, 14);
    private static final Color BACKGROUND_COLOR = Color.decode("#FFFFFF");
    private static final Color BUTTON_COLOR = Color.decode("#0487E2");

    private JTextField weightField;
    private JTextField heightField;
    private JLabel resultLabel;

    public BMICalculator() {
        initializeFrame();
        initializeUI();
    }

    private void initializeFrame() {
        setTitle("BMI Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(BACKGROUND_COLOR);
    }

    private void initializeUI() {
        addLabel("Berat (kg):", 50, 50);
        weightField = createTextField(150, 50);

        addLabel("Tinggi (cm):", 50, 100);
        heightField = createTextField(150, 100);

        JButton calculateButton = createButton("Hitung BMI", 150, 150);
        calculateButton.addActionListener(e -> calculateBMI());

        resultLabel = new JLabel("BMI Anda: ");
        resultLabel.setFont(FONT);
        resultLabel.setBounds(50, 200, 300, 25);
        add(resultLabel);
    }

    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(FONT);
        label.setBounds(x, y, 100, 25);
        add(label);
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 150, 30);
        textField.setFont(FONT);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(0, 5, 0, 5)
                ));
        add(textField);
        return textField;
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 150, 30);
        button.setFont(FONT);
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 20, 5, 20)
        ));
        add(button);
        return button;
    }

    private void calculateBMI() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText()) / 100;

            if (weight <= 0 || height <= 0) {
                resultLabel.setText("Masukkan angka positif (> 0)!");
                return;
            }

            double bmi = weight / (height * height);
            String category = getBMICategory(bmi);
            resultLabel.setText(String.format("BMI Anda: %.2f (%s)", bmi, category));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Masukkan angka yang valid!");
        }
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Kurus";
        } else if (bmi < 24.9) {
            return "Normal";
        } else if (bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesitas";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BMICalculator app = new BMICalculator();
            app.setVisible(true);
        });
    }
}
