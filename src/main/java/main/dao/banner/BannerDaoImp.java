package main.dao.banner;

import java.io.Console;
import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import main.models.Banner;
import main.services.FileService;

@Repository @Transactional
public class BannerDaoImp implements BannerDao {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    FileService fileService;

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

    @Override
    public Banner uploadFile(Long id, String to, MultipartFile multipartFile) {

        String url = null;
        
        try {
            
            String fileName = multipartFile.getOriginalFilename();

            fileName = UUID.randomUUID().toString().concat(fileService.getExtension(fileName));

            url = fileService.uploadFile(multipartFile, fileName);
        }
        catch (Exception e) {

            LOG.info(e.getMessage(), e);
            
            url = e.getMessage();
        }

        Banner banner = null;

        if(url != null){
            banner = entityManager.find(Banner.class, id);

            if(to.equals("banner")) banner.setBannerImg(url);

            if(to.equals("perfil")) banner.setPerfilImg(url);
        }

        return banner;
    }
    
}
