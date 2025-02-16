package com.criabb.model;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class qrCodeTableEntity implements PanacheRepository<qrCodeEntity> {

    public qrCodeEntity findByLocation(String location) {
        return find("location", location).firstResult();
    }

    public List<qrCodeEntity> findAllEntity() {
        return this.findAll().list();
    }

    public void insert(String qrCodeEntityString, String location) {
        qrCodeEntity qrCodeEntity = new qrCodeEntity();
        qrCodeEntity.setStringByte(qrCodeEntityString);
        qrCodeEntity.setLocation(location);
        qrCodeEntity.setStatus(1);
        persist(qrCodeEntity);
    }
}
