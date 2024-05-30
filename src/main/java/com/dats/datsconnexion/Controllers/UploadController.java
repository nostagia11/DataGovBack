package com.dats.datsconnexion.Controllers;


import com.dats.datsconnexion.Services.uploadFile;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(path="api/v1/upload")
public class UploadController {

    private uploadFile uploadFile;




    @PostMapping("/uploadfiles")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> multipartFiles){
        try {
             uploadFile.uploadFiles(multipartFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> uploadedFileNames = multipartFiles.stream()
                .map(MultipartFile::getOriginalFilename)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(uploadedFileNames);

    }
}
