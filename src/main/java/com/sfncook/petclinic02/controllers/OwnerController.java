package com.sfncook.petclinic02.controllers;

import com.sfncook.petclinic02.models.Owner;
import com.sfncook.petclinic02.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = {"/owners","/owners/"})
public class OwnerController {
    @Autowired
    private OwnerRepository ownerRepository;

    @RequestMapping(value = {"","/"}, method = RequestMethod.GET, consumes = "application/json", produces="application/json")
    public ResponseEntity<List<Owner>> getAll() {
        List<Owner> owners = new ArrayList<Owner>();
        for(Owner owner : ownerRepository.findAll()) {
            owners.add(owner);
        }
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.POST, consumes = "application/json", produces="application/json")
    public ResponseEntity<Owner> create(@RequestBody Owner newOwner) {
        newOwner.setId(null); // Necessary so repo doesn't think we're trying to *update* an obj
        Owner owner = ownerRepository.save(newOwner);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }
}
