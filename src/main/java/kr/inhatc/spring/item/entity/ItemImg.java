package kr.inhatc.spring.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import kr.inhatc.spring.item.constant.ItemSellStatus;
import kr.inhatc.spring.utils.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor  //디폴트 생성자 생성
public class ItemImg extends BaseEntity{        //파일(이미지) 업로드 담당

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_img_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    
    private String imgName;
    
    private String oriName;
    
    private String imgUrl;
    
    private String repImgYn;

    public void updateItemImg(String oriName, String imgUrl, String repImgYn) {
        this.oriName = oriName;
        this.imgUrl = imgUrl;
        this.repImgYn = repImgYn;
    }
}
