package tn.esprit.autoloc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.InterfaceEmployeService;

import java.util.List;

@RestController
@RequestMapping("/employes")
public class EmployeController {

    @Autowired
    private InterfaceEmployeService employeService;

    @GetMapping
    public List<Employe> retrieveAllEmployes() {
        return employeService.retrieveAllEmployes();
    }

    @GetMapping("/{id}")
    public Employe retrieveEmploye(@PathVariable("id") Long idEmploye) {
        return employeService.retrieveEmploye(idEmploye);
    }

    @PostMapping
    public Employe addEmploye(@RequestBody Employe employe) {
        return employeService.addEmploye(employe);
    }

    @PostMapping("/batch")
    public List<Employe> addEmployes(@RequestBody List<Employe> employes) {
        return employeService.addEmployes(employes);
    }

    @PutMapping
    public Employe updateEmploye(@RequestBody Employe employe) {
        return employeService.updateEmploye(employe);
    }

    @DeleteMapping("/{id}")
    public void removeEmploye(@PathVariable("id") Long idEmploye) {
        employeService.removeEmploye(idEmploye);
    }
}
