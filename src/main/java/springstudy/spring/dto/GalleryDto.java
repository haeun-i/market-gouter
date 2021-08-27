package springstudy.spring.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import springstudy.spring.domain.Gallery;

@Getter
@Setter
@ToString // 객체를 문자화
public class GalleryDto {
    private Long id;
    private String title;
    private String filePath;
    private String imgFullPath;

    public Gallery toEntity(){
        Gallery build = Gallery.builder()
                .id(id)
                .title(title)
                .filePath(filePath)
                .build();
        return build;
    }

    @Builder // 객체 생성 + 생성자 호출로 빌더 객체를만든다.
    public GalleryDto(Long id, String title, String filePath, String imgFullPath){
        this.id = id;
        this.title = title;
        this.filePath = filePath;
        this.imgFullPath = imgFullPath;
    }
}