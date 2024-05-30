package com.dats.datsconnexion.Controllers;


import com.dats.datsconnexion.Services.ColumnsService;
import com.dats.datsconnexion.Services.LabelService;
import com.dats.datsconnexion.entities.Columns;
import com.dats.datsconnexion.entities.Label;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/labels")
public class LabelController {

    private LabelService labelService;



    @GetMapping

    public List<Label> getLabels(){
        return  labelService.getLabels();

    }
    @GetMapping("{labId}")
    public Label getLabel(@PathVariable("labId") Long labId){
        return labelService.getLabelById(labId);
    }




    @PostMapping("/savelabel")

    public void createLabel(@RequestBody Label label){
        labelService.addLabel(label);
    }

    @PutMapping(path = "{labId}")
    public void updateLabel(@PathVariable("labId") Long labId,
                             @RequestBody Label LabelUpdate) {
        labelService.updateLabel(labId,LabelUpdate);

    }


    @DeleteMapping(path = "{labId}")
    public void deleteLabel(@PathVariable("labId")Long labId){
        labelService.deleteLabel(labId);
    }

}
