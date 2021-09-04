package springstudy.spring.dto;

import jdk.jfr.Category;
import lombok.*;
import springstudy.spring.domain.CategoryItem;
import springstudy.spring.domain.Item;

import java.util.List;

@Getter @Setter
@ToString
@AllArgsConstructor
@Builder
public class ItemDto {
    private Long id;
    private String itemName;
    private byte[] itemImage;
    private int itemPrice;
    private String itemDescription;

    // Dto 에서 필요한 부분을 빌더 패턴을 통해 Entity화
    // 추후 수정 필요!
    public Item toEntity() {
        Item build = Item.builder()
                .id(id)
                .itemName(itemName)
                .itemPrice(itemPrice)
                .itemDescription(itemDescription)
                .build();
        return build;
    }
}