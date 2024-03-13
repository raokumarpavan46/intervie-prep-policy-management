package com.policymanageement.spring.jpa.h2.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.policymanagement.spring.jpa.h2.model.Policy;

public interface IPolicyService {
	public List<Policy> getAllPolicies(String title);

	public Policy getPolicyById(long id);

	public Policy createPolicy(Policy policyRequest);

	public Policy updatePolicy(long id, Policy policyRequest);

	public HttpStatus deletePolicy(long id);

	public HttpStatus deleteAllPolicies();

	public List<Policy> findByPublished();
}
