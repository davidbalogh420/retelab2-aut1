package hu.bme.aut.retelab2.repository;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.domain.Note;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AdRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Ad save (Ad ad){return em.merge(ad);}



    public List<Ad> findAll() {
        return em.createQuery("SELECT a FROM Ad a", Ad.class).getResultList();
    }

    @Transactional
    public List<Ad> findByPriceRange(int minPrice, int maxPrice) {
        return em.createQuery("SELECT a FROM Ad a WHERE a.price BETWEEN :minPrice AND :maxPrice", Ad.class)
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }



}
