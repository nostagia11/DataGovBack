package com.dats.datsconnexion.Services;

import com.dats.datsconnexion.entities.TechStock;

import java.util.List;

public interface TechStockService {

    public List<TechStock> getTechStocks();
    public TechStock getTechStockById(Long techId );
    public TechStock addTechStock(TechStock techStock);


    void updateTechStock(Long techId, TechStock updatedTech);

    public void deleteTechStock(Long techId);
}
