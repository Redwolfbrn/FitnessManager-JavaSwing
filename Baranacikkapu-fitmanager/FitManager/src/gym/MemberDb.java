package gym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberDb {

    public Connection getConnected() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/gym", "root", "82.boyutB");
    }

    public ResultSet getMembers() throws SQLException {
        Statement st = getConnected().createStatement();
        return st.executeQuery("SELECT * FROM member");
    }

    public boolean memberExists(int memberId) throws SQLException {
        String query = "SELECT COUNT(*) FROM member WHERE memberId = ?";
        PreparedStatement ps = getConnected().prepareStatement(query);
        ps.setInt(1, memberId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1) > 0;
    }

    public void saveMember(Member m) throws SQLException {
        if (memberExists(m.getMemberId())) {
            updateMember(m);
        } else {
            String query = "INSERT INTO member (memberId, name, surname, gender, height, weight, bmi, cardOwner, cardBrand, cardNumber, expirationDate, cvv) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = getConnected().prepareStatement(query);
            ps.setInt(1, m.getMemberId());
            ps.setString(2, m.getName());
            ps.setString(3, m.getSurname());
            ps.setString(4, m.getGender());
            ps.setDouble(5, m.getHeight());
            ps.setDouble(6, m.getWeight());
            ps.setDouble(7, m.getBmi());
            ps.setString(8, m.getCardOwner());
            ps.setString(9, m.getCardBrand());
            ps.setString(10, m.getCardNumber());
            ps.setString(11, m.getExpirationDate());
            ps.setString(12, m.getCvv());
            ps.executeUpdate();
        }
    }

    public void updateMember(Member m) throws SQLException {
        String query = "UPDATE member SET name = ?, surname = ?, gender = ?, height = ?, weight = ?, bmi = ?, cardOwner = ?, cardBrand = ?, cardNumber = ?, expirationDate = ?, cvv = ? WHERE memberId = ?";
        PreparedStatement ps = getConnected().prepareStatement(query);
        ps.setString(1, m.getName());
        ps.setString(2, m.getSurname());
        ps.setString(3, m.getGender());
        ps.setDouble(4, m.getHeight());
        ps.setDouble(5, m.getWeight());
        ps.setDouble(6, m.getBmi());
        ps.setString(7, m.getCardOwner());
        ps.setString(8, m.getCardBrand());
        ps.setString(9, m.getCardNumber());
        ps.setString(10, m.getExpirationDate());
        ps.setString(11, m.getCvv());
        ps.setInt(12, m.getMemberId());
        ps.executeUpdate();
    }

    public void deleteMember(int memberId) throws SQLException {
        String query = "DELETE FROM member WHERE memberId=?";
        PreparedStatement ps = getConnected().prepareStatement(query);
        ps.setInt(1, memberId);
        ps.executeUpdate();
    }
}
