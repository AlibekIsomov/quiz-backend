package com.quiz.service.withoutDTO.impl;




import com.quiz.entity.FileEntity;
import com.quiz.repository.FileRepository;
import com.quiz.service.withoutDTO.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;

    @Override
    public List<FileEntity> getAll(String key) {
        return fileRepository.findAll();
    }

    @Override
    public FileEntity getById(Long id) {
        return fileRepository.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }

    @Override
    public FileEntity create(FileEntity entity) {
        if(entity.getId() == null)
        return fileRepository.save(entity);
        throw new RuntimeException("id must be null");
    }

    @Override
    public FileEntity update(FileEntity entity) {
        if(entity.getId() != null)
            return fileRepository.save(entity);
        throw new RuntimeException("id must not be null");
    }

    @Override
    public void delete(FileEntity entity) {
        fileRepository.delete(entity);
    }

    @Override
    public void deleteById(Long dataId) {
        fileRepository.deleteById(dataId);
    }
}
