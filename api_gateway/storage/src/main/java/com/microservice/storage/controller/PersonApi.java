package com.microservice.storage.controller;

import com.microservice.storage.model.Person;
import com.microservice.storage.repository.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/person"})
public class PersonApi {
    @Autowired
    private PersonDao personDao;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Person> findAll() throws Exception {
        System.out.println("Jahanzeb");
        return personDao.findAll();
    }
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public Person findById(@PathVariable("id") long id) throws Exception {
        Optional<Person> persons = personDao.findById(id);
        if(persons.isPresent()) {
            Person person = persons.get();
            if (persons != null) {
                return person;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Person save(@RequestBody Person person) throws Exception {
        return personDao.save(person);
    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Person> update(@PathVariable("id") long id,
                                          @RequestBody Person person){
        return personDao.findById(id)
                .map(record -> {
                    record.setPersonName(person.getPersonName());
                    record.setPersonAddress(person.getPersonAddress());
                    Person updated = personDao.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return personDao.findById(id)
                .map(record -> {
                    personDao.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    @RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
    public void deleteAll() throws Exception {
        personDao.deleteAll();
    }
    @RequestMapping(value = "/saveAll", method = RequestMethod.POST)
    public List<Person> saveAll(@RequestBody Iterable<Person> persons) throws Exception {
        return personDao.saveAll(persons);
    }
}
