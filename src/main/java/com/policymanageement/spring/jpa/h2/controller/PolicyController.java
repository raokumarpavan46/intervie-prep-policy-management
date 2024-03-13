package com.policymanageement.spring.jpa.h2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.policymanageement.spring.jpa.h2.service.IPolicyService;
import com.policymanagement.spring.jpa.h2.model.Policy;

@RestController
@RequestMapping("/api")
public class PolicyController {

	private IPolicyService policyService;

	public PolicyController(IPolicyService policyService) {
		this.policyService = policyService;
	}

	@GetMapping("/policies")
	public ResponseEntity<List<Policy>> getAllPolicies(@RequestParam(required = false) String title) {

		List<Policy> policies = policyService.getAllPolicies(title);
		return new ResponseEntity<>(policies, HttpStatus.OK);
	}

	@GetMapping("/policy/{id}")
	public ResponseEntity<Policy> getPolicyById(@PathVariable("id") long id) {

		Policy policy = policyService.getPolicyById(id);
		return new ResponseEntity<>(policy, HttpStatus.OK);

	}

	@PostMapping("/policies")
	public ResponseEntity<Policy> createPolicy(@RequestBody Policy policyRequest) {
		Policy policy = policyService.createPolicy(policyRequest);
		return new ResponseEntity<>(policy, HttpStatus.CREATED);
	}

	@PutMapping("/policy/{id}")
	public ResponseEntity<Policy> updatePolicy(@PathVariable("id") long id, @RequestBody Policy policyRequest) {
		Policy policyData = policyService.updatePolicy(id, policyRequest);
		return new ResponseEntity<>(policyData, HttpStatus.OK);
	}

	@DeleteMapping("/policy/{id}")
	public ResponseEntity<HttpStatus> deletePolicy(@PathVariable("id") long id) {
		HttpStatus staus = policyService.deletePolicy(id);
		return new ResponseEntity<>(staus);
	}

	@DeleteMapping("/policies")
	public ResponseEntity<HttpStatus> deleteAllPolicies() {
		HttpStatus status = policyService.deleteAllPolicies();
		return new ResponseEntity<>(status);
	}

	@GetMapping("/policies/published")
	public ResponseEntity<List<Policy>> findByPublished() {
		List<Policy> policies = policyService.findByPublished();
		return new ResponseEntity<>(policies, HttpStatus.OK);

	}

}
