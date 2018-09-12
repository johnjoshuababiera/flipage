package com.cpu.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long>{

    @Query(value = "SELECT * FROM comment c WHERE c.post_id=:postId", nativeQuery = true)
    List<Comment> findCommentsByPostId(@Param("postId") long id);

    @Query(value = "SELECT * FROM comment c WHERE c.topic_id in(:topicIds) ",nativeQuery = true)
    List<Comment> findCommentsInIds(@Param("topicIds") List<Long> topicIds);
}
