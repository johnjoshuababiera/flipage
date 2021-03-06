package com.cpu.comments;

import com.cpu.AuditTrail;
import com.cpu.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Comment extends AuditTrail{
    private long articleId;
    @OneToOne
    private User user;
    @Column(length = 1000)
    private String message;
}
