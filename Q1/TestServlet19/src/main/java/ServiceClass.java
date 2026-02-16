import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceClass extends DBConnection {

    // INSERT
    public boolean insertDB(String deptName, int numStudents) {
        this.getConnection();
        String sql = "INSERT INTO MYDEPARTMENT(DEPT_NAME, NUM_STUDENTS) VALUES(?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, deptName);
            ps.setInt(2, numStudents);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }

    // VIEW
    public List<String> viewDB() {
        List<String> result = new ArrayList<>();
        this.getConnection();
        String sql = "SELECT DEPT_NAME, NUM_STUDENTS FROM MYDEPARTMENT";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result.add("Department: " + rs.getString("DEPT_NAME") +
                        ", Students: " + rs.getInt("NUM_STUDENTS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return result;
    }

    // UPDATE
    public boolean updateDB(String deptName, int numStudents) {
        this.getConnection();
        String sql = "UPDATE MYDEPARTMENT SET NUM_STUDENTS = ? WHERE DEPT_NAME = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, numStudents);
            ps.setString(2, deptName);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }

    // DELETE
    public boolean deleteDB(String deptName) {
        this.getConnection();
        String sql = "DELETE FROM MYDEPARTMENT WHERE DEPT_NAME = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, deptName);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }
}
