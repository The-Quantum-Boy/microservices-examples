package com.sumit.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "micro_users")
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    private String userId;

    private String name ;

    private String email;

    private String about;

    @Transient //this will not been stored in database
    private List<Rating> ratings=new ArrayList<>();

}
