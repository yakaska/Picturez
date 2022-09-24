package ru.mbc.yakaska.pictures.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.mbc.yakaska.pictures.model.Picture;
import ru.mbc.yakaska.pictures.service.PicturesService;

import java.io.IOException;

@RestController
public class PicturesController {

    private final PicturesService picturesService;

    public PicturesController(PicturesService picturesService) {
        this.picturesService = picturesService;
    }

    @GetMapping("/pictures")
    public Iterable<Picture> index(@RequestParam(required = false) String contentType) {
        if (contentType == null) return picturesService.getPictures();
        return picturesService.getByContentType(contentType);
    }

    @GetMapping("/pictures/{id}")
    public Picture getById(@PathVariable Long id) {
        Picture picture = picturesService.getPictureById(id);
        if (picture == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return picture;
    }

    @PostMapping("/pictures")
    public Picture add(@RequestPart("data") MultipartFile file) throws IOException {
        Picture picture = new Picture(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        return picturesService.savePicture(picture);
    }

    @DeleteMapping("/pictures/{id}")
    public void delete(@PathVariable Long id) {
        picturesService.deletePicture(id);
    }

}
