package com.jgefroh.braindump.server.topics;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jgefroh.braindump.server.solutions.SolutionMapper;


@Stateless
public class TopicMapper {

    @Inject private SolutionMapper solutionMapper;

    public TopicDTO mapFull(final Topic entity) {
        TopicDTO dto = new TopicDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setOrganizationId(entity.getOrganization().getId());
        dto.setSolutions(solutionMapper.mapToDTOs(entity.getSolutions()));
        dto.setViewCount(entity.getViewCount());
        dto.setKudoCount(entity.getKudos() == null ? 0 : entity.getKudos().size());
        dto.setEditable(entity.isEditable());
        return dto;
    }

    public TopicSelectionDTO mapSelection(final Topic entity) {
        TopicSelectionDTO dto = new TopicSelectionDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setKudoCount(entity.getKudos().size());
        dto.setSolutionCount(entity.getSolutions().size());
        dto.setViewCount(entity.getViewCount());
        return dto;
    }
    
    public List<TopicDTO> mapToDTOs(final List<Topic> entities) {
        List<TopicDTO> dtos = new ArrayList<TopicDTO>();
        for (Topic entity : entities) {
            dtos.add(mapFull(entity));
        }
        return dtos;
    }

    public List<TopicSelectionDTO> mapToSelectionDTOs(List<Topic> entities) {
        List<TopicSelectionDTO> dtos = new ArrayList<TopicSelectionDTO>();
        for (Topic entity : entities) {
            dtos.add(mapSelection(entity));
        }
        return dtos;
    }
}
