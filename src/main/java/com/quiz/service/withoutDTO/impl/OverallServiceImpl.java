package com.quiz.service.withoutDTO.impl;

import com.quiz.dto.OverallDTO;
import com.quiz.entity.Overall;
import com.quiz.repository.DistributedRepository;
import com.quiz.repository.OverallRepository;
import com.quiz.repository.UserRepository;
import com.quiz.service.withDTO.Impl.UserServiceImpl;
import com.quiz.service.withoutDTO.OverAllService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OverallServiceImpl  extends AbstractService<Overall> implements OverAllService {
	private static final Logger LOG = LoggerFactory.getLogger(AbstractService.class);
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
	public Page<OverallDTO> getAllDTO(Pageable pageable) {
		Page<Overall> overalls = overallRepository.findAll(pageable);
		Page<OverallDTO> overallDTOPage = overalls.map(OverallDTO::new);
		return overallDTOPage;
	}

	@Override
	public List<Overall> getOverallsOfQuestionLevel(Long id) {
		List<Overall> questionLevelId = overallRepository.findAllByQuestionLevelId(id);

		return questionLevelId;
	}


}
