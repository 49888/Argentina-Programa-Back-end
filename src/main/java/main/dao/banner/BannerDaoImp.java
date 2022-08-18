package main.dao.banner;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import main.models.Banner;

@Repository @Transactional
public class BannerDaoImp implements BannerDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Banner> getBanner() {

        String query = "FROM Banner";

        List<Banner> result = entityManager.createQuery(query).getResultList();
        
        return result;
    }

    @Override
    public Banner updateBanner(Long id, Banner aux) {
        
        
        Banner banner = entityManager.find(Banner.class, id);

        if(banner != null){

            if(aux.getName() != null) banner.setName(aux.getName());

            if(aux.getTitle() != null) banner.setTitle(aux.getTitle());

            if(aux.getInformation() != null) banner.setInformation(aux.getInformation());

            if(aux.getBannerImg() != null) banner.setBannerImg(aux.getBannerImg());

            if(aux.getPerfilImg() != null) banner.setPerfilImg(aux.getPerfilImg());

            return banner.clone();
        }
        else {

            return null;
        }


        
    }
    
}
