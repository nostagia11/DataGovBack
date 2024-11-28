package com.dats.datsconnexion.Controllers;

import com.dats.datsconnexion.entities.Loi;
import com.dats.datsconnexion.Services.ILoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/Loi")
public class LoiController {
    @Autowired
    ILoiService iLoiService;

    @GetMapping("/{id}")
    public Loi getLoi(@PathVariable Long id) {
        return iLoiService.getLoi(id);
    }
    @GetMapping("/Doc/{id}")
    public List<Loi>  getLoiByDoc(@PathVariable Long id) {
        return iLoiService.getLoidoc(id);
    }
    @GetMapping
    public List<Loi> getAllLois() {

        return iLoiService.getAllLois();
    }
    @GetMapping("/last")
    public List<Loi> getLastAddedDocumentation() {
        return iLoiService.getLastAddedDocumentation();
    }
    @PutMapping("/{id}")
    public Void updateLoi(@RequestBody Loi Loi, @PathVariable Long id) {
        iLoiService.updateLoi(Loi, id);

        return null;
    }

    @DeleteMapping("/{id}")
    public Void deleteLoi(@PathVariable Long id) {
        iLoiService.deleteLoi(id);
        return null;

    }
}
