package com.sfncook.petclinic02.controllers;

import com.sfncook.petclinic02.models.Pet;
import com.sfncook.petclinic02.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = {"/pets","/pets/"})
public class PetController {
    private PetRepository petRepository;

    @Autowired
    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.GET, consumes = "application/json", produces="application/json")
    public ResponseEntity<List<Pet>> getAll() {
        List<Pet> pets = new ArrayList<>();
        for(Pet pet : petRepository.findAll()) {
            pets.add(pet);
        }
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.POST, consumes = "application/json", produces="application/json")
    public ResponseEntity<Pet> create(@RequestBody Pet pet) {
        pet.setId(null); // Necessary so repo doesn't think we're trying to *update* an obj
        Pet rsp = petRepository.save(pet);
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.PUT, consumes = "application/json", produces="application/json")
    public ResponseEntity<Pet> update(@RequestBody Pet pet) {
        Pet rsp = petRepository.save(pet);
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.DELETE, consumes = "application/json")
    public ResponseEntity delete(@RequestBody Pet pet) {
        petRepository.delete(pet);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = {"{id:[\\d]+}","/{id:[\\d]+}"}, method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        petRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
