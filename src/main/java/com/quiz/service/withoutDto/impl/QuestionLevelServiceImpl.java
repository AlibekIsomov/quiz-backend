package com.quiz.service.withoutDto.impl;


import com.quiz.entity.Overall;
import com.quiz.entity.QuestionLevel;
import com.quiz.repository.DistributedRepository;
import com.quiz.repository.OverallRepository;
import com.quiz.repository.QuestionLevelRepository;
import com.quiz.repository.QuestionRepository;
import com.quiz.service.withoutDto.QuestionLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionLevelServiceImpl extends AbstractService<QuestionLevel> implements QuestionLevelService {
    @Autowired
    QuestionLevelRepository questionLevelRepository;
    @Autowired
    OverallRepository overallRepository;

    @Autowired
    QuestionRepository questionRepository;


    public QuestionLevelServiceImpl(DistributedRepository<QuestionLevel> repository) {
        super(repository);
    }



    @Override
    public void someChangesForCreate(QuestionLevel entity) {

    }

    @Override
    public void someChangesForUpdate(QuestionLevel entity) {

    }
    @Override
    public Page<QuestionLevel> search(String key, Pageable pageable) {
        try{
            Long n=Long.parseLong(key);
            return questionLevelRepository.findAllByLevelContainingIgnoreCase(n, key,  pageable);
        }
        catch (Exception x) {
            return questionLevelRepository.findAllByLevelContainingIgnoreCase((long)-1, String.valueOf(key), pageable);
        }
    }

    @Override
    public void deleteQuestionLevel(Long id) {
        overallRepository.deleteAll(overallRepository.findAllByQuestionLevelId(id));
        questionRepository.deleteAll(questionRepository.findAllByQuestionLevelId(id));
        questionLevelRepository.deleteById(id);
    }


}
