package com.shop.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by geely
 */
public interface FileService {
    String upload(MultipartFile file, String path);
}
