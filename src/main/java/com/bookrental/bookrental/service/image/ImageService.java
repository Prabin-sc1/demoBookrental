package com.bookrental.bookrental.service.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface ImageService {

    String upload(String path, MultipartFile file) throws IOException;

    InputStream getImage(String path, String fileName) throws FileNotFoundException;
}
