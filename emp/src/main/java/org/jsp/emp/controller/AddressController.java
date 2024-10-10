package org.jsp.emp.controller;

import org.jsp.emp.entity.Address;
import org.jsp.emp.responsestructure.ResponseStructure;

//import javax.servlet.http.HttpServletReques

import org.jsp.emp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@GetMapping(value = "/address/{eid}")
	public ResponseEntity<ResponseStructure<?>> findAllAddresses(@PathVariable int eid) {
		return addressService.findAllAddresses(eid);

	}
	
	@GetMapping(value = "/address/{eid}/{aid}")
	public ResponseEntity<ResponseStructure<?>> findAddressByEid(@PathVariable int eid,@PathVariable int aid) {
		return addressService.findAddressById(eid,aid);

	}
	@PostMapping(value = "/address/{eid}")
	public ResponseEntity<ResponseStructure<?>> saveAddress(@PathVariable int eid,@RequestBody Address address) {
		return addressService.saveAddress(eid,address);
	}
	
	@DeleteMapping(value="/address/{eid}/{aid}")
	public ResponseEntity<ResponseStructure<String>> deleteAddressById(@PathVariable int eid,@PathVariable int aid){
		return addressService.deleteAddressById(eid,aid);
	}
	
	@PutMapping(value = "/address/{eid}/{aid}")
	public ResponseEntity<ResponseStructure<Object>> updateAddress(@PathVariable int eid,@PathVariable int aid,@RequestBody Address address) {
		return addressService.updateAddress(eid,aid,address);
	}
	
//	@GetMapping(value = "/addaddress")
//	public ModelAndView addAddressPage(@RequestParam int id, ModelAndView mv) {
//		return addressService.addAddressPage(id, mv);
//	}
//
//	@PostMapping(value = "/saveaddress")
//	public ModelAndView saveAddress(@RequestParam int id, ModelAndView mv, HttpServletRequest req) {
//		return addressService.saveAddress(id, req, mv);
//	}

}