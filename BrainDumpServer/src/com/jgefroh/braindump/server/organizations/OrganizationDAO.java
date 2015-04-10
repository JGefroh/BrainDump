package com.jgefroh.braindump.server.organizations;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.jgefroh.braindump.server.core.BrainDumpGenericDAO;
import com.jgefroh.braindump.server.security.Permission;

@Stateless
public class OrganizationDAO extends BrainDumpGenericDAO {

    public List<Organization> getOrganizationsForUserWithPermission(final int userId, final Permission permission) {
        StringBuilder sql = new StringBuilder();
        sql.append("select O from Organization O, Membership M, User U");
        sql.append(" where M member of O.memberships");
        sql.append(" and U.id = M.user.id");
        sql.append(" and U.id = :userId");
        sql.append(" and :permission member of M.permissions");
 
        TypedQuery<Organization> query = getEntityManager().createQuery(sql.toString(), Organization.class);
        query.setParameter("permission", permission);
        query.setParameter("userId", userId);
        
        return query.getResultList();
    }

    public Membership getMembershipFor(int userId, int organizationId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select M from Membership M, Organization O");
        sql.append(" where M.user.id = :userId");
        sql.append(" and M member of O.memberships");
        sql.append(" and O.id = :organizationId");
 
        TypedQuery<Membership> query = getEntityManager().createQuery(sql.toString(), Membership.class);
        query.setParameter("organizationId", organizationId);
        query.setParameter("userId", userId);
        
        return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
    }
}
