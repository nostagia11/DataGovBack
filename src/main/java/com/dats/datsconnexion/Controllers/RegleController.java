package com.dats.datsconnexion.Controllers;

import com.dats.datsconnexion.entities.Regle;
import com.dats.datsconnexion.Services.IRegleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/Regle")
public class RegleController {
    @Autowired
    IRegleService iRegleService;

    @PostMapping
    public void addDoc(@RequestBody Regle doc) {
        iRegleService.saveRegle(doc);
    }

    @GetMapping("/{id}")
    public Regle getRegle(@PathVariable Long id) {
        return iRegleService.getRegle(id);

    }
    @GetMapping("/loi/{id}")
    public List<Regle> getRegleLoi(@PathVariable Long id) {
        return iRegleService.getRegleLoi(id);

    }

    @GetMapping
    public List<Regle> getAllRegles() {

        return iRegleService.getAllRegles();
    }

    @PutMapping("/{id}")
    public Void updateRegle(@RequestBody Regle Regle, @PathVariable Long id) {
        iRegleService.updateRegle(Regle, id);

        return null;
    }

    @DeleteMapping("/{id}")
    public Void deleteRegle(@PathVariable Long id) {
        iRegleService.deleteRegle(id);
        return null;

    }
}
