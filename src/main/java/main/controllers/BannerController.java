package main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import main.dao.banner.BannerDao;
import main.models.Banner;


@RestController @RequestMapping("/api/banner") @CrossOrigin(origins = {"https://49888.github.io", "https://argentina-programa-abb9b.web.app"})
public class BannerController {
    
    @Autowired
    private BannerDao bannerDao;


    //-> GET
    @RequestMapping(value = "/get")
    public Banner getBanner(){

        List<Banner> list = bannerDao.getBanner();

        return list.get(0);
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



    //Upload Images
    @RequestMapping(value = "/images/update", method = RequestMethod.POST)
    public Banner updateImage(@RequestParam("to") String to, @RequestParam("file") MultipartFile multipartFile){

        return bannerDao.uploadFile(1l, to, multipartFile);
    }
}
