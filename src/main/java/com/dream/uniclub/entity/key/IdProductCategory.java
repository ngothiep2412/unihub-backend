package com.dream.uniclub.entity.key;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class IdProductCategory implements Serializable {
    @Column(name = "id_product")
    private int idProduct;

    @Column(name = "id_category")
    private int idCategory;

}
