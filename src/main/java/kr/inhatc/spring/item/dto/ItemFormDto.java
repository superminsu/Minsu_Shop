package kr.inhatc.spring.item.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import kr.inhatc.spring.item.constant.ItemSellStatus;
import kr.inhatc.spring.item.entity.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemFormDto {

    private Long id;    //상품 코드
    
    @NotBlank(message = "상품명은 필수 입력입니다.")
    private String itemName;    //상품 이름
    
    @NotNull(message = "가격은 필수 입력입니다.")
    private int price;  //상품 가격
    
    @NotNull(message = "재고는 필수 입력입니다.")
    private int stockNumber;    //재고 수량
    
    private ItemSellStatus itemSellStatus;
    
    @NotBlank(message = "상품 설명은 필수 입력입니다.")
    private String itemDetail;  //상품 상세 설명
    
    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();    //초기화
    
    private List<Long> itemImgIds = new ArrayList<>();
    
    private static ModelMapper modelMapper = new ModelMapper();
    
    public Item createItem() {
        //Dto -> Entity
        return modelMapper.map(this, Item.class);
    }
    
    public static ItemFormDto of(Item item) {
        //Entity -> Dto
        return modelMapper.map(item, ItemFormDto.class);
    }
}
