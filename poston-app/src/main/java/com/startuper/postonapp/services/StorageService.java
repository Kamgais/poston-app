package com.startuper.postonapp.services;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@Slf4j
@Service
public class StorageService {


    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;




    public String uploadFile(MultipartFile file) {
        File fileObj  = convertToFile(file);
        String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
        // save to aws
        s3Client.putObject(new PutObjectRequest(bucketName, fileName,fileObj));

        // Set the object ACL to public-read
       // s3Client.setObjectAcl(bucketName,fileName, CannedAccessControlList.PublicRead);
       // s3Client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        fileObj.delete();
        String uri = generatePublicUrl(bucketName,fileName);

        return uri;
    }


    private File convertToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try(FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
      } catch (IOException e) {
           log.error("Error converting mutipartfile to file", e);
        }
        return convertedFile;
    }


    private String generatePublicUrl(String bucketName, String key) {
        // You can customize the URL format based on your bucket and region
        return "https://" + bucketName + ".s3.amazonaws.com/" + key;
    }
}
