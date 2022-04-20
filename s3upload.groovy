@Grapes(
    @Grab(group='software.amazon.awssdk', module='s3', version='2.17.172', scope='test')
)
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.*

import java.io.File
import java.nio.file.Paths

// Configurations
String accessKey = vars.get("AWS_ACCESS_KEY")
String secretKey = vars.get("AWS_SECRET_KEY")
String bucketName = vars.get("AWS_BUCKET_NAME")
String strFilename = "C:\\temp\\result.json"

try {
	// Set region
	Region region = Region.US_EAST_1
	
	// Create credentials
	AwsBasicCredentials awsCreds = AwsBasicCredentials.create(
	      accessKey,
	      secretKey)
	
	// Build S3 Client
	S3Client s3 = S3Client.builder()
	      .region(region)
	      .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
	      .build()
	
	// Create file object
	File s3Obj = new File(strFilename)
	
	// Create PUT request
	PutObjectRequest request = PutObjectRequest.builder()
	      .bucket(bucketName)
	      .key(s3Obj.getName())
	      .build()
	
	// Upload file
	s3.putObject(request, Paths.get(strFilename))
}

// Catching exception and displaying it in the Sample result
catch (S3Exception e){
	SampleResult.setSuccessful(false)
	SampleResult.setResponseMessage(e.getMessage())
	SampleResult.setResponseCode(null)
}
