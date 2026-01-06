package com.ra.controller;

import com.ra.model.dto.QuestionDTO;
import com.ra.model.entity.Exam;
import com.ra.model.entity.Question;
import com.ra.service.ExamService;
import com.ra.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ExamService examService;

    @GetMapping("/exam/{id}")
    public String exam(@PathVariable long id, Model model) {
        Exam exam = examService.findById(id);
        List<Question> questions = questionService.getQuestionByExamId(id);
        model.addAttribute("questions", questions);
        model.addAttribute("examId", id);
        model.addAttribute("exam", exam);
        return "questionList";
    }

    @GetMapping("/exam/{id}/add")
    public String addQuestion(@PathVariable long id, Model model) {
        Exam exam = examService.findById(id);
        model.addAttribute("exam", exam);
        model.addAttribute("question", new QuestionDTO());
        return "addQuestion";
    }

    @PostMapping("/exam/{id}/add")
    public String addQuestion(@PathVariable long id, @Valid @ModelAttribute("question") QuestionDTO questionDTO,
                              BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", questionDTO);
            return "addQuestion";
        }
        Question question = questionService.saveQuestion(questionDTO,id);
        if (question != null) {
            redirectAttributes.addFlashAttribute("message", "Question added successfully");
        }else {
            redirectAttributes.addFlashAttribute("message", "Error adding question");
        }
        return "redirect:/questions/exam/" + id;

    }
}
