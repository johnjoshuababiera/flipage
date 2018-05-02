package com.cpu.post;

import com.cpu.AuditTrail;
import com.cpu.comments.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Post extends AuditTrail{
    private String title;
    private String content;
    private long userId;
    private String username;
    private List<Comment> comments;
}
