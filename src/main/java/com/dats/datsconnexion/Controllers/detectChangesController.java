package com.dats.datsconnexion.Controllers;


import com.dats.datsconnexion.Services.Excelchanges;
import com.dats.datsconnexion.entities.SoftwareSolution;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(path="api/v1/detectChanges")
public class detectChangesController {


    private Excelchanges excelchanges;


    @GetMapping(path = "{soluId}")
    public Map<String,String> viewExcelLogs(@PathVariable("soluId") Long soluId){
        Map<String,String> logs = null;
        try {
            logs = excelchanges.watchExcelFileChanges(soluId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logs;
    }
}
