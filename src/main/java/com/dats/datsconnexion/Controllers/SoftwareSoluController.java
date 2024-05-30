package com.dats.datsconnexion.Controllers;


import com.dats.datsconnexion.Services.SoftwareSoluService;
import com.dats.datsconnexion.entities.Columns;
import com.dats.datsconnexion.entities.SoftwareSolution;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path="api/v1/softwaresolu")
public class SoftwareSoluController {

    private SoftwareSoluService soluService;



    @GetMapping

    public List<SoftwareSolution> getSoftwares(){
        return  soluService.getSoftwares();

    }
    @GetMapping("{soluId}")
    public SoftwareSolution getSoftawreSolu(@PathVariable("soluId") Long soluId){
        return soluService.getSoftwareById(soluId);
    }

    @GetMapping("/RNomSolu")
    public SoftwareSolution rechercheParNomSolu(@RequestParam String NomSolu){
        return  soluService.rechercheParNomSolution(NomSolu);

    }
    @GetMapping("/RType")
    public List<SoftwareSolution> rechercheParTypeSolu(@RequestParam String typeSolu){
        return  soluService.rechercheParTypeSolution(typeSolu);

    }

    @PostMapping("/savesolution")

    public void createSoftawreSolu(@RequestBody SoftwareSolution solution){
        soluService.addSoftwareSolu(solution);
    }

    @PutMapping(path = "{soluId}")
    public void updateSoftawreSolu(@PathVariable("soluId") Long soluId,
                            @RequestBody SoftwareSolution solutionupdate){
        soluService.updateSoftware(soluId,solutionupdate);

    }


    @DeleteMapping(path = "{soluId}")
    public void deleteSoftwareSolu(@PathVariable("soluId")Long soluId){
        soluService.deleteSoftware(soluId);
    }


}
