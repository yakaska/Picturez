package ru.mbc.yakaska.pictures.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.mbc.yakaska.pictures.model.Picture;
import ru.mbc.yakaska.pictures.service.PicturesService;

@RestController
public class DownloadController {

    private final PicturesService picturesService;

    public DownloadController(PicturesService picturesService) {
        this.picturesService = picturesService;
    }

    @GetMapping("download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Long id) {

        Picture picture = picturesService.getPictureById(id);
        if (picture == null) return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);


        byte[] data = picture.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(picture.getContentType()));
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(picture.getFileName())
                .build();
        headers.setContentDisposition(contentDisposition);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

}
