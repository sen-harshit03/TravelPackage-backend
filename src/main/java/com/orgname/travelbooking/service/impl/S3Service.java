package com.orgname.travelbooking.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;

/**
 * bucket -> travelPackges -> rajasthan-tourism-id -> images1, image2, image3
 *
 */
@Component
@RequiredArgsConstructor
public class S3Service {

    @Value("${aws.bucket.name}")
    private String bucketName;
    @Value("${aws.bucket.prefix}")
    private String bucketPrefix;

    private final S3Client s3Client;

    public void uploadImage(final Long travelPackageId,
                            final String travelPackageName,
                            final MultipartFile[] images
                            ) throws IOException {

        int imageCount = 1;
        for(MultipartFile image : images) {
            final String folderKey = getKey(travelPackageId, travelPackageName, image.getOriginalFilename());
            final PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(folderKey)
                    .build();
            byte[] file = image.getBytes();
            try {
                s3Client.putObject(request, RequestBody.fromBytes(file));
            } catch (S3Exception e) {
                e.printStackTrace();
            }
        }
    }



    private String getKey(final Long travelPackageId, final String travelPackageName, final String imageName) {
        final String folderName = travelPackageName.replaceAll("\\s", "-");

        final String key = String.format("%s/%s-%s/%s", bucketPrefix, folderName, travelPackageId, imageName);
        return key;
    }
}
