package com.example.products.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="Products")
@Data
public class Product implements Serializable {

    @Id
    private int  id;
    private String name;

  //  @Column
  //  private String description;

}
