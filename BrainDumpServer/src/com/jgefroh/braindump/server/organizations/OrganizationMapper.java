package com.jgefroh.braindump.server.organizations;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;


@Stateless
public class OrganizationMapper {
    
    public OrganizationDTO map(final Organization entity) {
        OrganizationDTO dto = new OrganizationDTO();
        dto.setName(entity.getName());
        dto.setId(entity.getId());
        dto.setEditable(entity.isEditable());
        return dto;
    }
    
    public List<OrganizationDTO> mapToDTOs(final List<Organization> entities) {
        List<OrganizationDTO> dtos = new ArrayList<OrganizationDTO>();
        for (Organization entity : entities) {
            dtos.add(map(entity));
        }
        return dtos;
    }
}
