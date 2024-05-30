package com.dats.datsconnexion.Controllers;


import com.dats.datsconnexion.Services.SoftwareSoluService;
import com.dats.datsconnexion.Services.TableService;
import com.dats.datsconnexion.entities.SoftwareSolution;
import com.dats.datsconnexion.entities.Tables;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path="api/v1/Tables")
public class TablesController {

    private TableService tableService;



    @GetMapping

    public List<Tables> getTables(){
        return  tableService.getTables();

    }
    @GetMapping("{tabId}")
    public Tables getTable(@PathVariable("tabId") Long tabId){
        return tableService.getTableById(tabId);
    }

    @GetMapping("/RTNomNat")
    public List<Tables> rechercheParNomNaturel(@RequestParam String nomNatTable){
        return  tableService.rechercheParNomNaturel(nomNatTable);

    }

    @GetMapping("/RTNomTech")
    public List<Tables> rechercheParNomTech(@RequestParam String nomTechTable){
        return  tableService.rechercheParNomTech(nomTechTable);

    }

    @PostMapping("/savetable")

    public void addNewTable(@RequestBody Tables table){
        tableService.addTable(table);
    }

    @PutMapping(path = "{tabId}")
    public void updateTable(@PathVariable("tabId") Long tabId,
                            @RequestBody Tables tableupdate){
        tableService.updateTable(tabId,tableupdate);

    }


    @DeleteMapping(path = "{tabId}")
    public void deleteTable(@PathVariable("tabId")Long tabId){
        tableService.deleteTable(tabId);
    }


}
