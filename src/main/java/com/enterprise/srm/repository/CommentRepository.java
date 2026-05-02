package com.enterprise.srm.repository;

import com.enterprise.srm.model.Comment;
import com.enterprise.srm.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByRequestOrderByCreatedAtAsc(ServiceRequest request);

    List<Comment> findByRequestAndInternalFalseOrderByCreatedAtAsc(ServiceRequest request);
}
