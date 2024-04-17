package com.example.peer_evaluation_project.Model;

public class Result {
    String name,rnumber,section;
    private String[] criterionNames;
    private double[] feedbacks;

    public Result() {

    }

    public Result(String name, String rnumber, String section,int feedlen) {
        this.name = name;
        this.rnumber = rnumber;
        this.section = section;
        this.feedbacks=new double[feedlen];
        for (int i=0;i<feedlen;i++){
            this.feedbacks[i]=0;
        }

    }

    public double[] getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(double[] feedbacks) {
        this.feedbacks = feedbacks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRnumber() {
        return rnumber;
    }

    public void setRnumber(String rnumber) {
        this.rnumber = rnumber;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String[] getCriterionNames() {
        return criterionNames;
    }

    public void setCriterionNames(String[] criterionNames) {
        this.criterionNames = criterionNames;
    }


}
