package com.example.scheduledreminder.model.entity;

import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class EhrBaseMongoEntity {

    @Id
    private String id;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastUpdatedDate;
    @Version
    private Long version;
}
