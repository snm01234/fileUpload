package com.snm01234.fileUpload.controller;

import com.snm01234.fileUpload.dto.FileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class HomeController {

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile[] uploadfile, Model model)
            throws IllegalStateException, IOException {
        List<FileDto> list = new ArrayList<>();
        for(MultipartFile file: uploadfile) {
            if(!file.isEmpty()) {
                // UUID를 이용해 unique한 파일 이름 만들어줌
                FileDto dto = new FileDto(UUID.randomUUID().toString(),
                        file.getOriginalFilename(),
                        file.getContentType());
                list.add(dto);

                File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());
                //전달된 내용을 실제 물리적인 파일로 저장해준다.
                file.transferTo(newFileName);
            }
        }
        model.addAttribute("files", list);
        return "result";
    }
    //application.properties에 등록된 파일 경로
    @Value("${spring.servlet.multipart.location}")
    String filePath;

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@ModelAttribute FileDto dto) throws IOException {
        Path path = Paths.get(filePath + "/" + dto.getUuid() + "_" + dto.getFileName());
        String contentType = Files.probeContentType(path);
        // header를 통해서 다ㄹ운로드 되는 파일의 정보를 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(dto.getFileName(), StandardCharsets.UTF_8)
                .build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
