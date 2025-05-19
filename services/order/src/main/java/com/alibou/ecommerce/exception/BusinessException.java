package com.alibou.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

  private final String msg;


  public String getMsg() {
    return msg;
  }

  @Override
  public String toString() {
    return "BusinessException{" +
            "msg='" + msg + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BusinessException that)) return false;
    return Objects.equals(getMsg(), that.getMsg());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getMsg());
  }

  public BusinessException(String msg) {
    this.msg = msg;
  }
}
