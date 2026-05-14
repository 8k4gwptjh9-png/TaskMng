package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

public class UserDAO {

    private final String JDBC_URL = "jdbc:h2:~/taskMng";
    private final String DB_USER = "sa";
    private final String DB_PASS = "";

    public boolean createUser(User user) {

        try {

            Class.forName("org.h2.Driver");

            Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

            PreparedStatement pStmt = conn.prepareStatement(sql);

            pStmt.setString(1, user.getUsername());
            pStmt.setString(2, user.getPassword());

            int result = pStmt.executeUpdate();

            conn.close();

            return result == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User findByLogin(User user) {

        try {

            Class.forName("org.h2.Driver");

            Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

            PreparedStatement pStmt = conn.prepareStatement(sql);

            pStmt.setString(1, user.getUsername());
            pStmt.setString(2, user.getPassword());

            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");

                conn.close();

                return new User(id, username, password);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}