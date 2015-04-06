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
        TypedQuery<Topic> query = getEntityManager().createQuery("select T from Topic T where T.organizationId = :organizationId", Topic.class);
        query.setParameter("organizationId", organizationId);
        
        return query.getResultList();
    }
}
