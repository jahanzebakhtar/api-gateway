package com.microservice.gateway.controller;

import com.microservice.gateway.config.JwtTokenUtil;
import com.microservice.gateway.model.JwtRequest;
import com.microservice.gateway.service.JwtUserDetailsService;
import com.microservice.gateway.service.PersonServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JwtFeignStorageController {
    @Autowired
    PersonServiceProxy personServiceProxy;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() throws Exception {
            return ResponseEntity.ok(personServiceProxy.findAll());
    }
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public Object findById(@PathVariable("id") long id) throws Exception {
        return personServiceProxy.findById(id);
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(@RequestBody Object person) throws Exception {
        return personServiceProxy.save(person);
    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable("id") long id,
                                         @RequestBody Object person) {
        return personServiceProxy.update(id, person);
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        return personServiceProxy.delete(id);
    }
    @RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
    public void deleteAll() throws Exception {
        personServiceProxy.deleteAll();
    }
    @RequestMapping(value = "/person/saveAll", method = RequestMethod.POST)
    public List<Object> saveAll(@RequestBody Iterable<Object> persons) throws Exception {
        return personServiceProxy.saveAll(persons);
    }
}
