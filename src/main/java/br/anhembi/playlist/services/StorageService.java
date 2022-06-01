package br.anhembi.playlist.services;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.mongodb.AwsCredential;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.lang.model.type.ReferenceType;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageService {

    private final AmazonS3 space;

    public StorageService(){
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials("K5A77B2DOGVXJHVGQ3FC", "KaRyLCgan5l4yAolI+2TWgnwA/JCjG1rMMupmPvb7T0")
        );

        space = AmazonS3ClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("sfo3.digitaloceanspaces.com", "sfo3")
                )
                .build();
        }

        public List<String> getSongFileNames(){
            ListObjectsV2Result result = space.listObjectsV2("playlist");
            List<S3ObjectSummary> objects = result.getObjectSummaries();

            return objects.stream()
                    .map(s3ObjectSummary -> {
                        return s3ObjectSummary.getKey();
                    }).collect(Collectors.toList());

        }

        public void uploadSong(MultipartFile file) throws IOException {
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentType(file.getContentType());
                space.putObject(new PutObjectRequest("playlist", file.getOriginalFilename(), file.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
        }
}
