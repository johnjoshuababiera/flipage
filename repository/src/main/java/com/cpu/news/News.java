package com.cpu.news;

import com.cpu.AuditTrail;
import com.cpu.comments.Comment;
import com.cpu.department.Department;
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
    private List<Comment> comments;


    public List<Comment> getComments() {
        if(comments==null){
            comments=new ArrayList<>();
        }
        return comments;
    }
}
