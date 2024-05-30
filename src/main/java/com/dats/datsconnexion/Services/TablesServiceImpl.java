package com.dats.datsconnexion.Services;

import com.dats.datsconnexion.Repositories.TableRepo;
import com.dats.datsconnexion.entities.Tables;
import org.springframework.stereotype.Service;
import java.util.List;


@Service

public class TablesServiceImpl implements TableService{

    private final TableRepo tableRepo;

    public TablesServiceImpl(TableRepo tableRepo) {
        this.tableRepo = tableRepo;
    }

    @Override
    public List<Tables> getTables() {
        return (List<Tables>) tableRepo.findAll();
    }

    @Override
    public Tables getTableById(Long tabId) {
        return tableRepo.findById(tabId).get();
    }

    @Override
    public Tables addTable(Tables table) {
        return tableRepo.save(table);
    }

    @Override
    public void updateTable(Long tabId, Tables updatedTable) {
        Tables table= tableRepo.findById(tabId).orElseThrow(
                ()-> new IllegalStateException("cette table  n'existe pas"));
        table.setType(updatedTable.getType());
        table.setFormat(updatedTable.getFormat());
        table.setTechName(updatedTable.getTechName());
        table.setNatName(updatedTable.getNatName());
        tableRepo.save(updatedTable);

    }

    @Override
    public void deleteTable(Long tabId) {
        tableRepo.deleteById(tabId);

    }

    @Override
    public List<Tables> rechercheParNomNaturel(String nomNatTable) {
        return (List<Tables>) tableRepo.findTablesByNatNameContaining(nomNatTable);
    }

    @Override
    public List<Tables> rechercheParNomTech(String nomTechTable) {
        return (List<Tables>) tableRepo.findTablesByTechNameContaining(nomTechTable);
    }
}
