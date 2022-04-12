@Grab('com.amazonaws:aws-java-sdk:1.12.196')
import com.amazonaws.auth.*
import com.amazonaws.services.s3.*
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils

String accessKey = vars.get("AWS_ACCESS_KEY")
String secretKey = vars.get("AWS_SECRET_KEY")
String bucketName = vars.get("AWS_BUCKET_NAME")

Regions clientRegion = Regions.US_EAST_1;

def credentials = new BasicAWSCredentials(accessKey, secretKey)
def s3client = new AmazonS3Client(credentials)
s3client.withRegion(clientRegion)

def strFilename = 'C:\\Training\\Training\\apache-jmeter-5.4.3\\bin\\jmeter.log'

s3Obj = new File(strFilename)
//String s3FileName = file.name.take(file.name.lastIndexOf('.'))

PutObjectRequest request = new PutObjectRequest(bucketName, s3Obj.getName().toString(), s3Obj);
//ObjectMetadata metadata = new ObjectMetadata();
//metadata.setContentType("plain/text");
//metadata.addUserMetadata("title", "someTitle");
//request.setMetadata(metadata);
s3client.putObject(request);

log.info("Upload successful")

//accessKey = 'accesskey'
//secretKey = 'secretkey'
//bucketName = 'bucketname'
//fileName = './S3Upload.jmx'
//
//credentials = new AWSCredentials(accessKey, secretKey)
//service = new RestS3Service(credentials)
//bucket = new S3Bucket(bucketName)
//file = new File(fileName)
//fileObject = new S3Object(file)
//fileObject.key = fileName
//service.putObject(bucket, fileObject)
//expiryTime = new Date() + 1 // 24 hours from current date
//link = service.createSignedGetUrl(bucket.name, fileObject.key, expiryTime)
//println "$fileName : $link"
