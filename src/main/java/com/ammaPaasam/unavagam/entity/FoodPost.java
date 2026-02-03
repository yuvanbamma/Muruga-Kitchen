package com.ammaPaasam.unavagam.entity;

import com.ammaPaasam.unavagam.entity.base.Audit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FoodPost extends Audit {

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false)
    private Integer quantity;

}
