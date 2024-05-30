package com.dats.datsconnexion.Services;


import com.dats.datsconnexion.Repositories.SoftwareTechRepo;
import com.dats.datsconnexion.entities.SoftwareSolution;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SoftwareServiceImpl implements SoftwareSoluService{

    private final SoftwareTechRepo softwareTechRepo ;

    public SoftwareServiceImpl(SoftwareTechRepo softwareTechRepo) {
        this.softwareTechRepo = softwareTechRepo;
    }


    @Override
    public List<SoftwareSolution> getSoftwares(){
       return (List<SoftwareSolution>) softwareTechRepo.findAll();
    }

    @Override
    public SoftwareSolution getSoftwareById(Long sol_id ){
        return softwareTechRepo.findById(sol_id).get();
    }
    @Override
    public SoftwareSolution addSoftwareSolu(SoftwareSolution solution){
        return  softwareTechRepo.save(solution);
    }
    @Override
    public void updateSoftware(Long solId, SoftwareSolution updatedSolution){
        SoftwareSolution solution= softwareTechRepo.findById(solId).orElseThrow(
                ()->new IllegalStateException("cette solution n'existe pas")
        );
        solution.setType(updatedSolution.getType());
        solution.setProvider(updatedSolution.getProvider());
        solution.setCostType(updatedSolution.getCostType());
       softwareTechRepo.save(updatedSolution);
        }









    public void deleteSoftware(Long sol_id){
        softwareTechRepo.deleteById(sol_id);
    }

    @Override
    public SoftwareSolution rechercheParNomSolution(String nomSolution) {
        return softwareTechRepo.findSoftwareSolutionByNameS(nomSolution);
    }

    @Override
    public List<SoftwareSolution> rechercheParTypeSolution(String typeSolu) {
        return (List<SoftwareSolution>) softwareTechRepo.findSoftwareSolutionByType(typeSolu);
    }


}
