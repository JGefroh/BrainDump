package com.jgefroh.braindump.server.core;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jgefroh.server.core.AbstractGenericDAO;

/**
 * @author Joseph Gefroh
 */
public class BrainDumpGenericDAO extends AbstractGenericDAO {
    @PersistenceContext(unitName = "BrainDumpDS") private EntityManager entityManager;
    
    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
