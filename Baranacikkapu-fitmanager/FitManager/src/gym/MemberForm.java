package gym;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MemberForm extends JFrame {

    private JPanel contentPane;
    private JTable table;
    MemberDb memberDb = new MemberDb();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MemberForm frame = new MemberForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MemberForm() throws SQLException {
        setTitle("Member List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 560, 300);
        contentPane.add(scrollPane);

        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);
        scrollPane.setViewportView(table);

        JButton btnGetList = new JButton("Get List");
        btnGetList.setForeground(Color.WHITE);
        btnGetList.setBackground(Color.GREEN);
        btnGetList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                try {
                    ResultSet rs = memberDb.getMembers();
                    int colNum = rs.getMetaData().getColumnCount();
                    String[] columnNames = new String[colNum];
                    for (int i = 0; i < colNum; i++) {
                        columnNames[i] = rs.getMetaData().getColumnName(i + 1);
                    }
                    model.setColumnIdentifiers(columnNames);
                    while (rs.next()) {
                        Object[] array = new Object[colNum];
                        for (int i = 0; i < colNum; i++) {
                            array[i] = rs.getObject(i + 1);
                        }
                        model.addRow(array);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnGetList.setBounds(10, 320, 100, 23);
        contentPane.add(btnGetList);

        JButton btnAddMember = new JButton("Add Member");
        btnAddMember.setForeground(Color.WHITE);
        btnAddMember.setBackground(Color.GREEN);
        btnAddMember.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MemberDetailsForm detailsForm = new MemberDetailsForm();
                detailsForm.setVisible(true);
            }
        });
        btnAddMember.setBounds(120, 320, 120, 23);
        contentPane.add(btnAddMember);

        JButton btnDeleteSelected = new JButton("Delete Selected");
        btnDeleteSelected.setForeground(Color.WHITE);
        btnDeleteSelected.setBackground(Color.GREEN);
        btnDeleteSelected.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int memberId = (int) table.getValueAt(selectedRow, 0);
                    try {
                        memberDb.deleteMember(memberId);
                        model.removeRow(selectedRow);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        btnDeleteSelected.setBounds(250, 320, 150, 23);
        contentPane.add(btnDeleteSelected);
    }
}
