package springstudy.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springstudy.spring.domain.Item;
import springstudy.spring.dto.ItemDto;
import springstudy.spring.service.ItemService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
 
    @Autowired
    private ItemService itemService;


    // item 들어가면 기 작성된 item 엔티티들이 표시
    @GetMapping("/item")
    public String ItemList(Model model){
        List<ItemDto> itemDtoList = itemService.getItemList();
        model.addAttribute("postList", itemDtoList);
        return "item/list.html";
    } // 넘어가는 itemDtoList들의 속성을 html에서 어떻게 사용하는지 가이드라인 작성 필요

    /* Create */
    @GetMapping("/item/write")
    public String postItem(){
        return "item/postItem.html";
    } // item 작성 페이지로 이동하기

    @PostMapping("/item/write")
    public String writeItem(Item item){
        itemService.saveItem(item);
        return "redirect:/";
    } // 작성 값 넘겨주기

    /* Read One */
    @GetMapping("/item/{id}")
    public String readOne(@PathVariable("id") Long id, Model model){
        ItemDto itemDto = itemService.getItem(id);
        model.addAttribute("post", itemDto);
        return "item/readOne.html";
    } // 그냥 Item으로 수정해야할 듯?

    /* Edit */
    @GetMapping("/item/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        ItemDto itemDto = itemService.getItem(id);
        model.addAttribute("post", itemDto);
        return "item/itemEdit.html";
    }

    /* Delete */
    @GetMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id){
        itemService.deleteItem(id);
        return "redirect:/";
    }
}