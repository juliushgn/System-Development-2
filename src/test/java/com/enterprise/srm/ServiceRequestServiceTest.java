package com.enterprise.srm;

import com.enterprise.srm.model.*;
import com.enterprise.srm.repository.CommentRepository;
import com.enterprise.srm.repository.ServiceRequestRepository;
import com.enterprise.srm.repository.UserRepository;
import com.enterprise.srm.service.ServiceRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceRequestServiceTest {

    @Mock ServiceRequestRepository requestRepository;
    @Mock CommentRepository commentRepository;
    @Mock UserRepository userRepository;

    @InjectMocks ServiceRequestService service;

    private User employee;
    private User agent;
    private ServiceRequest request;

    @BeforeEach
    void setUp() {
        employee = new User();
        employee.setId(1L);
        employee.setFirstName("Alice");
        employee.setLastName("Meyer");
        employee.setEmail("alice@company.com");
        employee.setRole(Role.EMPLOYEE);

        agent = new User();
        agent.setId(2L);
        agent.setFirstName("Carol");
        agent.setLastName("Wagner");
        agent.setEmail("carol@company.com");
        agent.setRole(Role.SERVICE_AGENT);

        request = new ServiceRequest();
        request.setId(1L);
        request.setTitle("Test request");
        request.setDescription("This is a test description with enough detail.");
        request.setCategory(RequestCategory.IT_SUPPORT);
        request.setPriority(Priority.MEDIUM);
        request.setStatus(RequestStatus.NEW);
        request.setSubmittedBy(employee);
    }

    // ── createRequest ────────────────────────────────────────────────────────

    @Test
    void createRequest_shouldSetStatusNew() {
        when(requestRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        ServiceRequest result = service.createRequest(
                "My laptop is broken",
                "It does not start since this morning.",
                RequestCategory.IT_SUPPORT,
                Priority.HIGH,
                employee
        );

        assertThat(result.getStatus()).isEqualTo(RequestStatus.NEW);
        assertThat(result.getSubmittedBy()).isEqualTo(employee);
        verify(requestRepository).save(any(ServiceRequest.class));
    }

    @Test
    void createRequest_shouldTrimWhitespace() {
        when(requestRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        ServiceRequest result = service.createRequest(
                "  Padded title  ",
                "  Padded description with enough text.  ",
                RequestCategory.FACILITY,
                Priority.LOW,
                employee
        );

        assertThat(result.getTitle()).isEqualTo("Padded title");
        assertThat(result.getDescription()).isEqualTo("Padded description with enough text.");
    }

    // ── assignRequest ────────────────────────────────────────────────────────

    @Test
    void assignRequest_shouldTransitionFromNewToAssigned() {
        when(requestRepository.findById(1L)).thenReturn(Optional.of(request));
        when(userRepository.findById(2L)).thenReturn(Optional.of(agent));
        when(requestRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        ServiceRequest result = service.assignRequest(1L, 2L);

        assertThat(result.getStatus()).isEqualTo(RequestStatus.ASSIGNED);
        assertThat(result.getAssignedTo()).isEqualTo(agent);
    }

    @Test
    void assignRequest_shouldNotChangeStatusIfAlreadyInProgress() {
        request.setStatus(RequestStatus.IN_PROGRESS);
        request.setAssignedTo(agent);
        when(requestRepository.findById(1L)).thenReturn(Optional.of(request));
        when(userRepository.findById(2L)).thenReturn(Optional.of(agent));
        when(requestRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        ServiceRequest result = service.assignRequest(1L, 2L);

        assertThat(result.getStatus()).isEqualTo(RequestStatus.IN_PROGRESS);
    }

    @Test
    void assignRequest_shouldThrowWhenAgentNotFound() {
        when(requestRepository.findById(1L)).thenReturn(Optional.of(request));
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.assignRequest(1L, 99L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Agent not found");
    }

    // ── updateStatus ─────────────────────────────────────────────────────────

    @Test
    void updateStatus_assignedToInProgress_shouldSucceed() {
        request.setStatus(RequestStatus.ASSIGNED);
        request.setAssignedTo(agent);
        when(requestRepository.findById(1L)).thenReturn(Optional.of(request));
        when(requestRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        ServiceRequest result = service.updateStatus(1L, RequestStatus.IN_PROGRESS, agent, null, false);

        assertThat(result.getStatus()).isEqualTo(RequestStatus.IN_PROGRESS);
    }

    @Test
    void updateStatus_withComment_shouldSaveComment() {
        request.setStatus(RequestStatus.ASSIGNED);
        when(requestRepository.findById(1L)).thenReturn(Optional.of(request));
        when(requestRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(commentRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        service.updateStatus(1L, RequestStatus.IN_PROGRESS, agent, "Working on it now.", false);

        verify(commentRepository).save(any(Comment.class));
    }

    @Test
    void updateStatus_withBlankComment_shouldNotSaveComment() {
        request.setStatus(RequestStatus.ASSIGNED);
        when(requestRepository.findById(1L)).thenReturn(Optional.of(request));
        when(requestRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        service.updateStatus(1L, RequestStatus.IN_PROGRESS, agent, "   ", false);

        verify(commentRepository, never()).save(any());
    }

    @Test
    void updateStatus_toResolved_shouldSetResolvedAt() {
        request.setStatus(RequestStatus.IN_PROGRESS);
        when(requestRepository.findById(1L)).thenReturn(Optional.of(request));
        when(requestRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        ServiceRequest result = service.updateStatus(1L, RequestStatus.RESOLVED, agent, null, false);

        assertThat(result.getResolvedAt()).isNotNull();
    }

    @Test
    void updateStatus_invalidTransition_shouldThrow() {
        request.setStatus(RequestStatus.NEW);
        when(requestRepository.findById(1L)).thenReturn(Optional.of(request));

        assertThatThrownBy(() -> service.updateStatus(1L, RequestStatus.RESOLVED, agent, null, false))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Cannot transition");
    }

    @Test
    void updateStatus_closedTicket_cannotTransitionAnywhere() {
        request.setStatus(RequestStatus.CLOSED);
        when(requestRepository.findById(1L)).thenReturn(Optional.of(request));

        assertThatThrownBy(() -> service.updateStatus(1L, RequestStatus.IN_PROGRESS, agent, null, false))
                .isInstanceOf(IllegalStateException.class);
    }

    // ── Status transition rules ──────────────────────────────────────────────

    @Test
    void statusTransitions_closedIsTerminal() {
        assertThat(RequestStatus.CLOSED.canTransitionTo(RequestStatus.NEW)).isFalse();
        assertThat(RequestStatus.CLOSED.canTransitionTo(RequestStatus.IN_PROGRESS)).isFalse();
        assertThat(RequestStatus.CLOSED.canTransitionTo(RequestStatus.RESOLVED)).isFalse();
        assertThat(RequestStatus.CLOSED.getAllowedTransitions()).isEmpty();
    }

    @Test
    void statusTransitions_resolvedCanBeReopened() {
        assertThat(RequestStatus.RESOLVED.canTransitionTo(RequestStatus.IN_PROGRESS)).isTrue();
        assertThat(RequestStatus.RESOLVED.canTransitionTo(RequestStatus.CLOSED)).isTrue();
    }

    @Test
    void statusTransitions_anyStatusCanTransitionToClosed() {
        assertThat(RequestStatus.NEW.canTransitionTo(RequestStatus.CLOSED)).isTrue();
        assertThat(RequestStatus.ASSIGNED.canTransitionTo(RequestStatus.CLOSED)).isTrue();
        assertThat(RequestStatus.IN_PROGRESS.canTransitionTo(RequestStatus.CLOSED)).isTrue();
        assertThat(RequestStatus.WAITING_FOR_INFO.canTransitionTo(RequestStatus.CLOSED)).isTrue();
        assertThat(RequestStatus.RESOLVED.canTransitionTo(RequestStatus.CLOSED)).isTrue();
    }

    @Test
    void statusTransitions_newCanOnlyGoToAssigned() {
        assertThat(RequestStatus.NEW.canTransitionTo(RequestStatus.ASSIGNED)).isTrue();
        assertThat(RequestStatus.NEW.canTransitionTo(RequestStatus.IN_PROGRESS)).isFalse();
        assertThat(RequestStatus.NEW.canTransitionTo(RequestStatus.RESOLVED)).isFalse();
        assertThat(RequestStatus.NEW.canTransitionTo(RequestStatus.WAITING_FOR_INFO)).isFalse();
    }

    @Test
    void statusTransitions_waitingForInfoCanReturnToInProgress() {
        assertThat(RequestStatus.WAITING_FOR_INFO.canTransitionTo(RequestStatus.IN_PROGRESS)).isTrue();
        assertThat(RequestStatus.WAITING_FOR_INFO.canTransitionTo(RequestStatus.RESOLVED)).isTrue();
    }

    // ── searchForEmployee ────────────────────────────────────────────────────

    @Test
    void searchForEmployee_withBlankQuery_returnsAllForEmployee() {
        when(requestRepository.findBySubmittedByOrderByCreatedAtDesc(employee))
                .thenReturn(List.of(request));

        List<ServiceRequest> result = service.searchForEmployee(employee, "");

        assertThat(result).hasSize(1);
        verify(requestRepository).findBySubmittedByOrderByCreatedAtDesc(employee);
        verify(requestRepository, never())
                .findBySubmittedByAndTitleContainingIgnoreCaseOrSubmittedByAndDescriptionContainingIgnoreCase(
                        any(), any(), any(), any());
    }

    @Test
    void searchForEmployee_withNullQuery_returnsAllForEmployee() {
        when(requestRepository.findBySubmittedByOrderByCreatedAtDesc(employee))
                .thenReturn(List.of(request));

        List<ServiceRequest> result = service.searchForEmployee(employee, null);

        assertThat(result).hasSize(1);
    }

    @Test
    void searchForEmployee_withQuery_delegatesToSearchRepo() {
        when(requestRepository
                .findBySubmittedByAndTitleContainingIgnoreCaseOrSubmittedByAndDescriptionContainingIgnoreCase(
                        employee, "laptop", employee, "laptop"))
                .thenReturn(List.of(request));

        List<ServiceRequest> result = service.searchForEmployee(employee, "laptop");

        assertThat(result).hasSize(1);
    }

    // ── getRequestsForEmployee ───────────────────────────────────────────────

    @Test
    void getRequestsForEmployee_shouldDelegateToRepo() {
        when(requestRepository.findBySubmittedByOrderByCreatedAtDesc(employee))
                .thenReturn(List.of(request));

        List<ServiceRequest> result = service.getRequestsForEmployee(employee);

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(request);
    }

    // ── addComment ───────────────────────────────────────────────────────────

    @Test
    void addComment_shouldPersistWithCorrectFields() {
        when(commentRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Comment result = service.addComment(request, agent, "Looking into this.", false, false);

        assertThat(result.getContent()).isEqualTo("Looking into this.");
        assertThat(result.getAuthor()).isEqualTo(agent);
        assertThat(result.getRequest()).isEqualTo(request);
        assertThat(result.isResolutionNote()).isFalse();
        assertThat(result.getInternal()).isFalse();
    }

    @Test
    void addComment_internalFlag_shouldBePreserved() {
        when(commentRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Comment result = service.addComment(request, agent, "Internal note.", false, true);

        assertThat(result.getInternal()).isTrue();
    }
}
