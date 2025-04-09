/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

/**
 *
 * @author Admin
 */
import model.Entity.JobCatalog;
import model.Entity.WorkerJob;
import databaseconnection.*;

import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JobCatalogDAO {
    private DatabaseConnection conn;
    private WorkerJobDAO workerJobDAO;
    
    public JobCatalogDAO(DatabaseConnection conn) {
        this.conn = conn;
        this.workerJobDAO = new WorkerJobDAO(conn);
    }
    
    public void addJob(JobCatalog jobCatalog) throws Exception {
        String sql = "INSERT INTO Job_Catalog (Job_ID, Job_name, Job_category, Wage, Worker_count, Status_job, Location, Start_time, End_time) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jobCatalog.getJobId());
            stmt.setString(2, jobCatalog.getJobName());
            stmt.setString(3, jobCatalog.getJobCategory());
            stmt.setBigDecimal(4, jobCatalog.getWage());
            stmt.setInt(5, jobCatalog.getWorkerCount());
            stmt.setBoolean(6, jobCatalog.isStatusJob());
            stmt.setString(7, jobCatalog.getLocation());
            stmt.setDate(8, new java.sql.Date(jobCatalog.getStartTime().getTime()));
            stmt.setDate(9, new java.sql.Date(jobCatalog.getEndTime().getTime()));
            stmt.executeUpdate();
        }
    }
    
    public void updateJob(JobCatalog jobCatalog) throws Exception {
        String sql = "UPDATE Job_Catalog SET Job_name = ?, Job_category = ?, Wage = ?, " +
                    "Worker_count = ?, Status_job = ?, Location = ?, Start_time = ?, End_time = ? " +
                    "WHERE Job_ID = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jobCatalog.getJobName());
            stmt.setString(2, jobCatalog.getJobCategory());
            stmt.setBigDecimal(3, jobCatalog.getWage());
            stmt.setInt(4, jobCatalog.getWorkerCount());
            stmt.setBoolean(5, jobCatalog.isStatusJob());
            stmt.setString(6, jobCatalog.getLocation());
            stmt.setDate(7, new java.sql.Date(jobCatalog.getStartTime().getTime()));
            stmt.setDate(8, new java.sql.Date(jobCatalog.getEndTime().getTime()));
            stmt.setString(9, jobCatalog.getJobId());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteJob(String jobId) throws Exception {
        String sql = "DELETE FROM Job_Catalog WHERE Job_ID = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jobId);
            stmt.executeUpdate();
        }
    }
    
    public JobCatalog findJobById(String jobId) throws Exception {
        String sql = "SELECT * FROM Job_Catalog WHERE Job_ID = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jobId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    JobCatalog jobCatalog = mapResultSetToJobCatalog(rs);
                    
                    // Load related worker jobs
                    List<WorkerJob> workerJobs = workerJobDAO.findByJobId(jobId);
                    for (WorkerJob workerJob : workerJobs) {
                        workerJob.setJobCatalog(jobCatalog);
                        jobCatalog.addWorkerJob(workerJob);
                    }
                    
                    return jobCatalog;
                }
            }
        }
        
        return null;
    }
    
    public List<JobCatalog> getAllJob() throws Exception {
        String sql = "SELECT * FROM Job_Catalog";
        List<JobCatalog> jobCatalogs = new ArrayList<>();
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                JobCatalog jobCatalog = mapResultSetToJobCatalog(rs);
                
                // Load related worker jobs
                List<WorkerJob> workerJobs = workerJobDAO.findByJobId(jobCatalog.getJobId());
                for (WorkerJob workerJob : workerJobs) {
                    workerJob.setJobCatalog(jobCatalog);
                    jobCatalog.addWorkerJob(workerJob);
                }
                
                jobCatalogs.add(jobCatalog);
            }
        }
        
        return jobCatalogs;
    }
    
    public List<JobCatalog> findJobByCategory(String category) throws Exception {
        String sql = "SELECT * FROM Job_Catalog WHERE Job_category = ?";
        List<JobCatalog> jobCatalogs = new ArrayList<>();
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    JobCatalog jobCatalog = mapResultSetToJobCatalog(rs);
                    
                    // Load related worker jobs
                    List<WorkerJob> workerJobs = workerJobDAO.findByJobId(jobCatalog.getJobId());
                    for (WorkerJob workerJob : workerJobs) {
                        workerJob.setJobCatalog(jobCatalog);
                        jobCatalog.addWorkerJob(workerJob);
                    }
                    
                    jobCatalogs.add(jobCatalog);
                }
            }
        }
        
        return jobCatalogs;
    }
    
    public List<JobCatalog> findJobByStatus(boolean status) throws Exception {
        String sql = "SELECT * FROM Job_Catalog WHERE Status_job = ?";
        List<JobCatalog> jobCatalogs = new ArrayList<>();
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, status);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    JobCatalog jobCatalog = mapResultSetToJobCatalog(rs);
                    
                    // Load related worker jobs
                    List<WorkerJob> workerJobs = workerJobDAO.findByJobId(jobCatalog.getJobId());
                    for (WorkerJob workerJob : workerJobs) {
                        workerJob.setJobCatalog(jobCatalog);
                        jobCatalog.addWorkerJob(workerJob);
                    }
                    
                    jobCatalogs.add(jobCatalog);
                }
            }
        }
        
        return jobCatalogs;
    }
    
    public List<JobCatalog> findJobByLocation(String location) throws Exception {
        String sql = "SELECT * FROM Job_Catalog WHERE Location = ?";
        List<JobCatalog> jobCatalogs = new ArrayList<>();
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, location);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    JobCatalog jobCatalog = mapResultSetToJobCatalog(rs);
                    
                    // Load related worker jobs
                    List<WorkerJob> workerJobs = workerJobDAO.findByJobId(jobCatalog.getJobId());
                    for (WorkerJob workerJob : workerJobs) {
                        workerJob.setJobCatalog(jobCatalog);
                        jobCatalog.addWorkerJob(workerJob);
                    }
                    
                    jobCatalogs.add(jobCatalog);
                }
            }
        }
        
        return jobCatalogs;
    }
    
    public int countWorkersByJobId(String jobId) throws Exception {
        String sql = "SELECT COUNT(*) FROM Worker_Job WHERE Job_ID = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jobId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        
        return 0;
    }
    
    private JobCatalog mapResultSetToJobCatalog(ResultSet rs) throws SQLException {
        JobCatalog jobCatalog = new JobCatalog();
        jobCatalog.setJobId(rs.getString("Job_ID"));
        jobCatalog.setJobName(rs.getString("Job_name"));
        jobCatalog.setJobCategory(rs.getString("Job_category"));
        jobCatalog.setWage(rs.getBigDecimal("Wage"));
        jobCatalog.setWorkerCount(rs.getInt("Worker_count"));
        jobCatalog.setStatusJob(rs.getBoolean("Status_job"));
        jobCatalog.setLocation(rs.getString("Location"));
        jobCatalog.setStartTime(rs.getDate("Start_time"));
        jobCatalog.setEndTime(rs.getDate("End_time"));
        
        return jobCatalog;
    }
}

