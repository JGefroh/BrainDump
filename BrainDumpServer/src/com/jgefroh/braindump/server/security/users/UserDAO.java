package com.jgefroh.braindump.server.security.users;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.jgefroh.braindump.server.core.BrainDumpGenericDAO;

/**
 * @author Joseph Gefroh
 */
@Stateless
public class UserDAO extends BrainDumpGenericDAO {

    public User getUserWithUUID(final String uuid) {
        TypedQuery<User> query = getEntityManager().createQuery("select U from User U where U.uuid = :uuid", User.class);
        query.setParameter("uuid", uuid);
        
        List<User> user = query.getResultList();
        if (user.isEmpty()) {
            return null;
        }
        else {
            return user.get(0);
        }
    }
}
