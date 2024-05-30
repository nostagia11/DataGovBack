package com.dats.datsconnexion.Services;

import com.dats.datsconnexion.entities.Columns;
import com.dats.datsconnexion.entities.Tables;

import java.util.List;

public interface ColumnsService {

     List<Columns> getColumns();
     Columns getColumnById(Long ColumnId );
     Columns addColumn(Columns column);


    void updateColumn(Long ColumnId, Columns updatedColumn);

     void deleteColumn(Long tabId);

     List<Columns> rechercheParNomNatTable(String tableNatName);
    List<Columns> rechercheParNomTechTable(String tableTechName);

    List<Columns> rechercheParSynonyme(String Synonyme);
    List<Columns> rechercheParLabel(String label);


}
