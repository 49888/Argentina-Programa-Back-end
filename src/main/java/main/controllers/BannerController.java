package main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import main.dao.banner.BannerDao;
import main.models.Banner;


@RestController @RequestMapping("/api/banner")
public class BannerController {
    
    @Autowired
    private BannerDao bannerDao;


    //-> GET
    @RequestMapping(value = "/get")
    public List<Banner> getBanner(){

        List<Banner> list = bannerDao.getBanner();

        return list;
    }

    //-> UPDATE
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Banner updateBanner(@RequestBody Banner banner){

        return bannerDao.updateBanner(1l, banner);
    }


    //-> DELETE
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String deleteBanner(){

        return "No se puede Eliminar el banner";
    }

    //-> CREATE
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createBanner(){

        return "No se puede crear otro banner";
    }
}
