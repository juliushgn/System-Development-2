package com.enterprise.srm.service;

import com.enterprise.srm.model.*;
import com.enterprise.srm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceRequestService {

    private final ServiceRequestRepository requestRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    // ── Employee operations ──────────────────────────────────────────────────

    public ServiceRequest createRequest(String title, String description,
                                        RequestCategory category, Priority priority,
                                        User submittedBy) {
        ServiceRequest request = new ServiceRequest();
        request.setTitle(title.trim());
        request.setDescription(description.trim());
        request.setCategory(category);
        request.setPriority(priority);
        request.setStatus(RequestStatus.NEW);
        request.setSubmittedBy(submittedBy);
        return requestRepository.save(request);
    }

    @Transactional(readOnly = true)
    public List<ServiceRequest> getRequestsForEmployee(User user) {
        return requestRepository.findBySubmittedByOrderByCreatedAtDesc(user);
    }

    @Transactional(readOnly = true)
    public List<ServiceRequest> searchForEmployee(User user, String query) {
        if (query == null || query.isBlank()) {
            return getRequestsForEmployee(user);
        }
        return requestRepository
                .findBySubmittedByAndTitleContainingIgnoreCaseOrSubmittedByAndDescriptionContainingIgnoreCase(
                        user, query, user, query);
    }

    // ── Agent operations ─────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<ServiceRequest> getOpenRequests() {
        return requestRepository.findByStatusInOrderByPriorityDescCreatedAtAsc(
                List.of(RequestStatus.NEW, RequestStatus.ASSIGNED,
                        RequestStatus.IN_PROGRESS, RequestStatus.WAITING_FOR_INFO));
    }

    @Transactional(readOnly = true)
    public List<ServiceRequest> getRequestsAssignedTo(User agent) {
        return requestRepository.findByAssignedToOrderByCreatedAtDesc(agent);
    }

    public ServiceRequest assignRequest(Long requestId, Long agentId) {
        ServiceRequest request = findById(requestId);
        User agent = userRepository.findById(agentId)
                .orElseThrow(() -> new IllegalArgumentException("Agent not found"));

        if (request.getStatus() == RequestStatus.NEW) {
            request.setStatus(RequestStatus.ASSIGNED);
        }
        request.setAssignedTo(agent);
        return requestRepository.save(request);
    }

    public ServiceRequest updateStatus(Long requestId, RequestStatus newStatus, User updatedBy,
                                       String commentText, boolean isResolutionNote) {
        ServiceRequest request = findById(requestId);
        validateTransition(request, newStatus);

        request.setStatus(newStatus);
        if (newStatus == RequestStatus.RESOLVED || newStatus == RequestStatus.CLOSED) {
            request.setResolvedAt(LocalDateTime.now());
        }

        if (commentText != null && !commentText.isBlank()) {
            addComment(request, updatedBy, commentText.trim(), isResolutionNote);
        }

        return requestRepository.save(request);
    }

    public Comment addComment(ServiceRequest request, User author, String content,
                              boolean isResolutionNote, boolean internal) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setRequest(request);
        comment.setAuthor(author);
        comment.setResolutionNote(isResolutionNote);
        comment.setInternal(internal);
        return commentRepository.save(comment);
    }

    public Comment addComment(ServiceRequest request, User author, String content, boolean isResolutionNote) {
        return addComment(request, author, content, isResolutionNote, false);
    }

    @Transactional(readOnly = true)
    public List<Comment> getPublicComments(ServiceRequest request) {
        return commentRepository.findByRequestAndInternalFalseOrderByCreatedAtAsc(request);
    }

    // ── Manager operations ───────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<ServiceRequest> getAllRequests() {
        return requestRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public List<ServiceRequest> searchAll(String query) {
        if (query == null || query.isBlank()) {
            return getAllRequests();
        }
        return requestRepository.searchAll(query);
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getStatusStats() {
        Map<String, Long> stats = new LinkedHashMap<>();
        for (RequestStatus s : RequestStatus.values()) {
            stats.put(s.getDisplayName(), requestRepository.countByStatus(s));
        }
        return stats;
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getPriorityStats() {
        Map<String, Long> stats = new LinkedHashMap<>();
        for (Priority p : Priority.values()) {
            stats.put(p.getDisplayName(), requestRepository.countByPriority(p));
        }
        return stats;
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getCategoryStats() {
        Map<String, Long> stats = new LinkedHashMap<>();
        for (RequestCategory c : RequestCategory.values()) {
            stats.put(c.getDisplayName(), requestRepository.countByCategory(c));
        }
        return stats;
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getAgentWorkloadStats() {
        Map<String, Long> stats = new LinkedHashMap<>();
        List<Object[]> rows = requestRepository.countGroupedByAgent();
        for (Object[] row : rows) {
            User agent = (User) row[0];
            Long count = (Long) row[1];
            stats.put(agent.getFullName(), count);
        }
        return stats;
    }

    // ── Shared ───────────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public ServiceRequest findById(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Request not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Comment> getComments(ServiceRequest request) {
        return commentRepository.findByRequestOrderByCreatedAtAsc(request);
    }

    private void validateTransition(ServiceRequest request, RequestStatus targetStatus) {
        if (!request.getStatus().canTransitionTo(targetStatus)) {
            throw new IllegalStateException(
                    String.format("Cannot transition from %s to %s",
                            request.getStatus().getDisplayName(),
                            targetStatus.getDisplayName()));
        }
    }
}
