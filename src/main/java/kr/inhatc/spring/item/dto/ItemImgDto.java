package kr.inhatc.spring.item.dto;

import org.modelmapper.ModelMapper;

import kr.inhatc.spring.item.entity.ItemImg;

public class ItemImgDto {

    private Long id;
    
    private String imgName;
    
    private String oriName;
    
    private String imgUrl;
    
    private String repImgYn;
    
    private static ModelMapper modelMapper = new ModelMapper();
    
    public static ItemImgDto of(ItemImg itemImg) {
        //DTO -> ENTITY
        return modelMapper.map(itemImg, ItemImgDto.class);
    }
}
