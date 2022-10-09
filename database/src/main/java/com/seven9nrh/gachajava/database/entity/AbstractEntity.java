package com.seven9nrh.gachajava.database.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

  @Column(name = "created_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime createdAt;

  @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime updatedAt;

  @Column(name = "deleted_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime deletedAt;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "updated_by")
  private String updatedBy;

  @Column(name = "deleted_by")
  private String deletedBy;

  @Column(name = "is_deleted", nullable = false)
  private Boolean isDeleted;

  @PrePersist
  public void onPrePersist() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = null;
    this.deletedAt = null;
    this.createdBy = "system";
    this.updatedBy = null;
    this.deletedBy = null;
    this.isDeleted = false;
  }

  @PreUpdate
  public void onPreUpdate() {
    this.updatedAt = LocalDateTime.now();
    this.updatedBy = "system";
  }
}
