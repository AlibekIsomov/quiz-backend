package com.quiz.service.withoutDTO.impl;

import com.quiz.dto.QuestionDTO;
import com.quiz.entity.FileEntity;
import com.quiz.entity.Question;
import com.quiz.entity.QuestionLevel;
import com.quiz.repository.FileRepository;
import com.quiz.repository.QuestionLevelRepository;
import com.quiz.repository.QuestionRepository;
import com.quiz.service.withoutDTO.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class QuestionServiceImpl implements QuestionService{
    private static final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    FileRepository fileRepository;
    @Autowired
    QuestionLevelRepository questionLevelRepository;

    @Override
    public Page<Question> getAll(Pageable pageable) throws Exception {
        return questionRepository.findAll(pageable);
    }

    @Override
    public Optional<Question> create(QuestionDTO data) throws Exception {
        Optional<QuestionLevel> optionalQuestionLevel = questionLevelRepository.findById(data.getQuestionLevelId());
        Optional<FileEntity> optionalFileEntity = fileRepository.findById(data.getFileEntityId());
        if (!optionalFileEntity.isPresent()) {
            logger.info("Such ID File does not exist!");
        }

        Question question = new Question();
        question.setTitle(data.getTitle());
        question.setAnswer(data.getAnswer());
        question.setQuestionLevel(optionalQuestionLevel.get());
        question.setFileEntity(optionalFileEntity.get());

        return Optional.of(questionRepository.save(question));
    }

    @Override
    public Optional<Question> update(Long id, QuestionDTO data) throws Exception {
        Optional<QuestionLevel> optionalQuestionLevel = questionLevelRepository.findById(data.getQuestionLevelId());
        Optional<Question> exsitingQuestion = questionRepository.findById(id);
        Optional<FileEntity> optionalFileEntity = fileRepository.findById(data.getFileEntityId());
        if (!exsitingQuestion.isPresent()) {
            logger.info("Inventory with id " + id + " does not exist");
            return null;
        }
        Question question = exsitingQuestion.get();

        question.setTitle(question.getTitle());
        question.setAnswer(question.getAnswer());
        question.setQuestionLevel(optionalQuestionLevel.get());
        question.setFileEntity(optionalFileEntity.get());

        return Optional.of(questionRepository.save(question));
    }

    @Override
    public Optional<Question> getById(Long id) throws Exception {
        if (!questionRepository.existsById(id)) {
            logger.error("Question with id " + id + " does not exists");
            return null;
        }
        return questionRepository.findById(id);
    }


    @Override
    public void deleteById(Long id) {
        if (!questionRepository.existsById(id)) {
            logger.error("Question with id " + id + " does not exists");
        }
        questionRepository.deleteById(id);

    }

}

