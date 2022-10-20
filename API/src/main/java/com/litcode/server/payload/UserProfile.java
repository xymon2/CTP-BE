package com.litcode.server.payload;

import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserProfile implements Serializable {

  private Integer id;
  private String userId;
  private String email;
  private List<Integer> solvedId;

  @Builder
  public UserProfile(
      @NonNull Integer id,
      @NonNull String userId,
      @NonNull String email,
      @NonNull List<Integer> solvedId) {
    this.id = id;
    this.userId = userId;
    this.email = email;
    this.solvedId = solvedId;
  }
}
