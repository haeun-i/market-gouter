package springstudy.spring.domain;

import lombok.*;

import javax.persistence.*;

@Entity
//@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "ItemPhoto")
@Getter @Setter
public class ItemPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_photo_id")
    private Long id;

    // item과의 FK
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(nullable = false)
    private String origFileName;  // 파일 원본명

    @Column(nullable = false)
    private String filePath;  // 파일 저장 경로

    private Long fileSize;

    @Builder
    public ItemPhoto(String origFileName, String filePath, Long fileSize) {
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    // Item 정보 저장
    public void setItem(Item item) {
        this.item = item;
         //게시글에 현재 파일이 존재하지 않는다면
        if (!item.getPhotos().contains(this))
            // 파일 추가
            item.getPhotos().add(this);
    }
}
