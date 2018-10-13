package com.cpu.topic;

import com.cpu.AuditTrail;
import com.cpu.comments.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Topic extends AuditTrail{
    private String title;
    @Column(length = 1000)
    private String description;
    @OneToMany
    @JoinColumn(name="topic_id")
    private List<Comment> comments;

    public List<Comment> getComments() {
        if(comments==null){
            comments=new ArrayList<>();
        }
        return comments;
    }
}
