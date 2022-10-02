package com.example.demo.dao;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import com.example.demo.model.Image;
import com.example.demo.model.ImageDetail; 

@Repository
@Transactional
public class ImageDaoImp implements ImageDao {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    FilesDao filesDao;

    @Override
    public List<Image> getAll() {
        String query = "SELECT I FROM Image I WHERE 1 = 1 ORDER BY I.imageId DESC" ; 
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Image getById(Long imageId) {
        String query = "SELECT I FROM Image I WHERE I.imageId = :imageId";

        List<Image> listImages = entityManager.createQuery(query)
            .setParameter("imageId", imageId)
            .getResultList();

        return listImages.isEmpty() ? null: listImages.get(0);
    }

    /*
     * ImageDetail return an image whit more detail than Image, this return an album and file whit mach by ID
     */
    @Override
    public ImageDetail getDetailById(Long imageId) {
        String query = "SELECT I FROM ImageDetail I WHERE I.imageId = :imageId";

        List<ImageDetail> listImages = entityManager.createQuery(query)
            .setParameter("imageId", imageId)
            .getResultList();

        return listImages.isEmpty() ? null: listImages.get(0);
    } 
 
    /*
     * return an List of Images for chunk elemnts and load when user needs
     * chunSection * chunksize return elements in this range
     * this range envolve also the album id, so only gets image for specific album
     * Filter : check string name math and return filter list
     */
    @Override
    public List<ImageDetail> getDetailByRange(Long albumId, int chunkSize, int chunSection, String filterName) { 
        if (chunSection <= 0 || chunkSize <= 0) return null;
        
        //Rest chunkSize for get and min and max limit in a range data
        int startIn = (chunSection * chunkSize) - chunkSize ;
        System.out.println("startIn :: " + startIn);
        System.out.println("chunkSize :: " + chunkSize);
        String query = "SELECT I FROM ImageDetail I WHERE 1 = 1 AND I.album.albumId = :albumId " +
        "AND I.imageName LIKE :imageName ORDER BY I.imageId DESC"; 
        List<ImageDetail> listImages = entityManager.createQuery(query)
            .setParameter("albumId", albumId)
            .setParameter("imageName", "%" + (filterName != null ? filterName : "" ) + "%")
            .setFirstResult(startIn)
            .setMaxResults(chunkSize)
            .getResultList();

        return listImages.isEmpty() ? null: listImages;
    } 

    @Override
    public Image getByName(String imageName) {
        String query = "SELECT I FROM Image I WHERE I.imageName = :imageName";

        List<Image> listImages = entityManager.createQuery(query)
            .setParameter("imageName", imageName)
            .getResultList();

        return listImages.isEmpty() ? null: listImages.get(0);
    }

    @Override
    public ImageDetail create(ImageDetail imageDetail) { 
        ImageDetail newImageDetail = (ImageDetail) entityManager.merge(imageDetail);   

        return newImageDetail;
    }

    @Override
    public void modify(ImageDetail imageDetail) {
        entityManager.merge(imageDetail);  
    }
 

    @Override
    public void delete(Image image) {  
        entityManager.remove(entityManager.contains(image) ? image : entityManager.merge(image));
    } 
 
}
