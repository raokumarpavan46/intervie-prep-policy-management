package com.policymanageement.spring.jpa.h2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.policymanagement.spring.jpa.h2.model.Policy;
import com.policymanagement.spring.jpa.h2.repository.PolicyRepository;

@Service
public class PolicyService implements IPolicyService {

	private PolicyRepository policyRepository;

	public PolicyService(PolicyRepository policyRepository) {
		this.policyRepository = policyRepository;
	}

	@Override
	public List<Policy> getAllPolicies(String title) {
		try {
			List<Policy> policies = new ArrayList<Policy>();

			if (title == null)
				policyRepository.findAll().forEach(policies::add);
			else
				policyRepository.findByNameContainingIgnoreCase(title).forEach(policies::add);

			if (policies.isEmpty()) {
				return null;
			}

			return policies;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Policy getPolicyById(long id) {
		Optional<Policy> policyData = policyRepository.findById(id);

		if (policyData.isPresent()) {
			return policyData.get();
		} else {
			return null;
		}
	}

	@Override
	public Policy createPolicy(Policy policyRequest) {
		try {
			Policy policy = policyRepository
					.save(new Policy(policyRequest.getName(), policyRequest.getDescription(), false));
			return policy;
		} catch (Exception e) {
			return null;
		}
	}

	public Policy updatePolicy(long id, Policy policyRequest) {
		Optional<Policy> policyData = policyRepository.findById(id);
	
		if (policyData.isPresent()) {
			Policy policy = policyData.get();
			policy.setName(policyRequest.getName());
			policy.setDescription(policyRequest.getDescription());
			policy.setPublished(policyRequest.isPublished());
			return policyRepository.save(policy);
		} else {
			return null;
		}
	}

	@Override
	public HttpStatus deletePolicy(long id) {
		try {
			policyRepository.deleteById(id);
			return HttpStatus.NO_CONTENT;
		} catch (Exception e) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}

	@Override
	public HttpStatus deleteAllPolicies() {
		try {
			policyRepository.deleteAll();
			return HttpStatus.NO_CONTENT;
		} catch (Exception e) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}

	}

	@Override
	public List<Policy> findByPublished() {
		try {
			List<Policy> policies = policyRepository.findByPublished(true);

			if (policies.isEmpty()) {
				return null;
			}
			return policies;
		} catch (Exception e) {
			throw e;
		}
	}

}
