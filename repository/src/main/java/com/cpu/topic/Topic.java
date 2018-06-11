package com.cpu.topic;

import com.cpu.AuditTrail;
import com.cpu.comments.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Topic extends AuditTrail{
    private String title;
    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Comment> comments;
}
