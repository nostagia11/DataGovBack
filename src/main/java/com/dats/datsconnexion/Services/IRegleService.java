package com.dats.datsconnexion.Services;

import  com.dats.datsconnexion.entities.Regle;

import java.util.List;

public interface IRegleService {

        Regle saveRegle(Regle regle);

    Regle getRegle(Long id);
    List<Regle> getRegleLoi(Long id);

        List<Regle> getAllRegles();

    void deleteRegle(Long id);

    void updateRegle(Regle regle, Long id);
    }

