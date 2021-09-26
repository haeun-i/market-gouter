package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.Item;
import springstudy.spring.domain.ItemPhoto;
import springstudy.spring.repository.ItemPhotoRepository;
import springstudy.spring.repository.ItemRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemPhotoService {
    private final ItemRepository itemRepository;
    private final ItemPhotoRepository itemPhotoRepository;

    // create
    public Long saveItemPhoto(Long itemId , String origName, String filePath, Long size ){
        Item item = itemRepository.findOne(itemId);
        ItemPhoto itemPhoto = new ItemPhoto();
        itemPhoto.setItem(item);
        itemPhoto.setOrigFileName(origName);
        itemPhoto.setFilePath(filePath);
        itemPhoto.setFileSize(size);

        itemPhotoRepository.save(itemPhoto);
        return itemPhoto.getId();
    }

    // Read One
    public ItemPhoto getItemPhoto(Long itemPhotoId){
        return itemPhotoRepository.findOne(itemPhotoId);
    }

    // Read All
    public List<ItemPhoto> getItemPhotos(Long itemId){
        return itemPhotoRepository.findAll(itemId);
    }
}
