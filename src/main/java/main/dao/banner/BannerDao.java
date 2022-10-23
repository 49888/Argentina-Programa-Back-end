package main.dao.banner;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import main.models.Banner;

public interface BannerDao {
    
    List<Banner> getBanner();

    Banner updateBanner(Long id, Banner banner);

    Banner uploadFile(Long id, String image, MultipartFile multipartFile);
}
