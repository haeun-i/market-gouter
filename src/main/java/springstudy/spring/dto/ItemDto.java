package springstudy.spring.dto;

import lombok.*;
import springstudy.spring.domain.Item;

import java.util.List;

@Getter @Setter
@ToString
@NoArgsConstructor
@Builder
public class ItemDto {
    private Long id;
    private String name;
    private String itemName;
    private byte[] itemImage;
    private String  itemCategory;
    private String itemQuantity;
    private String itemFrom;
    private String itemIntro;
    private Long itemPrice;
    private String itemDescription;
    private List<String> itemOptions;

    // Dto 에서 필요한 부분을 빌더 패턴을 통해 Entity화
    // 추후 수정 필요!
    public Item toEntity(){
        Item build = Item.builder()
                .id(id)
                .itemName(itemName)
                .itemFrom(itemFrom)
                .itemQuantity(itemQuantity)
                .itemIntro(itemIntro)
                .itemPrice(itemPrice)
                .itemDescription(itemDescription)
                .build();
        return build;
    }
}
