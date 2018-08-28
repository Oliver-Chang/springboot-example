package com.oliver.ElectronicCommerce.commodity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.oliver.ElectronicCommerce.category.Category;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer"})
public class Commodity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer secondaryCategory;
    private BigDecimal price;
    private Integer quantity;
    private String brand;
    private String recommend;
    private String image;

    @ManyToMany
    @JsonIgnoreProperties("commodities")
    private Set<Category> categories = new HashSet<>();

    public void addCategories(List<Category> categories) {
        for (Category category : categories) {
            Set<Commodity> commodities = category.getCommodities();
            commodities.add(this);
            this.categories.add(category);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSecondaryCategory() {
        return secondaryCategory;
    }

    public void setSecondaryCategory(Integer secondaryCategory) {
        this.secondaryCategory = secondaryCategory;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
