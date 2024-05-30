package com.dats.datsconnexion.Services;

import com.dats.datsconnexion.Repositories.ColumnsRepo;
import com.dats.datsconnexion.entities.Columns;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class ColumnsServiceImpl implements ColumnsService{

    private final ColumnsRepo columnsRepo;

    public ColumnsServiceImpl(ColumnsRepo columnsRepo) {
        this.columnsRepo = columnsRepo;
    }

    @Override
    public List<Columns> getColumns() {
        return (List<Columns>) columnsRepo.findAll();
    }

    @Override
    public Columns getColumnById(Long ColumnId) {
        return columnsRepo.findById(ColumnId).get();
    }

    @Override
    public Columns addColumn(Columns column) {
        return columnsRepo.save(column);
    }

    @Override
    public void updateColumn(Long ColumnId, Columns updatedColumn) {
        Columns techStock= columnsRepo.findById(ColumnId).orElseThrow(
                ()-> new IllegalStateException("cette colonne de stockage n'existe pas"));
        techStock.setType(updatedColumn.getType());
        techStock.setLabel(updatedColumn.getLabel());
        techStock.setSyno(updatedColumn.getSyno());
        techStock.setTname(updatedColumn.getTname());
        columnsRepo.save(updatedColumn);

    }

    @Override
    public void deleteColumn(Long tabId) {
        columnsRepo.deleteById(tabId);

    }

    @Override
    public List<Columns> rechercheParNomNatTable(String tableNatName) {
        return (List<Columns>) columnsRepo.findColumnsByTableNatName(tableNatName);
    }

    @Override
    public List<Columns> rechercheParNomTechTable(String tableTechName) {
        return (List<Columns>) columnsRepo.findColumnsByTableTechName(tableTechName);
    }

    @Override
    public List<Columns> rechercheParSynonyme(String Synonyme) {
        return (List<Columns>) columnsRepo.findColumnsBySynoContaining(Synonyme);
    }

    @Override
    public List<Columns> rechercheParLabel(String label) {
        return (List<Columns>) columnsRepo.findColumnsByLabelName(label);
    }
}
