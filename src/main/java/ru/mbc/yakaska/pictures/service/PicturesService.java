package ru.mbc.yakaska.pictures.service;

import org.springframework.stereotype.Service;
import ru.mbc.yakaska.pictures.model.Picture;
import ru.mbc.yakaska.pictures.repository.PicturesRepository;

@Service
public class PicturesService {

    private final PicturesRepository picturesRepository;

    public PicturesService(PicturesRepository picturesRepository) {
        this.picturesRepository = picturesRepository;
    }

    public Iterable<Picture> getPictures() {
        return picturesRepository.findAll();
    }

    public Iterable<Picture> getByContentType(String contentType) {
        return picturesRepository.findByContentType(contentType);
    }

    public Picture getPictureById(Long id) {
        return picturesRepository.findById(id).orElse(null);
    }

    public void deletePicture(Long id) {
        picturesRepository.deleteById(id);
    }

    public Picture savePicture(Picture picture) {
        picturesRepository.save(picture);
        return picture;
    }
}
