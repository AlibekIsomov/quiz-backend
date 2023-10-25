package com.quiz.service.withoutDTO.impl;


import com.quiz.entity.Fayl;
import com.quiz.repository.FaylRepository;
import com.quiz.service.withoutDTO.FaylService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaylServiceImpl implements FaylService {

    private final FaylRepository faylRepository;

    public FaylServiceImpl(FaylRepository faylRepository) {
        this.faylRepository = faylRepository;
    }

    @Override
    public List<Fayl> getAll(String key) {
        return faylRepository.findAll();
    }

    @Override
    public Fayl getById(Long id) {
        return faylRepository.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }

    @Override
    public Fayl create(Fayl entity) {
        if(entity.getId() == null)
        return faylRepository.save(entity);
        throw new RuntimeException("id must be null");
    }

    @Override
    public Fayl update(Fayl entity) {
        if(entity.getId() != null)
            return faylRepository.save(entity);
        throw new RuntimeException("id must not be null");
    }

    @Override
    public void delete(Fayl entity) {
            faylRepository.delete(entity);
    }

    @Override
    public void deleteById(Long dataId) {
            faylRepository.deleteById(dataId);
    }
}
