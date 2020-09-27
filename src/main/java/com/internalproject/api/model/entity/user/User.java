package com.internalproject.api.model.entity.user;

import com.internalproject.api.enums.UserStatus;
import com.internalproject.api.model.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, of = {"lastName", "email"})
@ToString(of = {"lastName", "email"})
public class User implements BaseEntity {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "users_user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column(name = "user_id")
    private long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String userName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "verification_key")
    private String verificationKey;
    @Column(name = "restore_key")
    private String restoreKey;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;
    @LastModifiedDate
    @Column(name = "last_modified_at")
    private Date lastModifiedAt;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "race")
    private String race;
    @Column(name = "gender")
    private String gender;
    @Column(name = "verification_code")
    private int verificationCode;
    @Column(name = "valid_phone_number")
    private boolean validPhoneNumber;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    private Set<Role> roles;
}
