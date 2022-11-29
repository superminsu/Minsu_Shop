package kr.inhatc.spring.utils.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@EntityListeners(value = {AuditingEntityListener.class})    //해당 클래스에 Auditing 기능을 포함
@MappedSuperclass   //JPA Entity 클래스들이 해당 추상 클래스를 상속할 경우 regTime, updateTine을 컬럼으로 인식
@Getter
@Setter
public abstract class BaseTimeEntity {

    @CreatedDate                    //생성한 시간 등록
    @Column(updatable = false)      //변경 불가능
    private LocalDateTime regTime;
    
    @LastModifiedDate               //변경한 시간 등록
    private LocalDateTime updateTime;
}
