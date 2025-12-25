package gym;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaymentDetailsForm extends JFrame {

    private JPanel contentPane;
    private JTextField txtCardOwner;
    private JTextField txtCardBrand;
    private JTextField txtCardNumber;
    private JTextField txtExpirationDate;
    private JTextField txtCvv;
    private MemberDetailsForm parentForm;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PaymentDetailsForm frame = new PaymentDetailsForm(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PaymentDetailsForm(MemberDetailsForm parent) {
        this.parentForm = parent;

        setTitle("Payment Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblCardOwner = new JLabel("Card Owner");
        lblCardOwner.setForeground(Color.WHITE);
        lblCardOwner.setBounds(10, 25, 100, 14);
        contentPane.add(lblCardOwner);

        txtCardOwner = new JTextField();
        txtCardOwner.setBounds(120, 22, 200, 20);
        contentPane.add(txtCardOwner);
        txtCardOwner.setColumns(10);

        JLabel lblCardBrand = new JLabel("Card Brand");
        lblCardBrand.setForeground(Color.WHITE);
        lblCardBrand.setBounds(10, 60, 100, 14);
        contentPane.add(lblCardBrand);

        txtCardBrand = new JTextField();
        txtCardBrand.setBounds(120, 57, 200, 20);
        contentPane.add(txtCardBrand);
        txtCardBrand.setColumns(10);

        JLabel lblCardNumber = new JLabel("Card Number");
        lblCardNumber.setForeground(Color.WHITE);
        lblCardNumber.setBounds(10, 95, 100, 14);
        contentPane.add(lblCardNumber);

        txtCardNumber = new JTextField();
        txtCardNumber.setBounds(120, 92, 200, 20);
        contentPane.add(txtCardNumber);
        txtCardNumber.setColumns(10);

        JLabel lblExpirationDate = new JLabel("Expiration Date");
        lblExpirationDate.setForeground(Color.WHITE);
        lblExpirationDate.setBounds(10, 130, 100, 14);
        contentPane.add(lblExpirationDate);

        txtExpirationDate = new JTextField();
        txtExpirationDate.setBounds(120, 127, 200, 20);
        contentPane.add(txtExpirationDate);
        txtExpirationDate.setColumns(10);

        JLabel lblCvv = new JLabel("CVV");
        lblCvv.setForeground(Color.WHITE);
        lblCvv.setBounds(10, 165, 100, 14);
        contentPane.add(lblCvv);

        txtCvv = new JTextField();
        txtCvv.setBounds(120, 162, 50, 20);
        contentPane.add(txtCvv);
        txtCvv.setColumns(10);

        JButton btnSave = new JButton("SAVE");
        btnSave.setForeground(Color.WHITE);
        btnSave.setBackground(Color.GREEN);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cardOwner = txtCardOwner.getText();
                String cardBrand = txtCardBrand.getText();
                String cardNumber = txtCardNumber.getText();
                String expirationDate = txtExpirationDate.getText();
                String cvv = txtCvv.getText();

                parentForm.setPaymentDetails(cardOwner, cardBrand, cardNumber, expirationDate, cvv);
                dispose(); // Close the window after saving
            }
        });
        btnSave.setBounds(120, 200, 100, 23);
        contentPane.add(btnSave);
    }
}
