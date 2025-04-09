/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Entity;

/**
 *
 * @author Admin
 */
import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class JobCatalog {
    private String jobId;
    private String jobName;
    private String jobCategory;
    private BigDecimal wage;
    private int workerCount;
    private boolean statusJob;
    private String location;
    private Date startTime;
    private Date endTime;
    private List<WorkerJob> workerJobs;
    
    public JobCatalog() {
        this.workerJobs = new ArrayList<>();
    }
    
    public JobCatalog(String jobId, String jobName, String jobCategory, BigDecimal wage, int workerCount,
                      boolean statusJob, String location, Date startTime, Date endTime) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobCategory = jobCategory;
        this.wage = wage;
        this.workerCount = workerCount;
        this.statusJob = statusJob;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workerJobs = new ArrayList<>();
    }
    
    // Getters and Setters
    public String getJobId() {
        return jobId;
    }
    
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    
    public String getJobName() {
        return jobName;
    }
    
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    
    public String getJobCategory() {
        return jobCategory;
    }
    
    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }
    
    public BigDecimal getWage() {
        return wage;
    }
    
    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }
    
    public int getWorkerCount() {
        return workerCount;
    }
    
    public void setWorkerCount(int workerCount) {
        this.workerCount = workerCount;
    }
    
    public boolean isStatusJob() {
        return statusJob;
    }
    
    public void setStatusJob(boolean statusJob) {
        this.statusJob = statusJob;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    public List<WorkerJob> getWorkerJobs() {
        return workerJobs;
    }
    
    public void setWorkerJobs(List<WorkerJob> workerJobs) {
        this.workerJobs = workerJobs;
    }
    
    // Method to add a worker job
    public void addWorkerJob(WorkerJob workerJob) {
        this.workerJobs.add(workerJob);
    }
    
    // Method to remove a worker job
    public void removeWorkerJob(WorkerJob workerJob) {
        this.workerJobs.remove(workerJob);
    }
}
