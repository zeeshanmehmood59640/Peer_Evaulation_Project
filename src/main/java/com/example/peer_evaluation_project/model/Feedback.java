package com.example.peer_evaluation_project.model;

public class Feedback {
    private String studentNumber,studentName,reviewerNumber;
    private String[] criterionNames,feedbacks;

    public Feedback() {
    }

    public Feedback(String studentNumber, String studentName, String reviewerNumber, String[] criterionNames, String[] feedbacks) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.reviewerNumber = reviewerNumber;
        this.criterionNames = criterionNames;
        this.feedbacks = feedbacks;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getReviewerNumber() {
        return reviewerNumber;
    }

    public void setReviewerNumber(String reviewerNumber) {
        this.reviewerNumber = reviewerNumber;
    }

    public String[] getCriterionNames() {
        return criterionNames;
    }

    public void setCriterionNames(String[] criterionNames) {
        this.criterionNames = criterionNames;
    }

    public String[] getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(String[] feedbacks) {
        this.feedbacks = feedbacks;
    }
}
