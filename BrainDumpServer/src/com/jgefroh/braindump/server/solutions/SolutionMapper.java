package com.jgefroh.braindump.server.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jgefroh.braindump.server.security.users.UserMapper;


@Stateless
public class SolutionMapper {

    @Inject private UserMapper userMapper;
    
    public SolutionDTO map(final Solution entity) {
        SolutionDTO dto = new SolutionDTO();
        dto.setId(entity.getId());
        dto.setKudoCount(entity.getKudos() == null ? 0 : entity.getKudos().size());
        dto.setText(entity.getText());
        dto.setEditable(entity.isEditable());
        dto.setCreator(userMapper.map(entity.getCreator()));
        dto.setKudoUsed(entity.isKudoUsed());
        return dto;
    }

    public List<SolutionDTO> mapToDTOs(final List<Solution> entities) {
        List<SolutionDTO> dtos = new ArrayList<SolutionDTO>();
        for (Solution entity : entities == null ? Collections.<Solution>emptyList() : entities) {
            dtos.add(map(entity));
        }
        return dtos;
    }
}
