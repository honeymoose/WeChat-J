package com.ossez.wechat.common.exception;

/**
 * @author Daniel Qian
 */
public class DataStructureException extends WxErrorException {
  private static final long serialVersionUID = -6357149550353160810L;

  private final WxError error;

  private static final int DEFAULT_ERROR_CODE = -99;

  public DataStructureException(String message) {
    this(WxError.builder().errorCode(DEFAULT_ERROR_CODE).errorMsg(message).build());
  }

  public DataStructureException(WxError error) {
    super(error.toString());
    this.error = error;
  }


  public WxError getError() {
    return this.error;
  }
}
