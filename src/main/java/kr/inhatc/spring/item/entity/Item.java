package kr.inhatc.spring.item.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import kr.inhatc.spring.item.constant.ItemSellStatus;
import kr.inhatc.spring.item.dto.ItemFormDto;
import kr.inhatc.spring.utils.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.asm.Advice.Local;

/**
 * 상품을 관리하는 클래스
 * @author minsu
 *
 */

@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
@NoArgsConstructor  //디폴트 생성자 생성
@AllArgsConstructor //필드에 쓴 모든 생성자만 생성
public class Item extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long id;	//상품 코드
	
	@Column(nullable = false, length = 50)
	private String itemName;	//상품 이름
	
	@Column(nullable = false)
	private int price;	//상품 가격
	
	@Column(nullable = false)
	private int stockNumber;	//재고 수량
	
	@Enumerated(EnumType.STRING)
	private ItemSellStatus itemSellStatus;
	
	@Lob	//데이터베이스에서 VARCHAR보다 큰 데이터를 담고 싶을 때 사용한다
	@Column(nullable = false)
	private String itemDetail;	//상품 상세 설명
	
	public void updateItem(ItemFormDto itemFormDto) {      
        this.itemName = itemFormDto.getItemName();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }
	
}
