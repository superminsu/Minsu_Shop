package kr.inhatc.spring.item.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j  //Log4j2보다 최신버전
public class FileService {

    public String uploadFile(String uploadPath, String oriFileName, byte[] fildData) throws IOException {
        
        UUID uuid = UUID.randomUUID();  //겹치지 않는 임의 닉네임 생성
        String extension = oriFileName.substring(oriFileName.lastIndexOf("."));
        String savedFileName = uuid.toString() + extension;
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;    //풀경로
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fildData);
        fos.close();
        return savedFileName;
    }
    
    public void deleteFile(String filePath) {
        File deleteFile = new File(filePath);
        
        if(deleteFile.exists()) {
            deleteFile.delete();
            log.info("파일 삭제 완료했습니다.");
        }
        else {
            log.info("해당 이름의 파일이 존재하지 않습니다.");
        }
    }
}
