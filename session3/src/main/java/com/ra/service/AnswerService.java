package com.ra.service;

import com.ra.model.dto.AnswerDTO;
import com.ra.model.entity.Answer;
import com.ra.model.entity.Question;
import com.ra.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionService questionService;

    public List<Answer> getAnswers(long id) {
        return answerRepository.findAllByQuestionId(id);
    }

    public Answer save(AnswerDTO answerDTO , long questionId) {
        Question question = questionService.getQuestionById(questionId);
        Answer answer = new Answer();
        answer.setContent(answerDTO.getContent());
        answer.setCorrect(answerDTO.isCorrect());
        answer.setQuestion(question);
        return answerRepository.save(answer);
    }
}
