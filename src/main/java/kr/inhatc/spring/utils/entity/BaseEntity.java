package kr.inhatc.spring.utils.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@EntityListeners(value = {AuditingEntityListener.class})       //변경 사항 감시
@MappedSuperclass                                              //다른 클래스에서 사용 가능하게 해줌
@Getter
@Setter
public abstract class BaseEntity extends BaseTimeEntity{

    @CreatedBy                       //처음으로 생성한 사람
    @Column(updatable = false)      //변경 불가능
    private String createdBy;
    
    @LastModifiedBy                 //마지막으로 변경한 사람
    private String modifiedBy;
}
