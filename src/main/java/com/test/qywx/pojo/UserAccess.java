package com.test.qywx.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Table(name = "user_access")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccess {
    @Id
    private Integer uaid;

    @Column(name = "uid")
    private String uid;

    @Column(name = "date")
    private Date date;

    @Column(name = "status")
    private int status;

    public UserAccess(String uid, Date date, int status) {
        this.uid = uid;
        this.date = date;
        this.status = status;
    }
}
