package com.orgname.travelbooking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;
import software.amazon.awssdk.services.sts.model.Credentials;

@Configuration
public class AWSConfig {
    private String roleArn = "arn:aws:iam::905418248720:role/Demo-Role";
    private String sessionName = "test-session";

    @Bean
    public StsClient stsClient() {
        return StsClient.builder()
                .region(Region.AP_SOUTH_1)
                .build();
    }

    @Bean
    public AssumeRoleResponse assumeRoleResponse(final StsClient stsClient) {
        AssumeRoleRequest roleRequest = AssumeRoleRequest.builder()
                .roleArn(roleArn)
                .roleSessionName(sessionName)
                .durationSeconds(3600)
                .build();
        AssumeRoleResponse roleResponse = stsClient.assumeRole(roleRequest);
        return roleResponse;
    }

    @Bean
    public AwsCredentialsProvider awsCredentialsProvider(final AssumeRoleResponse assumeRoleResponse) {
        final Credentials tempCredentials = assumeRoleResponse.credentials();

        final AwsSessionCredentials sessionCredentials  = AwsSessionCredentials.builder()
                .accessKeyId(tempCredentials.accessKeyId())
                .secretAccessKey(tempCredentials.secretAccessKey())
                .sessionToken(tempCredentials.sessionToken())
                .build();

        final StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(sessionCredentials);
        return credentialsProvider;
    }

    @Bean
    public S3Client s3Client(final AwsCredentialsProvider awsCredentialsProvider) {
        return S3Client.builder()
                .region(Region.AP_SOUTH_1)
                .credentialsProvider(awsCredentialsProvider)
                .build();
    }
}

