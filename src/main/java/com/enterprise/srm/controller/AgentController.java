package com.enterprise.srm.controller;

import com.enterprise.srm.model.*;
import com.enterprise.srm.service.ServiceRequestService;
import com.enterprise.srm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/agent")
@PreAuthorize("hasAnyRole('SERVICE_AGENT','MANAGER')")
@RequiredArgsConstructor
public class AgentController {

    private final ServiceRequestService requestService;
    private final UserService userService;

    @GetMapping("/requests")
    public String openRequests(Model model, Authentication auth) {
        User currentUser = userService.findByEmail(auth.getName());
        model.addAttribute("openRequests", requestService.getOpenRequests());
        model.addAttribute("myRequests", requestService.getRequestsAssignedTo(currentUser));
        model.addAttribute("currentUser", currentUser);
        return "agent/requests";
    }

    @GetMapping("/requests/{id}")
    public String requestDetail(@PathVariable Long id, Model model, Authentication auth) {
        User currentUser = userService.findByEmail(auth.getName());
        ServiceRequest request = requestService.findById(id);

        model.addAttribute("request", request);
        model.addAttribute("comments", requestService.getComments(request));
        model.addAttribute("agents", userService.findAllAgents());
        model.addAttribute("allStatuses", RequestStatus.values());
        model.addAttribute("currentUser", currentUser);
        return "agent/request-detail";
    }

    @PostMapping("/requests/{id}/assign")
    public String assignRequest(@PathVariable Long id,
                                @RequestParam Long agentId,
                                RedirectAttributes redirectAttributes) {
        try {
            requestService.assignRequest(id, agentId);
            redirectAttributes.addFlashAttribute("successMsg", "Request assigned successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/agent/requests/" + id;
    }

    @PostMapping("/requests/{id}/status")
    public String updateStatus(@PathVariable Long id,
                               @RequestParam RequestStatus newStatus,
                               @RequestParam(required = false) String comment,
                               @RequestParam(defaultValue = "false") boolean resolutionNote,
                               Authentication auth,
                               RedirectAttributes redirectAttributes) {
        try {
            User currentUser = userService.findByEmail(auth.getName());
            requestService.updateStatus(id, newStatus, currentUser, comment, resolutionNote);
            redirectAttributes.addFlashAttribute("successMsg",
                    "Status updated to: " + newStatus.getDisplayName());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/agent/requests/" + id;
    }

    @PostMapping("/requests/{id}/comment")
    public String addComment(@PathVariable Long id,
                             @RequestParam String content,
                             @RequestParam(defaultValue = "false") boolean internal,
                             Authentication auth,
                             RedirectAttributes redirectAttributes) {
        try {
            User currentUser = userService.findByEmail(auth.getName());
            ServiceRequest request = requestService.findById(id);
            requestService.addComment(request, currentUser, content, false, internal);
            redirectAttributes.addFlashAttribute("successMsg", "Kommentar hinzugefügt.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/agent/requests/" + id;
    }
}
