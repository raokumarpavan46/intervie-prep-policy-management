package com.policymanagement.spring.jpa.h2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.policymanagement.spring.jpa.h2.model.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
  List<Policy> findByPublished(boolean published);

  List<Policy> findByNameContainingIgnoreCase(String name);
}
