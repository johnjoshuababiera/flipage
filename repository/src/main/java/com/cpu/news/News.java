package com.cpu.news;

import com.cpu.AuditTrail;
import com.cpu.comments.Comment;
import com.cpu.department.Department;
import com.cpu.topic.Topic;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class News extends AuditTrail {
    private String title;
    private String filePath;
    @OneToOne
    private Department department;
    @OneToMany
    @JoinColumn
    private List<Topic> topics;
    @Lob
    private String image;

    @JsonIgnore
    @Transient
    private Long departmentId;


    public List<Topic> getTopics() {
        if(topics==null){
            topics=new ArrayList<>();
        }
        return topics;
    }
}
