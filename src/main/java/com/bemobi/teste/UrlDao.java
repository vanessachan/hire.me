package com.bemobi.teste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * Created by Vanessa Chan on 14/01/2017.
 */
@Repository
@Transactional
public class UrlDao {
    @Autowired
    EntityManager em;

    public void insert(ShortenUrl sh){
        em.persist(sh);
    }
    public ShortenUrl buscaShortenUrl(String alias){
        return em.find(ShortenUrl.class, alias);
    }
}
