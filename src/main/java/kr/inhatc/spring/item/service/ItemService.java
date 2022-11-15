package kr.inhatc.spring.item.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.inhatc.spring.item.dto.ItemFormDto;
import kr.inhatc.spring.item.entity.Item;
import kr.inhatc.spring.item.entity.ItemImg;
import kr.inhatc.spring.item.repository.ItemImgRepository;
import kr.inhatc.spring.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    
    private final ItemImgService itemImgService;
    
    private final ItemImgRepository itemImgRepository;
    
    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws IOException {
        
        Item item = itemFormDto.createItem();
        itemRepository.save(item);
        
        for (int i = 0; i < itemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);  //item(PK) JOIN itemImg(FK)
            
            if(i == 0) {
                itemImg.setRepImgYn("Y");
            }
            else {
                itemImg.setRepImgYn("N");
            }

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(0));
        }
        
        return item.getId();
    }
}
