package com.litcode.server.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserProfile implements Serializable {

  private Integer id;
  private String userId;
  private String name;
  private String email;
  private List<Object> solvedId;

  @Builder
  public UserProfile(
      @NonNull Integer id,
      @NonNull String userId,
      @NonNull String name,
      @NonNull String email,
      @NonNull List<Object> solvedId) {
    this.id = id;
    this.userId = userId;
    this.name = name;
    this.email = email;
    this.solvedId = solvedId;
  }
}
