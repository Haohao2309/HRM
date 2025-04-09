/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

/**
 *
 * @author Admin
 */
import databaseconnection.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Entity.WorkerJob;

public class WorkerJobDAO {

    private final DatabaseConnection conn;

    public WorkerJobDAO(DatabaseConnection conn) {
        this.conn = conn;
    }

    public void addWorkerJob(WorkerJob workerJob) {
        String sql = "INSERT INTO Worker_Job (user_id, Job_ID, first_name, last_name, Start_date, End_date, Status_job) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, workerJob.getUserId());
            stmt.setString(2, workerJob.getJobId());
            stmt.setString(3, workerJob.getFirstName());
            stmt.setString(4, workerJob.getLastName());
            stmt.setDate(5, new java.sql.Date(workerJob.getStartDate().getTime()));
            stmt.setDate(6, new java.sql.Date(workerJob.getEndDate().getTime()));
            stmt.setBoolean(7, workerJob.isStatusJob());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm WorkerJob vào cơ sở dữ liệu", e);
        }
    }

    public void updateWorkerJob(WorkerJob workerJob) {
        String sql = "UPDATE Worker_Job SET first_name = ?, last_name = ?, Start_date = ?, End_date = ?, Status_job = ? "
                + "WHERE user_id = ? AND Job_ID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, workerJob.getFirstName());
            stmt.setString(2, workerJob.getLastName());
            stmt.setDate(3, new java.sql.Date(workerJob.getStartDate().getTime()));
            stmt.setDate(4, new java.sql.Date(workerJob.getEndDate().getTime()));
            stmt.setBoolean(5, workerJob.isStatusJob());
            stmt.setInt(6, workerJob.getUserId());
            stmt.setString(7, workerJob.getJobId());

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated == 0) {
                throw new RuntimeException("Không tìm thấy WorkerJob để cập nhật (user_id = "
                        + workerJob.getUserId() + ", job_id = " + workerJob.getJobId() + ")");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật WorkerJob", e);
        }
    }

    public void deleteWorkerJob(int userId, String jobId) {
        String sql = "DELETE FROM Worker_Job WHERE user_id = ? AND Job_ID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, jobId);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new RuntimeException("Không tìm thấy WorkerJob để xóa (userId=" + userId + ", jobId=" + jobId + ")");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa WorkerJob", e);
        }
    }

    public WorkerJob findById(int userId, String jobId) {
        String sql = "SELECT * FROM Worker_Job WHERE user_id = ? AND Job_ID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, jobId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToWorkerJob(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm WorkerJob theo ID", e);
        }

        return null;
    }

    public List<WorkerJob> getAllWorkerJob() {
        String sql = "SELECT * FROM Worker_Job";
        List<WorkerJob> workerJobs = new ArrayList<>();

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                workerJobs.add(mapResultSetToWorkerJob(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách tất cả WorkerJob", e);
        }

        return workerJobs;
    }

    public List<WorkerJob> findByUserId(int userId) {
        String sql = "SELECT * FROM Worker_Job WHERE user_id = ?";
        List<WorkerJob> workerJobs = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    workerJobs.add(mapResultSetToWorkerJob(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm WorkerJob theo userId", e);
        }

        return workerJobs;
    }

    public List<WorkerJob> findByJobId(String jobId) throws Exception {
        String sql = "SELECT * FROM Worker_Job WHERE Job_ID = ?";
        List<WorkerJob> workerJobs = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jobId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    workerJobs.add(mapResultSetToWorkerJob(rs));
                }
            }
        }

        return workerJobs;
    }

    public List<WorkerJob> findByStatus(boolean status) throws Exception {
        String sql = "SELECT * FROM Worker_Job WHERE Status_job = ?";
        List<WorkerJob> workerJobs = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, status);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    workerJobs.add(mapResultSetToWorkerJob(rs));
                }
            }
        }

        return workerJobs;
    }

    private WorkerJob mapResultSetToWorkerJob(ResultSet rs) throws SQLException {
        WorkerJob workerJob = new WorkerJob();
        workerJob.setUserId(rs.getInt("user_id"));
        workerJob.setJobId(rs.getString("Job_ID"));
        workerJob.setFirstName(rs.getString("first_name"));
        workerJob.setLastName(rs.getString("last_name"));
        workerJob.setStartDate(rs.getDate("Start_date"));
        workerJob.setEndDate(rs.getDate("End_date"));
        workerJob.setStatusJob(rs.getBoolean("Status_job"));

        return workerJob;
    }
}
