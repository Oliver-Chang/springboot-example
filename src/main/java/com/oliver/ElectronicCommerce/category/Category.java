package com.oliver.ElectronicCommerce.category;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.oliver.ElectronicCommerce.commodity.Commodity;


import javax.persistence.*;
import java.io.Serializable;

import java.util.HashSet;

import java.util.Set;


@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer"})
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnoreProperties("categories")
    private Set<Commodity> commodities = new HashSet();

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

    public Set<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(Set<Commodity> commodities) {
        this.commodities = commodities;
    }
}
