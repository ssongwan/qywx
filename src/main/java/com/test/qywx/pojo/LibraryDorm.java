package com.test.qywx.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Table(name = "library_dorm")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryDorm {

    @Id
    private Integer ldid;

    @Column(name = "dormnum")
    private String dormnum;

    @Column(name = "exitnum")
    private String exitnum;

}
