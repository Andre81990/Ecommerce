package com.alibou.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

  private final String msg;

  public String getMsg() {
    return msg;
  }

  public BusinessException(String msg) {
    this.msg = msg;
  }
}
