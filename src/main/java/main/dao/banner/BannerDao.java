package main.dao.banner;

import java.util.List;

import main.models.Banner;

public interface BannerDao {
    
    List<Banner> getBanner();

    Banner updateBanner(Long id, Banner banner);
}
