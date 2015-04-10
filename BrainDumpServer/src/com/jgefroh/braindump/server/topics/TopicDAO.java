package com.jgefroh.braindump.server.topics;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.jgefroh.braindump.server.core.BrainDumpGenericDAO;

/**
 * @author Joseph Gefroh
 */
@Stateless
public class TopicDAO extends BrainDumpGenericDAO {

    public List<Topic> getTopicsForOrganization(final int organizationId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select T from Topic T");
        sql.append(" where T.organization.id = :organizationId");
        
        TypedQuery<Topic> query = getEntityManager().createQuery(sql.toString(), Topic.class);
        query.setParameter("organizationId", organizationId);
        
        return query.getResultList();
    }
}
