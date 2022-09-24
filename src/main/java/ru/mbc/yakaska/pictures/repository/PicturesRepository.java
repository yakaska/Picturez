package ru.mbc.yakaska.pictures.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mbc.yakaska.pictures.model.Picture;

@Repository
public interface PicturesRepository extends CrudRepository<Picture, Long> {

    Iterable<Picture> findByContentType(String contentType);

}
