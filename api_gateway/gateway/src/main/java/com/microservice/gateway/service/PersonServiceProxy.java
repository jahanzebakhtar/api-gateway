package com.microservice.gateway.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "storage")
public interface PersonServiceProxy {
    @RequestMapping(value = "/person/findAll", method = RequestMethod.GET)
    public List<Object> findAll() throws Exception;
    @RequestMapping(value = "/person/findById/{id}", method = RequestMethod.GET)
    public Object findById(@PathVariable("id") long id) throws Exception;
    @RequestMapping(value = "/person/save", method = RequestMethod.POST)
    public Object save(@RequestBody Object person) throws Exception;
    @RequestMapping(value = "/person/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable("id") long id,
                                         @RequestBody Object person);
    @RequestMapping(value = "/person/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") long id);
    @RequestMapping(value = "/person/deleteAll", method = RequestMethod.DELETE)
    public void deleteAll() throws Exception;
    @RequestMapping(value = "/person/saveAll", method = RequestMethod.POST)
    public List<Object> saveAll(@RequestBody Iterable<Object> persons) throws Exception;
}
