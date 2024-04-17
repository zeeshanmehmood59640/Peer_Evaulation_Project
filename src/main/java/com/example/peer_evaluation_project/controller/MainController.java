package com.example.peer_evaluation_project.controller;




import com.example.peer_evaluation_project.Model.Feedback;
import com.example.peer_evaluation_project.Model.Result;
import com.example.peer_evaluation_project.Model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import com.example.peer_evaluation_project.model.Student;

@Controller
public class MainController {
    private ArrayList<Student> students= new ArrayList<>();
    private String[] criteriaArray;
    private ArrayList<Feedback> feedbacks=new ArrayList<>();
    private ArrayList<Result> results=new ArrayList<>();
    private ArrayList<Teacher> teachers=new ArrayList<>();
    @RequestMapping("/")
    public String homePage(){
        return "index";
    }
    @RequestMapping("/index")
    public String HomePage(){
        return "index";
    }
    @RequestMapping("/teacher")
    public String teacher(){
        return "teacher";
    }
    @RequestMapping("/checkteacher")
    public String checkteacher(HttpServletRequest request){
        String email,password;
        email=request.getParameter("email");
        password=request.getParameter("password");
        boolean teacherExists=false;
        for (Teacher teacher:teachers){
            if (teacher.getEmail().equals(email)&&teacher.getPassword().equals(password)){
                teacherExists=true;
                break;
            }
        }
        if (teacherExists){return "teacher";}else{
            return "studentnotfound";
        }

    }
    @RequestMapping("/signup")
    public String signup(){
        return "signup";
    }
    @RequestMapping("/addteacher")
    public String addteacher(HttpServletRequest request){
        if (teachers.isEmpty()){
            teachers.add(new Teacher(request.getParameter("f-name"),request.getParameter("l-name"),request.getParameter("email"),request.getParameter("pswd")));
            return "index";
        }else{
            boolean teacherExists=false;
            for (Teacher teacher:teachers){
                if (request.getParameter("email").equals(teacher.getEmail())){
                   teacherExists=true;
                }
            }
            if (teacherExists){
                return "teacherexist";
            }else{
                teachers.add(new Teacher(request.getParameter("f-name"),request.getParameter("l-name"),request.getParameter("email"),request.getParameter("pswd")));
                return "index";
            }
        }
    }
    @RequestMapping("/addamember")
    public String addamember(){
        return "addamember";
    }
    @RequestMapping("/viewmembers")
    public String viewmembers(Model model){
        model.addAttribute("students",students);
        return "groupmembers";
    }
    @RequestMapping("/register")
    public String register(HttpServletRequest request,Model model){
        students.add(new Student(request.getParameter("name"),request.getParameter("rnumber"),request.getParameter("section")));
        return "membersuccess";
    }
    @RequestMapping("/evaluationcriteria")
    public String evaluationcriteria(HttpServletRequest request,Model model) {
        int criteriaItems=Integer.parseInt(request.getParameter("item"));
        int[] criteriaArray = new int[criteriaItems];
        model.addAttribute("criteriaArray",criteriaArray);
        return "evaluationcriteria";
    }
    @RequestMapping("/addcriteria")
    public String addcriteria(HttpServletRequest request){
        String[] newCriteria = request.getParameterValues("criteria");

        if (criteriaArray != null && newCriteria != null) {
            String[] combinedArray = new String[criteriaArray.length + newCriteria.length];

            System.arraycopy(criteriaArray, 0, combinedArray, 0, criteriaArray.length);

            // Copy new elements
            System.arraycopy(newCriteria, 0, combinedArray, criteriaArray.length, newCriteria.length);

            // Update criteriaArray reference
            criteriaArray = combinedArray;
            return "criteriasuccess";
        }
        else if (newCriteria != null) {
            // If criteriaArray is null, just assign newCriteria to it
            criteriaArray = newCriteria;
            return "criteriasuccess";
        }
        else{
            System.out.println("criteria added not added");
            return "criteriafail";
        }

    }
    @RequestMapping("/viewcriteria")
    public String viewcriteria(Model model){
        if (criteriaArray != null) {
            model.addAttribute("criteriaArray",criteriaArray);
            return "viewcriteria";
        }
        else {
            return "criteriafail";
        }

    }
//    Student part
    @RequestMapping("/checkstudent")
    public String checkstudent(HttpServletRequest request,Model model) {
        String inRnumber = request.getParameter("r-number");
        System.out.println("entered number"+inRnumber);
        boolean rnumberFound = false;
        for (Student student : students) {
            System.out.println(student.getRnmuber());
            if (student.getRnmuber().equals(inRnumber)) {
                model.addAttribute("student", student);
                model.addAttribute("students", students);
                rnumberFound = true;
                break;
            }
        }
        if (rnumberFound) {
            System.out.println("student found");
            return "studentpage";
        } else {
            System.out.println("student not found");
            return "studentnotfound";
        }
    }
    @RequestMapping("/submitfeedback")
    public String submitfeedback(HttpServletRequest request,Model model){
        String name,section,rnumber,ownNumber;
        ownNumber=request.getParameter("ownNumber");
        name=request.getParameter("studentName");
        section=request.getParameter("studentSection");
        rnumber=request.getParameter("studentRnumber");
        boolean feedbackExists = false;

        for (Feedback feedback : feedbacks) {
            if (feedback.getStudentNumber().equals(rnumber) && feedback.getReviewerNumber().equals(ownNumber)) {
                feedbackExists = true;
                break;
            }
        }
        if (feedbackExists) {
            System.out.println("Feedback already exits");
            Student student=new Student();
            for(Student studentTemp:students){
                if (studentTemp.getRnmuber().equals(ownNumber)){
                    student=studentTemp;
                    System.out.println(student.getName());
                    break;
                }
            }
            model.addAttribute("student", student);
            model.addAttribute("students", students);
            return "feedbackexists";
        } else {
            model.addAttribute("name",name);
            model.addAttribute("ownNumber",ownNumber);
            model.addAttribute("section",section);
            model.addAttribute("rnumber",rnumber);
            model.addAttribute("criteriaArray", criteriaArray);
            return "submitfeedback";
        }

    }
    @RequestMapping("/addfeedback")
    public String addfeedback(HttpServletRequest request,Model model){
        String ownNumber=request.getParameter("ownNumber");
        System.out.println(ownNumber);
        Student student=new Student();
        String studentRnumber = request.getParameter("studentRnumber");
        String studentName = request.getParameter("studentName");
        String[] criteriaName=request.getParameterValues("criterionName");
        String[] feedbackValues= request.getParameterValues("feedback");
        feedbacks.add(new Feedback(studentRnumber,studentName,ownNumber,criteriaName,feedbackValues));
        for(Student studentTemp:students){
            if (studentTemp.getRnmuber().equals(ownNumber)){
                student=studentTemp;
                System.out.println(student.getName());
            }
        }
        model.addAttribute("student", student);
        model.addAttribute("students", students);
        return "studentpage";
    }
    @RequestMapping("/studentpage")
    public String studentpage(HttpServletRequest request,Model model){
        String ownNumber=request.getParameter("studentnumber");
        Student student=new Student();
        for(Student studentTemp:students){
            if (studentTemp.getRnmuber().equals(ownNumber)){
                student=studentTemp;
                break;
            }
        }
        model.addAttribute("student", student);
        model.addAttribute("students", students);
        return "studentpage";

    }
    @RequestMapping("/Viewfeedback")
    public String viewFeedback(Model model) {

        for (Student student: students){
            results.add(new Result(student.getName(),student.getRnmuber(),student.getSection(),criteriaArray.length));
        }
        for (Result result:results){
            double[] sumFeedback;
            int count=0;
            sumFeedback= result.getFeedbacks();
            for (Feedback feedback:feedbacks){
                if (feedback.getStudentNumber().equals(result.getRnumber())){
                    count++;
                    String[] pervfeed;
                    pervfeed=feedback.getFeedbacks();
                    double[] previosResult=new double[feedback.getFeedbacks().length];
                    for (int i=0;i<feedback.getFeedbacks().length;i++){
                        previosResult[i]=Double.parseDouble(pervfeed[i]);
                        sumFeedback[i]+=previosResult[i];
                    }
                }
                result.setCriterionNames(feedback.getCriterionNames());
            }
            for (int i=0; i<sumFeedback.length;i++){
                sumFeedback[i]/=count;
            }
            result.setFeedbacks(sumFeedback);
        }
        for (Result result:results){
            System.out.println(Arrays.toString(result.getFeedbacks()));
        }

        model.addAttribute("results",results);
        model.addAttribute("criteriaArray",criteriaArray);

        return "Viewfeedback";
    }
    @RequestMapping("/checkrecommendation")
    public String checkrecommendation(HttpServletRequest request,Model model){
        String rNumb=request.getParameter("student");
        ArrayList<String> lessResult=new ArrayList<>();
        boolean feedBackExists=false;
        for (Result result:results){
            if (result.getRnumber().equals(rNumb)){
                feedBackExists=true;
                double[] feedback=result.getFeedbacks();
                String[] criterias=result.getCriterionNames();

                for(int i=0;i< feedback.length;i++){
                    if (feedback[i]<5){
                        lessResult.add(criterias[i]);
                    }
                }
                break;
            }
        }
        if (feedBackExists){
            Boolean lengthCheck=lessResult.isEmpty();
            model.addAttribute("lessResult",lessResult);
            model.addAttribute("lengthCheck",lengthCheck);
            return "viewrecommendation";
        }else{
            return "studentnotfound";
        }
    }

}
