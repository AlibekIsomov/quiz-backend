package com.quiz.service.withoutDTO.impl;


import com.quiz.entity.DistributedEntity;
import com.quiz.repository.DistributedRepository;
import com.quiz.service.withoutDTO.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public abstract class AbstractService<ENTITY extends DistributedEntity> implements CommonService<ENTITY> {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractService.class);

    private final DistributedRepository<ENTITY> repository;

    public AbstractService(DistributedRepository<ENTITY> repository) {
        this.repository = repository;
    }


    @Override
    public Page<ENTITY> getAll(Pageable pageable) {
        Page<ENTITY> some = repository.findAllByOrderByIdDesc(pageable);
        if(some.isEmpty()){
            return Page.empty();
        }
        return some;
    }

    @Override
    public ENTITY create(ENTITY entity) throws Exception {
        if(entity.isNewEntity()){
            entity.setCreated(LocalDateTime.now());
            entity.setModified(LocalDateTime.now());
            someChangesForCreate(entity);
            return repository.save(entity);
        }
        LOG.error("Failed to save the entity of class '{}', Id should be null", entity.getClass());
        return null;

    }

    @Override
    public ENTITY update(ENTITY entity) {
        if(!entity.isNewEntity()){
            entity.setCreated(LocalDateTime.now());
            entity.setModified(LocalDateTime.now());
            someChangesForUpdate(entity);
            return repository.save(entity);
        }

        LOG.error("Failed to save the entity of class '{}', Id should not be null", entity.getClass());
        return null;

    }

    /**
     * @return tanasini yozishingiz majburiy emas agar uzgarishlaringiz bulmasa FUNKSIYA TANASINI OCHIQ QOLDIRING
     * @param entity kiruvchi entity bu uzgartishingiz kerak bulgan entity;
     */
    public abstract void someChangesForCreate(ENTITY entity);
    /**
     * tanasini yozishingiz majburiy emas agar uzgarishlaringiz bulmasa FUNKSIYA TANASINI OCHIQ QOLDIRING
     * @param entity kiruvchi entity bu uzgartishingiz kerak bulgan entity;
     */
    public abstract void someChangesForUpdate(ENTITY entity);

    @Override
    public ENTITY getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(Long id) {
        ENTITY entity = getById(id);
        if(entity == null){
            LOG.error("Failed to delete entity with ID '{}' as it does not exist", id);
            return false;
        }
        try {
            repository.delete(entity);
            return true;
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }

    }
}