package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Task implements Serializable {

    private int id;
    private int userId;
    private String title;
    private String detail;
    private Date deadline;
    private Timestamp createdAt;
    private int priority;
    

    public Task() {}

    public Task(int id, int userId, String title, String detail, Date deadline, Timestamp createdAt, int priority) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.detail = detail;
        this.deadline = deadline;
        this.createdAt = createdAt;
        this.priority = priority;
    }

    public Task(int userId, String title, String detail, Date deadline, int priority) {
        this.userId = userId;
        this.title = title;
        this.detail = detail;
        this.deadline = deadline;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }


    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}