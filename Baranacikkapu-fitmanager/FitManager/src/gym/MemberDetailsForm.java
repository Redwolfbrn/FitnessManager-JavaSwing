package gym;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MemberDetailsForm extends JFrame {

    private JPanel contentPane;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtHeight;
    private JTextField txtWeight;
    private ButtonGroup genderGroup;
    private MemberDb memberDb = new MemberDb();
    private Member member;
    
    private String cardOwner;
    private String cardBrand;
    private String cardNumber;
    private String expirationDate;
    private String cvv;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MemberDetailsForm frame = new MemberDetailsForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MemberDetailsForm() {
        setTitle("Member Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblId = new JLabel("ID");
        lblId.setForeground(Color.WHITE);
        lblId.setBounds(10, 25, 46, 14);
        contentPane.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(78, 22, 32, 20);
        contentPane.add(txtId);
        txtId.setColumns(10);

        JLabel lblName = new JLabel("Name");
        lblName.setForeground(Color.WHITE);
        lblName.setBounds(10, 53, 46, 14);
        contentPane.add(lblName);

        txtName = new JTextField();
        txtName.setColumns(10);
        txtName.setBounds(78, 50, 86, 20);
        contentPane.add(txtName);

        JLabel lblSurname = new JLabel("Surname");
        lblSurname.setForeground(Color.WHITE);
        lblSurname.setBounds(10, 82, 71, 14);
        contentPane.add(lblSurname);

        txtSurname = new JTextField();
        txtSurname.setColumns(10);
        txtSurname.setBounds(78, 79, 86, 20);
        contentPane.add(txtSurname);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setForeground(Color.WHITE);
        lblGender.setBounds(10, 116, 71, 14);
        contentPane.add(lblGender);

        JRadioButton rdbtnMale = new JRadioButton("Male");
        rdbtnMale.setBounds(78, 112, 109, 23);
        contentPane.add(rdbtnMale);
        rdbtnMale.setActionCommand("Male");

        JRadioButton rdbtnFemale = new JRadioButton("Female");
        rdbtnFemale.setBounds(78, 137, 109, 23);
        contentPane.add(rdbtnFemale);
        rdbtnFemale.setActionCommand("Female");

        genderGroup = new ButtonGroup();
        genderGroup.add(rdbtnMale);
        genderGroup.add(rdbtnFemale);

        JLabel lblHeight = new JLabel("Height (cm)");
        lblHeight.setForeground(Color.WHITE);
        lblHeight.setBounds(10, 171, 71, 14);
        contentPane.add(lblHeight);

        txtHeight = new JTextField();
        txtHeight.setBounds(78, 168, 86, 20);
        contentPane.add(txtHeight);
        txtHeight.setColumns(10);

        JLabel lblWeight = new JLabel("Weight (kg)");
        lblWeight.setForeground(Color.WHITE);
        lblWeight.setBounds(10, 202, 71, 14);
        contentPane.add(lblWeight);

        txtWeight = new JTextField();
        txtWeight.setBounds(78, 199, 86, 20);
        contentPane.add(txtWeight);
        txtWeight.setColumns(10);

        JButton btnPayment = new JButton("Payment Method");
        btnPayment.setForeground(Color.WHITE);
        btnPayment.setBackground(Color.GREEN);
        btnPayment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PaymentDetailsForm paymentForm = new PaymentDetailsForm(MemberDetailsForm.this);
                paymentForm.setVisible(true);
            }
        });
        btnPayment.setBounds(78, 230, 150, 23);
        contentPane.add(btnPayment);

        JButton btnSave = new JButton("SAVE");
        btnSave.setForeground(Color.WHITE);
        btnSave.setBackground(Color.GREEN);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    saveMemberDetails();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnSave.setBounds(240, 230, 89, 23);
        contentPane.add(btnSave);
    }

    private void saveMemberDetails() throws SQLException {
        try {
            member = new Member();
            member.setMemberId(Integer.parseInt(txtId.getText()));
            member.setName(txtName.getText());
            member.setSurname(txtSurname.getText());
            member.setGender(genderGroup.getSelection().getActionCommand());
            member.setHeight(Double.parseDouble(txtHeight.getText()));
            member.setWeight(Double.parseDouble(txtWeight.getText()));

            double heightInMeters = member.getHeight() / 100;
            member.setBmi(member.getWeight() / (heightInMeters * heightInMeters));

            member.setCardOwner(cardOwner);
            member.setCardBrand(cardBrand);
            member.setCardNumber(cardNumber);
            member.setExpirationDate(expirationDate);
            member.setCvv(cvv);

            memberDb.saveMember(member);
            dispose();
        } catch (NumberFormatException nfe) {
            System.err.println("Number format exception: " + nfe.getMessage());
        } catch (SQLException sqle) {
            System.err.println("SQL exception: " + sqle.getMessage());
            throw sqle;
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setPaymentDetails(String cardOwner, String cardBrand, String cardNumber, String expirationDate, String cvv) {
        this.cardOwner = cardOwner;
        this.cardBrand = cardBrand;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }
}
