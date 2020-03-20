package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.Image;
import com.codeup.springblog.repositories.AdRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class AdController {

    private AdRepo adDao;

    public AdController(AdRepo adDao) {
        this.adDao = adDao;
    }

    @GetMapping("/ads")
    @ResponseBody
    public List<Ad> getAllAds() {
        return adDao.findAll();
    }

    // ad creation example with out form-model-binding
//        @GetMapping("/ads/create")
//        public String adCreateForm() {
//            return "ads/create";
//        }
//
//        @PostMapping("/ads")
//        public String insertAd(
//                @RequestParam String title,
//                @RequestParam String description,
//                @RequestParam String image1,
//                @RequestParam String image2,
//                @RequestParam String image3
//        ) {
//            Ad ad = new Ad();
//            ad.setTitle(title);
//            ad.setDescription(description);
//            ad.setImages(Arrays.asList(
//                    new Image(image1, ad),
//                    new Image(image2, ad),
//                    new Image(image3, ad)
//            ));
//
//            adDao.save(ad);
//
//            return "redirect:/ads";
//        }


    // ad creation with form-model-binding
    @GetMapping("/ads/create")
    public String adCreateForm(Model model) {
        model.addAttribute("ad", new Ad());
        return "ads/create-m";
    }

    @PostMapping("/ads")
    public String insertAd(@ModelAttribute Ad ad) {
        List<Image> images = ad.getImages();
        // needed to associate the child entity with its parent
        for (Image image : images) {
            image.setAd(ad);
        }
        adDao.save(ad);
        return "redirect:/ads";
    }


    @GetMapping("/ads/update")
    @ResponseBody
    public String updateAd() {
        Ad ad = adDao.getOne(1L);
        ad.setTitle("Updated Title!");
        adDao.save(ad);
        return "Updating ad";
    }


    @GetMapping("/ads/search")
    public String searchAd(Model model) {
        Ad ad = adDao.findByTitle("scalable");
        model.addAttribute("ad", ad);
        return "ads/search";
    }



}
