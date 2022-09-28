package com.quiz.converter;


import com.quiz.dto.BaseDTO;
import com.quiz.dto.UserDTO;
import com.quiz.entity.DistributedEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.stream.Collectors;

public abstract class AbstractDTOConverter<ENTITY extends DistributedEntity, DTO extends BaseDTO> {

    public abstract DTO convert(ENTITY entity);

    public void convert(final ENTITY entity, final DTO dto) {
        UserDTO userDTO = new UserDTO();
        dto.setId(entity.getId());
        dto.setCreated(entity.getCreated());
        dto.setModified(entity.getModified());
    }

    public Page<DTO> convertList(final Page<ENTITY> list){
        if(list.isEmpty()){
           return Page.empty();
        }

        return new PageImpl<>(list.stream().map(this::convert).collect(Collectors.toList()));
    }

}
