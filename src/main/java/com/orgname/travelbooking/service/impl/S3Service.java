package com.orgname.travelbooking.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.List;

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

        for(MultipartFile image : images) {
            final String objectKey = getObjectKey(travelPackageId, travelPackageName, image.getOriginalFilename());
            final PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectKey)
                    .build();
            byte[] file = image.getBytes();
            try {
                s3Client.putObject(request, RequestBody.fromBytes(file));
            } catch (S3Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<S3Object> getObjectList(final Long travelPackageId, final String travelPackageName) {
        final ListObjectsRequest listObjectsRequest = ListObjectsRequest.builder()
                .bucket(bucketName)
                .prefix(getFolderPrefix(travelPackageId, travelPackageName))
                .build();

        final ListObjectsResponse listObjectsResponse = s3Client.listObjects(listObjectsRequest);
        final List<S3Object> s3ObjectList = listObjectsResponse.contents();
        return s3ObjectList;
    }

    public byte[] getObject(final String bucketKey) {
        final GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(bucketKey)
                .build();

        final ResponseBytes<GetObjectResponse> getObjectResponse = s3Client.getObjectAsBytes(getObjectRequest);
        return getObjectResponse.asByteArray();
    }

    private String getFolderPrefix(final Long travelPackageId, final String travelPackageName) {
        final String folderName = travelPackageName.replaceAll("\\s", "-");
        final String folderPrefix = String.format("%s/%s-%s", bucketPrefix, folderName, travelPackageId);
        return folderPrefix;
    }


    private String getObjectKey(final Long travelPackageId, final String travelPackageName, final String imageName) {
        final String folderName = travelPackageName.replaceAll("\\s", "-");

        final String key = String.format("%s/%s-%s/%s", bucketPrefix, folderName, travelPackageId, imageName);
        return key;
    }
}
