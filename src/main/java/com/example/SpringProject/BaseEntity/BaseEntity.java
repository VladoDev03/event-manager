package com.example.SpringProject.BaseEntity;

import javax.persistence.*;



@MappedSuperclass
public abstract class BaseEntity {
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
