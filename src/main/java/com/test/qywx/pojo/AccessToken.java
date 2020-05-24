package com.test.qywx.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Table(name ="access_token")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessToken {

    @Id
    private Integer id;

    @Column(name = "token")
    private String token;

    @Column(name = "expiresIn")
    private Integer expiresIn;

    @Column(name = "save_date")
    private String save_date;

    @Column(name = "types")
    private String types;

}
