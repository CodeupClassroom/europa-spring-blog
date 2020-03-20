package com.codeup.springblog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="categories")
public class AdCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

//    @ManyToMany(mappedBy = "categories")
    @ManyToMany()
    @JoinTable(
            name="categories_ads",
            joinColumns = {@JoinColumn(name="category_id")},
            inverseJoinColumns = {@JoinColumn(name="ad_id")}
    )
    private List<Ad> ads;

    public AdCategory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
}