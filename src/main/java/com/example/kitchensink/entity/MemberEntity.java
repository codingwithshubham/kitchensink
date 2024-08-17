package com.example.kitchensink.entity;

import com.example.kitchensink.repository.RepositoryConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@XmlRootElement
@Table(name = RepositoryConstants.MEMBER_TABLE)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    @Column(name = RepositoryConstants.NAME)
    private String name;

    @NotNull
    @NotEmpty
    @Email
    @Column(name = RepositoryConstants.EMAIL, unique = true)
    private String email;

    @NotNull
    @Size(min = 10, max = 12)
    @Digits(fraction = 0, integer = 12)
    @Column(name = RepositoryConstants.PHONE_NUMBER)
    private String phoneNumber;

    public MemberEntity(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
