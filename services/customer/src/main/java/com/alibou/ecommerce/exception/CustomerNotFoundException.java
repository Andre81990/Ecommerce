package com.alibou.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
public class CustomerNotFoundException extends RuntimeException {

  private final String msg;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CustomerNotFoundException that)) return false;
    return Objects.equals(getMsg(), that.getMsg());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getMsg());
  }

  public String getMsg() {
    return msg;
  }


  @Override
  public String toString() {
    return "CustomerNotFoundException{" +
            "msg='" + msg + '\'' +
            '}';
  }

  public CustomerNotFoundException(String msg) {
    this.msg = msg;
  }
}
