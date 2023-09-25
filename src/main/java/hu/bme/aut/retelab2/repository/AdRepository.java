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
import java.util.Objects;

@Repository
public class AdRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Ad save (Ad ad){return em.merge(ad);}



    public List<Ad> findAll() {
        return em.createQuery("SELECT NEW Ad(a.id, a.title, a.description, a.price, a.date) FROM Ad a", Ad.class).getResultList();

    }

    public List<Ad> privateFind(){
        return em.createQuery("SELECT a FROM Ad a", Ad.class).getResultList();
    }

    @Transactional
    public List<Ad> findByPriceRange(int minPrice, int maxPrice) {
        return em.createQuery("SELECT a FROM Ad a WHERE a.price BETWEEN :minPrice AND :maxPrice", Ad.class)
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }

    public Ad findById(Long id) {
        return em.find(Ad.class, id);
    }

    @Transactional
    public Ad updateById(Ad u){
        Ad a = em.find(Ad.class,u.getId());
        if(a==null){
            return null;
        }
        else if(a.getCode().equals(u.getCode())){
            a.setDescription(u.getDescription());
            a.setPrice(u.getPrice());
            a.setTitle(u.getTitle());
            return a;
        }
        else {
            return null;
        }
    }



}
