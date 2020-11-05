package com.aeon.stoomapiaddress.controller;

import com.aeon.stoomapiaddress.business.AddressService;
import com.aeon.stoomapiaddress.exception.ResourceDefaultException;
import com.aeon.stoomapiaddress.model.Address;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(value = "/api/register/address")
public class CrudAddressController {

    @Autowired
    private AddressService service;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<?> postAddress(@Valid @RequestBody Address address) throws InterruptedException, ApiException, IOException {
        Address newAddress = service.addAddress(address);
        return new ResponseEntity<>(HttpStatus.CREATED).accepted().body(newAddress);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> getAddressAll(){
        return  new ResponseEntity<>(service.listAllAddress(), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public ResponseEntity<?> putAddress(@Valid @RequestBody Address address) throws IOException {
        Address newAddress = service.updateAddress(address);
        return new ResponseEntity<>(HttpStatus.OK).accepted().body(newAddress);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAddress(@Valid @RequestParam("id") String id){
        service.deleteAddress(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.ACCEPTED).accepted().body(service.listAllAddress());
    }



}
