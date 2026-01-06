package com.ra.model.entity;

import com.ra.model.constant.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String username ;
    private String password ;
    private String phoneNumber ;
    private String fullName ;
    @Enumerated(EnumType.STRING)
    private Role role ;
}
