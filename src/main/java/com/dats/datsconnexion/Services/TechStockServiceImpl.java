package com.dats.datsconnexion.Services;


import com.dats.datsconnexion.Repositories.TechStockRepo;
import com.dats.datsconnexion.entities.TechStock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TechStockServiceImpl implements TechStockService {

    private final TechStockRepo stockRepo;

    public TechStockServiceImpl(TechStockRepo stockRepo) {
        this.stockRepo = stockRepo;
    }


    @Override
    public List<TechStock> getTechStocks() {
        return (List<TechStock>) stockRepo.findAll();
    }

    @Override
    public TechStock getTechStockById(Long techId) {
        return stockRepo.findById(techId).get();
    }

    @Override
    public TechStock addTechStock(TechStock techStock) {
        return stockRepo.save(techStock);
    }

    @Override
    public void updateTechStock(Long techId, TechStock updatedTech) {
        TechStock techStock= stockRepo.findById(techId).orElseThrow(
                ()-> new IllegalStateException("cette technique de stockage n'existe pas"));
        techStock.setType(updatedTech.getType());
        techStock.setConnMethod(updatedTech.getConnMethod());
        techStock.setInfrastructure(updatedTech.getInfrastructure());
        techStock.setLocal(updatedTech.getLocal());
        techStock.setUrl(updatedTech.getUrl());
        stockRepo.save(updatedTech);

    }

    @Override
    public void deleteTechStock(Long techId) {
        stockRepo.deleteById(techId);

    }
}
