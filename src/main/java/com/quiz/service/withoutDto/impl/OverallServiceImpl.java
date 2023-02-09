package com.quiz.service.withoutDto.impl;

import com.quiz.entity.Overall;
import com.quiz.entity.QuestionLevel;
import com.quiz.repository.DistributedRepository;
import com.quiz.repository.OverallRepository;
import com.quiz.service.withoutDto.OverAllService;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class OverallServiceImpl  extends AbstractService<Overall> implements OverAllService {
    
    @Autowired
	private OverallRepository overallRepository;


    public OverallServiceImpl(DistributedRepository<Overall> repository) {
        super(repository);
    }

    @Override
    public void someChangesForCreate(Overall entity) {

    }

    @Override
    public void someChangesForUpdate(Overall entity) {

    }

    //Davron
	@Override
	public Overall addOverall(Overall overall) {
		return this.overallRepository.save(overall);
	}

	@Override
	public Overall updateOverall(Overall overall) {
		return this.overallRepository.save(overall);
	}

	@Override
	public Set<Overall> getOveralls() {
		return new HashSet<Overall>(this.overallRepository.findAll());
	}

	@Override
	public Overall getOverall(Long overallId) {
		return this.overallRepository.findById(overallId).get();
	}

	@Override
	public void deleteOverall(Long overallId) {
		this.overallRepository.deleteById(overallId);
	}

	@Override
	public Set<Overall> getOverallsOfUser(String username) {
		return new HashSet<Overall>(this.overallRepository.getOverallsByUsername(username));
	}

	@Override
	public Set<Overall> getOverallsOfQuestionLevel(QuestionLevel questionLevel) {
		return new HashSet<>(this.overallRepository.findByQuestionLevel(questionLevel));
	}
    

}
