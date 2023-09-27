package com.hexadecimal.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="user")
public class UserDO implements Serializable {
    private static final long serialVersionUID = -1L;

    @Id
    private Long id;
    private String name;
    private Integer age;
    private String email;

}
