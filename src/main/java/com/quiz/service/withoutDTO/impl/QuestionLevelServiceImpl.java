package com.quiz.service.withoutDTO.impl;


import com.quiz.dto.QuestionLevelDTO;
import com.quiz.entity.Question;
import com.quiz.entity.QuestionLevel;
import com.quiz.repository.OverallRepository;
import com.quiz.repository.QuestionLevelRepository;
import com.quiz.repository.QuestionRepository;
import com.quiz.service.withoutDTO.QuestionLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionLevelServiceImpl  implements QuestionLevelService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionLevelServiceImpl.class);
    @Autowired
    QuestionLevelRepository questionLevelRepository;
    @Autowired
    OverallRepository overallRepository;
    @Autowired
    QuestionRepository questionRepository;


    @Override
    public Page<QuestionLevel> getAll(Pageable pageable) throws Exception {
        return questionLevelRepository.findAll(pageable);
    }

    @Override
    public Optional<QuestionLevel> create(QuestionLevelDTO data) throws Exception {

        QuestionLevel questionLevel = new QuestionLevel();
        questionLevel.setLevel(data.getLevel());
        questionLevel.setTimer(data.getTimer());
        questionLevel.setInfo(data.getInfo());

        return Optional.of(questionLevelRepository.save(questionLevel));
    }

    @Override
    public Optional<QuestionLevel> update(Long id, QuestionLevelDTO data) throws Exception {
        Optional<QuestionLevel> exsitingQuestionLevel = questionLevelRepository.findById(id);

        if (!exsitingQuestionLevel.isPresent()) {
            logger.info("Level with id " + id + " does not exist");
            return null;
        }
        QuestionLevel questionLevel = exsitingQuestionLevel.get();

        questionLevel.setLevel(questionLevel.getLevel());
        questionLevel.setTimer(questionLevel.getTimer());
        questionLevel.setInfo(questionLevel.getInfo());

        return Optional.of(questionLevelRepository.save(questionLevel));
    }

    @Override
    public Optional<QuestionLevel> getById(Long id) throws Exception {
        if (!questionLevelRepository.existsById(id)) {
            logger.error("Level with id " + id + " does not exists");
            return null;
        }
        return questionLevelRepository.findById(id);
    }



    @Override
    public void deleteById(Long id) {
        if (!questionLevelRepository.existsById(id)) {
            logger.error("Question level with id " + id + " does not exists");
        }
        if(!overallRepository.existsById(id)){
            logger.error("Overall with id " + id + " does not exists");
        }

        if(!questionRepository.existsById(id)){
            logger.error("Question with id " + id + " does not exists");
        }
        questionLevelRepository.deleteById(id);
        overallRepository.deleteById(id);
        questionLevelRepository.deleteById(id);


    }



}
