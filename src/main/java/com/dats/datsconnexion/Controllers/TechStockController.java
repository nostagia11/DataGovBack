package com.dats.datsconnexion.Controllers;


import com.dats.datsconnexion.Services.SoftwareSoluService;
import com.dats.datsconnexion.Services.TechStockService;
import com.dats.datsconnexion.entities.SoftwareSolution;
import com.dats.datsconnexion.entities.TechStock;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path="api/v1/techstock")
public class TechStockController {

    private TechStockService techService;



    @GetMapping

    public List<TechStock> getSoftwares(){
        return  techService.getTechStocks();

    }
    @GetMapping("{techId}")
    public TechStock getTechStock(@PathVariable("techId") Long techId){
        return techService.getTechStockById(techId);
    }

    @PostMapping("/savetechstock")

    public void addNewTecStock(@RequestBody TechStock techstock){
        techService.addTechStock(techstock);
    }

    @PutMapping(path = "{techId}")
    public void updateTechStock(@PathVariable("techId") Long techId,
                            @RequestBody TechStock techupdate){
        techService.updateTechStock(techId,techupdate);

    }


    @DeleteMapping(path = "{techId}")
    public void deleteTechStock(@PathVariable("techId")Long techId){
        techService.deleteTechStock(techId);
    }

}
