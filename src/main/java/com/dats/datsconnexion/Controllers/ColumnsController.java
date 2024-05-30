package com.dats.datsconnexion.Controllers;


import com.dats.datsconnexion.Services.ColumnsService;
import com.dats.datsconnexion.Services.SoftwareSoluService;
import com.dats.datsconnexion.entities.Columns;
import com.dats.datsconnexion.entities.SoftwareSolution;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path="api/v1/columns")
public class ColumnsController {

    private ColumnsService columnsService;



    @GetMapping

    public List<Columns> getColumns(){
        return  columnsService.getColumns();

    }
    @GetMapping("{colId}")
    public Columns getColumn(@PathVariable("colId") Long colId){
        return columnsService.getColumnById(colId);
    }

    @GetMapping("/RNomNatTable")
    public List<Columns> rechercheParNomNatTable(@RequestParam String tableNatName){
        return  columnsService.rechercheParNomNatTable(tableNatName);

    }

    @GetMapping("/RNomTechTable")
    public List<Columns> rechercheParNomTechTable(@RequestParam String tableTechName){
        return  columnsService.rechercheParNomTechTable(tableTechName);

    }

    @GetMapping("/RSyno")
    public List<Columns> rechercheParSyno(@RequestParam String synonyme){
        return  columnsService.rechercheParSynonyme(synonyme);

    }
    @GetMapping("/RLabel")
    public List<Columns> rechercheParLabel(@RequestParam String Label){
        return  columnsService.rechercheParLabel(Label);

    }

    @PostMapping("/savecolumn")

    public void createColumn(@RequestBody Columns column){
        columnsService.addColumn(column);
    }

    @PutMapping(path = "{colId}")
    public void updateColumn(@PathVariable("colId") Long colId,
                                   @RequestBody Columns columnUpdate){
        columnsService.updateColumn(colId,columnUpdate);

    }


    @DeleteMapping(path = "{colId}")
    public void deleteColumn(@PathVariable("colId")Long colId){
        columnsService.deleteColumn(colId);
    }

}
