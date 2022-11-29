package kr.inhatc.spring.item.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.inhatc.spring.item.dto.ItemSearchDto;
import kr.inhatc.spring.item.entity.Item;

public interface ItemRepositoryCustom {
    
    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
