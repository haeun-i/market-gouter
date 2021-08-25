package springstudy.spring.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import springstudy.spring.dto.GalleryDto;
import springstudy.spring.service.GalleryService;
import springstudy.spring.service.S3Service;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class GalleryController {
    private S3Service s3Service;
    private GalleryService galleryService;

    @GetMapping("/gallery")
    public String dispWrite(Model model){
        List<GalleryDto> galleryDtoList = galleryService.getList();

        model.addAttribute("galleryList", galleryDtoList);

        return "/gallery";
    }

    @PostMapping("/gallery")
    public String execWrite(GalleryDto galleryDto, MultipartFile file) throws IOException {
        String imgPath = s3Service.upload(galleryDto.getFilePath(),file);
        // galleryDto의 filePath가 할당되지 않아 에러
        galleryDto.setFilePath(imgPath);

        galleryService.savePost(galleryDto); // DB에 데이터를 조작하기 위함

        return "redirect:/gallery";
    }


}