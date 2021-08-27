package springstudy.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springstudy.spring.domain.Gallery;
import springstudy.spring.dto.GalleryDto;
import springstudy.spring.repository.GalleryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GalleryService { // DB 저장 로직
    private GalleryRepository galleryRepository;
    private S3Service s3Service;

    public void savePost(GalleryDto galleryDto){
        galleryRepository.save(galleryDto.toEntity());
    }

    public List<GalleryDto> getList(){
        List<Gallery> galleryList = galleryRepository.findAll();
        List<GalleryDto> galleryDtoList = new ArrayList<>();

        for(Gallery gallery : galleryList){
            galleryDtoList.add(convertEntityToDto(gallery));
        }
        return galleryDtoList;
    }

    public GalleryDto convertEntityToDto(Gallery gallery){
        return GalleryDto.builder()
                .id(gallery.getId())
                .title(gallery.getTitle())
                .filePath(gallery.getFilePath())
                .imgFullPath("https://" + s3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + gallery.getFilePath()) // How?
                .build();
    }
}