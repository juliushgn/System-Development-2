package com.enterprise.srm.controller;

import com.enterprise.srm.model.RequestStatus;
import com.enterprise.srm.model.User;
import com.enterprise.srm.service.ServiceRequestService;
import com.enterprise.srm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manager")
@PreAuthorize("hasRole('MANAGER')")
@RequiredArgsConstructor
public class ManagerController {

    private final ServiceRequestService requestService;
    private final UserService userService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication auth) {
        User currentUser = userService.findByEmail(auth.getName());
        model.addAttribute("currentUser", currentUser);
        var allRequests = requestService.getAllRequests();
        long openCount = allRequests.stream()
            .filter(r -> r.getStatus() != RequestStatus.RESOLVED
                      && r.getStatus() != RequestStatus.CLOSED)
            .count();
        long resolvedCount = allRequests.stream()
            .filter(r -> r.getStatus() == RequestStatus.RESOLVED).count();
        long closedCount = allRequests.stream()
            .filter(r -> r.getStatus() == RequestStatus.CLOSED).count();
        model.addAttribute("allRequests", allRequests);
        model.addAttribute("totalRequests", allRequests.size());
        model.addAttribute("openCount", openCount);
        model.addAttribute("resolvedCount", resolvedCount);
        model.addAttribute("closedCount", closedCount);
        model.addAttribute("statusStats", requestService.getStatusStats());
        model.addAttribute("categoryStats", requestService.getCategoryStats());
        model.addAttribute("priorityStats", requestService.getPriorityStats());
        model.addAttribute("agentWorkload", requestService.getAgentWorkloadStats());
        return "manager/dashboard";
    }

    @GetMapping("/requests")
    public String allRequests(Model model,
                              Authentication auth,
                              @RequestParam(required = false) String search) {
        User currentUser = userService.findByEmail(auth.getName());
        var requests = (search != null && !search.isBlank())
                ? requestService.searchAll(search)
                : requestService.getAllRequests();

        model.addAttribute("requests", requests);
        model.addAttribute("search", search);
        model.addAttribute("currentUser", currentUser);
        return "manager/requests";
    }
}
