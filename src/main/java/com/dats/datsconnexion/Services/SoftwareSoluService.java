package com.dats.datsconnexion.Services;

import com.dats.datsconnexion.entities.SoftwareSolution;

import java.util.List;

public interface SoftwareSoluService {
    public List<SoftwareSolution> getSoftwares();
    public SoftwareSolution getSoftwareById(Long sol_id );
    public SoftwareSolution addSoftwareSolu(SoftwareSolution solution);


    void updateSoftware(Long solId, SoftwareSolution updatedSolution);

    public void deleteSoftware(Long sol_id);

    SoftwareSolution rechercheParNomSolution(String nomSolution);
    List<SoftwareSolution> rechercheParTypeSolution(String typeSolu);

}
