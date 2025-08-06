package com.abbascoban.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

//lombok
@Data
//hibernate-jpa
@MappedSuperclass
//Auditing
@EntityListeners(AuditingEntityListener.class)

@JsonIgnoreProperties(value = {"createdDate", "updateDate"}, allowGetters = true)
public class BaseEntitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,updatable = false)
    private Long id;

    @Column(name = "created_By")
    @CreatedBy
    private String created_By;

    @Column(name = "created_date")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "update_by", nullable = false)
    @LastModifiedBy
    private String updateBy;

    @Column(name = "update_date",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateDate;

    @Column(name = "system_auto_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date date;

}
