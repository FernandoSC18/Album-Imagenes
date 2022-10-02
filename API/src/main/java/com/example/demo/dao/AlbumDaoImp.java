package com.example.demo.dao; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import com.example.demo.model.Album; 

@Repository
@Transactional
public class AlbumDaoImp implements AlbumDao {

    @PersistenceContext
    EntityManager entityManager; 

    @Override
    public List<Album> getAll() {
        String query = "SELECT A FROM Album A WHERE 1 = 1 ORDER BY A.albumId DESC"; 
        return entityManager.createQuery(query).getResultList();
    } 

    @Override
    public Album getById(Long albumId) {
        String query = "SELECT A FROM Album A WHERE A.albumId = :albumId";

        List<Album> listAlbums = entityManager.createQuery(query)
            .setParameter("albumId", albumId)
            .getResultList();

        return listAlbums.isEmpty() ? null: listAlbums.get(0);
    }

    @Override
    public Album getByName(String albumName) {
        String query = "SELECT A FROM Album A WHERE A.albumName = :albumName ORDER BY A.albumId DESC";

        List<Album> listAlbums = entityManager.createQuery(query)
            .setParameter("albumName", albumName)
            .getResultList();

        return listAlbums.isEmpty() ? null: listAlbums.get(0);
    }

    @Override
    public Album create(Album album) { 
        entityManager.merge(album);   
        Album newAlbum = getByName(album.getAlbumName());
        return newAlbum;
    }

    @Override
    public void modify(Album album) {
        entityManager.merge(album);   
    }

    @Override
    public void delete(Album album) {
 
        //for delete and album the cascade delete the mach records Image 
        entityManager.remove(entityManager.contains(album) ? album : entityManager.merge(album));
    }
    
}
