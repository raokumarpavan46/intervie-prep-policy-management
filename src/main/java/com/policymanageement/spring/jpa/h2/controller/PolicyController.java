package com.policymanageement.spring.jpa.h2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.policymanagement.spring.jpa.h2.model.Policy;
import com.policymanagement.spring.jpa.h2.repository.PolicyRepository;

@RestController
@RequestMapping("/api")
public class PolicyController {

  @Autowired
  PolicyRepository policyRepository;

  @GetMapping("/policies")
  public ResponseEntity<List<Policy>> getAllPolicies(@RequestParam(required = false) String title) {
    try {
      List<Policy> policies = new ArrayList<Policy>();

      if (title == null)
        policyRepository.findAll().forEach(policies::add);
      else
        policyRepository.findByNameContainingIgnoreCase(title).forEach(policies::add);

      if (policies.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(policies, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/policy/{id}")
  public ResponseEntity<Policy> getPolicyById(@PathVariable("id") long id) {
    Optional<Policy> policyData = policyRepository.findById(id);

    if (policyData.isPresent()) {
      return new ResponseEntity<>(policyData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/policies")
  public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy) {
    try {
      Policy _policy = policyRepository.save(new Policy(policy.getName(), policy.getDescription(), false));
      return new ResponseEntity<>(_policy, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/policy/{id}")
  public ResponseEntity<Policy> updatePolicy(@PathVariable("id") long id, @RequestBody Policy policy) {
    Optional<Policy> policyData = policyRepository.findById(id);

    if (policyData.isPresent()) {
      Policy _policy = policyData.get();
      _policy.setName(policy.getName());
      _policy.setDescription(policy.getDescription());
      _policy.setPublished(policy.isPublished());
      return new ResponseEntity<>(policyRepository.save(_policy), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/policy/{id}")
  public ResponseEntity<HttpStatus> deletePolicy(@PathVariable("id") long id) {
    try {
      policyRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/policies")
  public ResponseEntity<HttpStatus> deleteAllPolicies() {
    try {
      policyRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/policies/published")
  public ResponseEntity<List<Policy>> findByPublished() {
    try {
      List<Policy> policies = policyRepository.findByPublished(true);

      if (policies.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(policies, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
