package com.example.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import com.example.demo.model.Files; 

@Repository
@Transactional
public class FilesDaoImp implements FilesDao {
    
    @PersistenceContext
    EntityManager entityManager; 

    @Override
    public Files getById(Long fileId) {
        String query = "SELECT F FROM Files F WHERE F.fileId = :fileId";

        List<Files> listFiles = entityManager.createQuery(query)
            .setParameter("fileId", fileId)
            .getResultList();

        return listFiles.isEmpty() ? null: listFiles.get(0);
    }

    @Override
    public void create(Files files) { 
        entityManager.persist(files);  
    }

    @Override
    public void modify(Files files) {
        entityManager.merge(files);  
    }

    @Override
    public void delete(Files files) { 
        entityManager.remove(entityManager.contains(files) ? files : entityManager.merge(files));
    } 

    

} 

