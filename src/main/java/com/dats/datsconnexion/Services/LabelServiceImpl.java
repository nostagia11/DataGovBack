package com.dats.datsconnexion.Services;

import com.dats.datsconnexion.Repositories.LabelRepo;
import com.dats.datsconnexion.entities.Label;
import com.dats.datsconnexion.entities.Tables;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LabelServiceImpl implements LabelService{

    private final LabelRepo labelRepo;

    public LabelServiceImpl(LabelRepo labelRepo) {
        this.labelRepo = labelRepo;
    }

    @Override
    public List<Label> getLabels() {
        return (List<Label>) labelRepo.findAll();
    }

    @Override
    public Label getLabelById(Long labelId) {
        return labelRepo.findById(labelId).get();
    }

    @Override
    public Label addLabel(Label techStock) {
        return labelRepo.save(techStock);
    }

    @Override
    public void updateLabel(Long labelId, Label updatedLabel) {
        Label label= labelRepo.findById(labelId).orElseThrow(
                ()-> new IllegalStateException("ce label  n'existe pas"));
        label.setType(updatedLabel.getType());
        label.setDescription(updatedLabel.getDescription());
        label.setName(updatedLabel.getName());
        label.setType(updatedLabel.getType());
        labelRepo.save(updatedLabel);

    }

    @Override
    public void deleteLabel(Long labelId) {
        labelRepo.deleteById(labelId);

    }
}
