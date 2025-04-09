/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Entity;

/**
 *
 * @author Admin
 */
import java.util.Date;

public class WorkerJob {
    private int userId;
    private String jobId;
    private String firstName;
    private String lastName;
    private Date startDate;
    private Date endDate;
    private boolean statusJob;
    private JobCatalog jobCatalog;  // Reference to the parent JobCatalog
    
    public WorkerJob() {
    }
    
    public WorkerJob(int userId, String jobId, String firstName, String lastName, 
                    Date startDate, Date endDate, boolean statusJob) {
        this.userId = userId;
        this.jobId = jobId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.statusJob = statusJob;
    }
    
    // Getters and Setters
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getJobId() {
        return jobId;
    }
    
    public void setJobId(String jobId) {
        this.jobId = jobId;
        if (this.jobCatalog != null && !this.jobCatalog.getJobId().equals(jobId)) {
            this.jobCatalog = null;
        }
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public boolean isStatusJob() {
        return statusJob;
    }
    
    public void setStatusJob(boolean statusJob) {
        this.statusJob = statusJob;
    }
    
    public JobCatalog getJobCatalog() {
        return jobCatalog;
    }
    
    public void setJobCatalog(JobCatalog jobCatalog) {
        this.jobCatalog = jobCatalog;
        if (jobCatalog != null) {
            this.jobId = jobCatalog.getJobId();
        }
    }
    
    // Get full name helper method
    public String getFullName() {
        return firstName + " " + lastName;
    }

}
