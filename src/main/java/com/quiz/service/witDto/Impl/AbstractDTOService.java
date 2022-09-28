package com.quiz.service.witDto.Impl;

import com.quiz.converter.AbstractDTOConverter;
import com.quiz.dto.BaseDTO;
import com.quiz.entity.DistributedEntity;
import com.quiz.repository.DistributedRepository;
import com.quiz.service.witDto.CommonServiceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public abstract class AbstractDTOService<ENTITY extends DistributedEntity, DTO extends BaseDTO> implements CommonServiceDto<ENTITY,DTO> {
    
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDTOService.class);

    private DistributedRepository<ENTITY> repository;
    private AbstractDTOConverter<ENTITY, DTO> converter;


    public AbstractDTOService(DistributedRepository<ENTITY> repository, AbstractDTOConverter<ENTITY, DTO> converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public Page<DTO> getAll(Pageable pageable) {
        Page<ENTITY> entity = repository.findAll(pageable);

        if(entity.isEmpty()){
            return Page.empty();
        }

        return converter.convertList(entity);
    }

    @Override
    public DTO create(ENTITY entity) throws Exception{
        if(entity.isNewEntity()){
            entity.setCreated(LocalDateTime.now());
            entity.setModified(LocalDateTime.now());
            someChangesForCreate(entity);

            repository.save(entity);

            return converter.convert(entity);
        }
        else{
            LOG.error("Failed to save the entity of class '{}', Id should be null", entity.getClass());
            return null;
        }
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
    public DTO update(ENTITY entity) {
        if(!entity.isNewEntity()){
            entity.setCreated(LocalDateTime.now());
            entity.setModified(LocalDateTime.now());
            someChangesForUpdate(entity);
            repository.save(entity);

            return converter.convert(entity);
        }
        else{
            LOG.error("Failed to save the entity of class '{}', Id shouldn't be null", entity.getClass());
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
        final ENTITY entity = findEntityById(id);
        if (entity == null) {
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

    @Override
    public DTO getById(Long id) {
        return converter.convert(repository.findById(id).orElse(null));
    }

    private ENTITY findEntityById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
