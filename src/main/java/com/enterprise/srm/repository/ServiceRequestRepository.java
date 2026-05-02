package com.enterprise.srm.repository;

import com.enterprise.srm.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

    // Employee: own requests
    List<ServiceRequest> findBySubmittedByOrderByCreatedAtDesc(User user);

    // Agent: open/assigned requests
    List<ServiceRequest> findByStatusInOrderByPriorityDescCreatedAtAsc(List<RequestStatus> statuses);

    List<ServiceRequest> findByAssignedToOrderByCreatedAtDesc(User agent);

    // Manager: all requests
    List<ServiceRequest> findAllByOrderByCreatedAtDesc();

    // Search
    List<ServiceRequest> findBySubmittedByAndTitleContainingIgnoreCaseOrSubmittedByAndDescriptionContainingIgnoreCase(
            User user1, String title, User user2, String description);

    @Query("SELECT sr FROM ServiceRequest sr WHERE " +
           "(LOWER(sr.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(sr.description) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<ServiceRequest> searchAll(@Param("query") String query);

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
