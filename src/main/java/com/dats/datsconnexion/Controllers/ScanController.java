package com.dats.datsconnexion.Controllers;


import com.dats.datsconnexion.Services.ExcelRead;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping(path="api/v1/scans")
public class ScanController {

    private ExcelRead excelRead;


    @PostMapping("/scanExcel")
    public void scanExcelFiles(@RequestPart(value = "file") MultipartFile file){
        try {
            excelRead.scanExcel(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
