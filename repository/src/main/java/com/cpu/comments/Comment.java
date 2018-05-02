package com.cpu.comments;

import com.cpu.AuditTrail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Comment extends AuditTrail{
    private String comment;
    private long userId;
    private String username;
}
