package com.dats.datsconnexion.Services;

import com.dats.datsconnexion.entities.Label;
import com.dats.datsconnexion.entities.TechStock;

import java.util.List;

public interface LabelService {

    public List<Label> getLabels();
    public Label getLabelById(Long labelId );
    public Label addLabel(Label techStock);


    void updateLabel(Long labelId, Label updatedLabel);

    public void deleteLabel(Long labelId);
}
