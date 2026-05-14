package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Task;

public class TaskDAO {

    private final String JDBC_URL = "jdbc:h2:~/taskMng";
    private final String DB_USER = "sa";
    private final String DB_PASS = "";

    // 一覧取得
    public List<Task> findAll(int userId) {

        List<Task> taskList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            String sql = "SELECT * FROM tasks WHERE user_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, userId);

            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {

                Task task = new Task();

                task.setId(rs.getInt("id"));
                task.setUserId(rs.getInt("user_id"));
                task.setTitle(rs.getString("title"));

                // DBに合わせる
                task.setDetail(rs.getString("description"));
                task.setDeadline(rs.getDate("deadline"));
                task.setPriority(rs.getInt("priority"));

                taskList.add(task);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return taskList;
    }

    // 登録
    public boolean insert(Task task) {

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            String sql = "INSERT INTO tasks (user_id, title, description, deadline, priority) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            pStmt.setInt(1, task.getUserId());
            pStmt.setString(2, task.getTitle());
            pStmt.setString(3, task.getDetail());
            pStmt.setDate(4, task.getDeadline());
            pStmt.setInt(5, task.getPriority());

            int result = pStmt.executeUpdate();
            return result == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 更新
    public boolean update(Task task) {

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            String sql = "UPDATE tasks SET title=?, description=?, deadline=?, priority=? WHERE id=?";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            pStmt.setString(1, task.getTitle());
            pStmt.setString(2, task.getDetail());
            pStmt.setDate(3, task.getDeadline());
            pStmt.setInt(4, task.getPriority());
            pStmt.setInt(5, task.getId());

            int result = pStmt.executeUpdate();
            return result == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 削除
    public boolean delete(int id) {

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            String sql = "DELETE FROM tasks WHERE id=?";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            pStmt.setInt(1, id);

            int result = pStmt.executeUpdate();
            return result == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 1件取得
    public Task findById(int id) {

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            String sql = "SELECT * FROM tasks WHERE id=?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, id);

            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {

                Task task = new Task();

                task.setId(rs.getInt("id"));
                task.setUserId(rs.getInt("user_id"));
                task.setTitle(rs.getString("title"));

                // DBに合わせる
                task.setDetail(rs.getString("description"));
                task.setDeadline(rs.getDate("deadline"));
                task.setPriority(rs.getInt("priority"));

                return task;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}