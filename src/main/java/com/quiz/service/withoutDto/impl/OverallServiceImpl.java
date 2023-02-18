package com.quiz.service.withoutDto.impl;

import com.quiz.Security.JwtTokenUtil;
import com.quiz.dto.OverallDTO;
import com.quiz.dto.UserDTO;
import com.quiz.entity.Overall;
import com.quiz.entity.QuestionLevel;
import com.quiz.repository.DistributedRepository;
import com.quiz.repository.OverallRepository;
import com.quiz.repository.UserRepository;
import com.quiz.service.witDto.Impl.UserServiceImpl;
import com.quiz.service.withoutDto.OverAllService;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OverallServiceImpl  extends AbstractService<Overall> implements OverAllService {
    
    @Autowired
	private OverallRepository overallRepository;
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private UserRepository userRepository;

    public OverallServiceImpl(DistributedRepository<Overall> repository) {
        super(repository);
    }

    @Override
    public void someChangesForCreate(Overall entity) {

    }

    @Override
    public void someChangesForUpdate(Overall entity) {

    }

	@Override
	public Overall addOverallWithUser(Overall overall){
		overall.setUser(userRepository.findByUsername(userServiceImpl.getCurrentUser().getUsername()).get());
		return overallRepository.save(overall);
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
	public OverallDTO getOverallDTO(Long id) {
		Overall overall = overallRepository.findById(id).get();
		OverallDTO overallDTO = new OverallDTO();
		overallDTO.setDate(overall.getDate());
		overallDTO.setAttemptedQuestions(overall.getAttemptedQuestions());
		overallDTO.setName(overall.getUser().getName());
		overallDTO.setPoint(overall.getPoint());
		overallDTO.setScore(overall.getScore());
		overallDTO.setSurname(overall.getUser().getSurname());
		overallDTO.setUsername(overall.getUser().getUsername());
		overallDTO.setQuestionLevel(overall.getQuestionLevel());
		return overallDTO;
	}

	@Override
	public Overall getOverall(Long overallId) {
		return this.overallRepository.findById(overallId).get();
	}

	@Override
	public Page<OverallDTO> getAllDTO(Pageable pageable) {
		Page<Overall> overalls = overallRepository.findAll(pageable);
		Page<OverallDTO> overallDTOPage = overalls.map(OverallDTO::new);
		return overallDTOPage;
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
