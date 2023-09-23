package com.cloudinary.rest.services;

import com.cloudinary.rest.controllers.DTO.FileDTO;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {

    Cloudinary cloudinary;

    @Value("${cloud.name}") // revisar
    private final String cloudName = "";

    @Value("${cloud.api_key}") // revisar
    private final String apiKey = "";

    @Value("${cloud.api_secret}") // revisar
    private final String apiSecret = "";

    private Map<String, Object> params = new HashMap<>();

    public CloudinaryService(){
        params.put("cloud_name", cloudName);
        params.put("api_key", apiKey);
        params.put("api_secret", apiSecret);
        params.put("secure", true);

        cloudinary = new Cloudinary(params);
    }

    public Map<Object, Object> upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);

        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();

        return result;
    }

    public Map<Object, Object> delete(String id) throws IOException {

        Map<Object, Object> result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        return result;
    }

    private File convert(MultipartFile multipartFile){

        File file = new File(multipartFile.getOriginalFilename());

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(multipartFile.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

}
