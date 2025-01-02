package com.example.SpringProject.BaseEntity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }

    public BaseEntity() {
    }

    public BaseEntity(long id) {
        this.id = id;
    }
}
