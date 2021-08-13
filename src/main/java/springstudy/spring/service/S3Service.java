package springstudy.spring.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@NoArgsConstructor
public class S3Service {
    private AmazonS3 s3Client;
    public static final String CLOUD_FRONT_DOMAIN_NAME = "";

    // application.approperties에서 aws 연결 정보를 가져온다.
    @Value("${cloud.aws.credentials.accessKey")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    // S3Client를 가져오기 위한 자격증명,  Bean에 대한 의존성 주입 및 초기화
    @PostConstruct // 의존성 주입이 이루어진 후 초기화 수행
    public void setS3Client(){
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public String upload(String currentFilePath, MultipartFile file) throws IOException {
        // 고유한 key 값을 갖기 위해 현재 시간을 postFix로 붙여줌
        SimpleDateFormat date = new SimpleDateFormat("yyyymmddHHmmss");
        String fileName = file.getOriginalFilename() + "-" + date.format(new Date()); // S3 객체 식별 key 값

        //이미지 수정 시 기존 파일은 삭제
        if("".equals(currentFilePath) == false && currentFilePath != null){
            boolean isExistObject = s3Client.doesObjectExist(bucket,currentFilePath);
            // 이미 존재하는 객체인지 체크
            if(isExistObject == true){
                s3Client.deleteObject(bucket, currentFilePath); // 삭제 함수
            }
        }

        // putObject : 업로드를 위해 사용되는 함수
        s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead));


        return fileName;
        // URL을 DB에 저장하는 코드
    }

}