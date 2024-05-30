package com.dats.datsconnexion.Services;

import com.dats.datsconnexion.entities.SoftwareSolution;
import com.dats.datsconnexion.entities.Tables;
import com.dats.datsconnexion.entities.TechStock;

import java.util.List;

public interface TableService {

    public List<Tables> getTables();
    public Tables getTableById(Long tabId );
    public Tables addTable(Tables table);


    void updateTable(Long tabId, Tables updatedTable);

    public void deleteTable(Long tabId);
    List<Tables> rechercheParNomNaturel(String nomNatTable);
    List<Tables> rechercheParNomTech(String nomTechTable);

}
