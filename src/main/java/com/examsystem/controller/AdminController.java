package com.examsystem.controller;

import com.examsystem.entity.Exam;
import com.examsystem.entity.Question;
import com.examsystem.entity.Student;
import com.examsystem.model.ExamType;
import com.examsystem.service.ExamServiceImpl;
import com.examsystem.service.IExamService;
import com.examsystem.service.IStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Admin Controller.
 * Handles admin dashboard, analytics, and exam management.
 * Demonstrates Streams API for data analytics.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    private final IExamService examService;
    private final IStudentService studentService;
    private final ExamServiceImpl examServiceImpl; // For advanced features
    
    public AdminController(IExamService examService, 
                          IStudentService studentService,
                          ExamServiceImpl examServiceImpl) {
        this.examService = examService;
        this.studentService = studentService;
        this.examServiceImpl = examServiceImpl;
    }
    
    /**
     * Admin dashboard with analytics.
     * Demonstrates Streams API usage for data processing.
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Exam> exams = examService.getAllExams();
        List<Student> students = studentService.getAllStudents();
        
        model.addAttribute("exams", exams);
        model.addAttribute("students", students);
        model.addAttribute("totalExams", exams.size());
        model.addAttribute("totalStudents", students.size());
        
        return "admin/dashboard";
    }
    
    /**
     * Analytics page with charts and statistics.
     * Uses Streams API for aggregations.
     */
    @GetMapping("/analytics")
    public String analytics(Model model) {
        List<Exam> exams = examService.getAllExams();
        model.addAttribute("exams", exams);
        return "admin/analytics";
    }
    
    /**
     * REST API for exam analytics.
     * Demonstrates Streams: filter, average, groupingBy.
     */
    @GetMapping("/api/analytics/{examId}")
    @ResponseBody
    public Map<String, Object> getExamAnalytics(@PathVariable Long examId) {
        List<Student> students = examService.getStudentsForExam(examId);
        
        // Streams API - filter for passed students
        List<Student> passedStudents = examService.getPassedStudents(examId, 50);
        
        // Streams API - calculate average
        double averageScore = examService.getAverageScore(examId);
        
        // Additional statistics
        Map<String, Object> stats = examServiceImpl.getExamStatistics(examId);
        
        Map<String, Object> analytics = new HashMap<>();
        analytics.put("totalStudents", students.size());
        analytics.put("passedStudents", passedStudents.size());
        analytics.put("failedStudents", students.size() - passedStudents.size());
        analytics.put("averageScore", averageScore);
        analytics.put("passPercentage", stats.get("passPercentage"));
        
        return analytics;
    }
    
    /**
     * REST API to get grouped students by exam type.
     * Demonstrates Streams groupingBy collector.
     */
    @GetMapping("/api/students/grouped")
    @ResponseBody
    public Map<String, List<Student>> getGroupedStudents() {
        List<Student> allStudents = studentService.getAllStudents();
        return examService.groupStudentsByExamType(allStudents);
    }
    
    /**
     * Create new exam page.
     */
    @GetMapping("/exam/create")
    public String createExamPage(Model model) {
        model.addAttribute("examTypes", ExamType.values());
        return "admin/create-exam";
    }
    
    /**
     * Create new exam - demonstrates Factory pattern.
     */
    @PostMapping("/exam/create")
    public String createExam(@RequestParam String type,
                            @RequestParam String title,
                            @RequestParam int sections,
                            @RequestParam int questionsPerSection,
                            Model model) {
        try {
            ExamType examType = ExamType.valueOf(type.toUpperCase());
            Exam exam = examService.createExam(examType, title, sections, questionsPerSection);
            examService.saveExam(exam);
            
            model.addAttribute("success", "Exam created successfully!");
            return "redirect:/admin/exam/" + exam.getId() + "/questions";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("examTypes", ExamType.values());
            return "admin/create-exam";
        }
    }
    
    /**
     * Add questions to exam page.
     */
    @GetMapping("/exam/{examId}/questions")
    public String addQuestionsPage(@PathVariable Long examId, Model model) {
        Exam exam = examService.getExamById(examId);
        model.addAttribute("exam", exam);
        return "admin/add-questions";
    }
    
    /**
     * Add question to exam.
     */
    @PostMapping("/exam/{examId}/question")
    public String addQuestion(@PathVariable Long examId,
                             @RequestParam int section,
                             @RequestParam String text,
                             @RequestParam(required = false) String[] options,
                             @RequestParam String correctAnswer,
                             Model model) {
        Question question = new Question();
        question.setText(text);
        question.setCorrectAnswer(correctAnswer);
        
        if (options != null && options.length > 0) {
            question.setOptionsJson(arrayToJson(options));
        }
        
        examService.addQuestionToExam(examId, section, question);
        
        return "redirect:/admin/exam/" + examId + "/questions";
    }
    
    /**
     * Show exam metadata using Reflection.
     */
    @GetMapping("/exam/{examId}/metadata")
    @ResponseBody
    public String showExamMetadata(@PathVariable Long examId) {
        Exam exam = examService.getExamById(examId);
        exam.printMetadata(); // Uses reflection - prints to console
        return "Metadata printed to console. Check server logs.";
    }
    
    private String arrayToJson(String[] arr) {
        if (arr == null || arr.length == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append("\"").append(arr[i].replace("\"", "\\\"")).append("\"");
            if (i < arr.length - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
