package com.enterprise.srm.repository;

import com.enterprise.srm.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

    // Employee: own requests
    List<ServiceRequest> findBySubmittedByOrderByCreatedAtDesc(User user);

    // Agent: open/assigned requests
    List<ServiceRequest> findByStatusInOrderByPriorityDescCreatedAtAsc(List<RequestStatus> statuses);

    // Agent: requests by status, newest first
    List<ServiceRequest> findByStatusInOrderByCreatedAtDesc(List<RequestStatus> statuses);

    List<ServiceRequest> findByAssignedToOrderByCreatedAtDesc(User agent);

    // Manager: all requests
    List<ServiceRequest> findAllByOrderByCreatedAtDesc();

    // Search
    List<ServiceRequest> findBySubmittedByAndTitleContainingIgnoreCaseOrSubmittedByAndDescriptionContainingIgnoreCase(
            User user1, String title, User user2, String description);

    List<ServiceRequest> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String title, String description);

    // Statistics
    long countByStatus(RequestStatus status);

    long countByCategory(RequestCategory category);

    long countByPriority(Priority priority);

    long countByAssignedTo(User agent);

    @Query("SELECT sr.status, COUNT(sr) FROM ServiceRequest sr GROUP BY sr.status")
    List<Object[]> countGroupedByStatus();

    @Query("SELECT sr.category, COUNT(sr) FROM ServiceRequest sr GROUP BY sr.category")
    List<Object[]> countGroupedByCategory();

    @Query("SELECT sr.assignedTo, COUNT(sr) FROM ServiceRequest sr WHERE sr.assignedTo IS NOT NULL GROUP BY sr.assignedTo")
    List<Object[]> countGroupedByAgent();
}
