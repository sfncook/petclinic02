package com.sfncook.petclinic02.controllers;

import com.sfncook.petclinic02.models.Vet;
import com.sfncook.petclinic02.repositories.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = {"/vets","/vets/"})
public class VetController {
    private VetRepository vetRepository;

    @Autowired
    public VetController(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.GET, consumes = "application/json", produces="application/json")
    public ResponseEntity<List<Vet>> getAll() {
        List<Vet> vets = new ArrayList<>();
        for(Vet vet : vetRepository.findAll()) {
            vets.add(vet);
        }
        return new ResponseEntity<>(vets, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = {"","/"}, method = RequestMethod.POST, consumes = "application/json", produces="application/json")
    public ResponseEntity<Vet> create(@RequestBody Vet vet) {
        vet.setId(null); // Necessary so repo doesn't think we're trying to *update* an obj
        Vet rsp = vetRepository.save(vet);
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = {"","/"}, method = RequestMethod.PUT, consumes = "application/json", produces="application/json")
    public ResponseEntity<Vet> update(@RequestBody Vet vet) {
        Vet rsp = vetRepository.save(vet);
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = {"","/"}, method = RequestMethod.DELETE, consumes = "application/json")
    public ResponseEntity delete(@RequestBody Vet vet) {
        vetRepository.delete(vet);
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = {"{id:[\\d]+}","/{id:[\\d]+}"}, method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        vetRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
