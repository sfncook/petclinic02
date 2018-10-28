package com.sfncook.petclinic02.controllers;

import com.sfncook.petclinic02.models.Owner;
import com.sfncook.petclinic02.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = {"/owners","/owners/"})
public class OwnerController {
    private OwnerRepository ownerRepository;

    @Autowired
    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.GET, consumes = "application/json", produces="application/json")
    public ResponseEntity<List<Owner>> getAll() {
        List<Owner> owners = new ArrayList<>();
        for(Owner owner : ownerRepository.findAll()) {
            owners.add(owner);
        }
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.POST, consumes = "application/json", produces="application/json")
    public ResponseEntity<Owner> create(@RequestBody Owner owner) {
        owner.setId(null); // Necessary so repo doesn't think we're trying to *update* an obj
        Owner rsp = ownerRepository.save(owner);
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.PUT, consumes = "application/json", produces="application/json")
    public ResponseEntity<Owner> update(@RequestBody Owner owner) {
        Owner rsp = ownerRepository.save(owner);
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.DELETE, consumes = "application/json")
    public ResponseEntity delete(@RequestBody Owner owner) {
        ownerRepository.delete(owner);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = {"{id:[\\d]+}","/{id:[\\d]+}"}, method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        ownerRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
