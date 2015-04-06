package com.jgefroh.braindump.server.topics;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;


@Stateless
public class TopicMapper {
    
    public List<TopicDTO> mapToDTOs(final List<Topic> entities) {
        List<TopicDTO> dtos = new ArrayList<TopicDTO>();
        for (Topic entity : entities) {
            TopicDTO dto = new TopicDTO();
            dtos.add(dto);
        }
        return dtos;
    }

    public List<TopicSelectionDTO> mapToSelectionDTOs(List<Topic> entities) {
        List<TopicSelectionDTO> dtos = new ArrayList<TopicSelectionDTO>();
        for (Topic entity : entities) {
            TopicSelectionDTO dto = new TopicSelectionDTO();
            dtos.add(dto);
        }
        return dtos;
    }
}
