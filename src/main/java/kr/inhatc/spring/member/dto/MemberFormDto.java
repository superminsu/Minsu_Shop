package kr.inhatc.spring.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberFormDto {
    
    @NotBlank(message = "이름은 필수 값입니다.")
    private String name;
    
    @NotBlank(message = "이메일은 필수 값입니다.")
    @Email(message = "이메일 형식으로 입력하세요.")
    private String email;
    
    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Length(min=4, max=12, message ="비밀번호는 4자 이상 12자 이하로 입력하세요.")
    private String password;
    
    @NotBlank(message = "주소는 필수 입력값입니다.")
    private String address;
}
