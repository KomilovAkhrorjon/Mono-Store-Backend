package com.qiroldev.monomarket.utils.model;

import com.qiroldev.monomarket.utils.enumerated.ResponseResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {

  public ResponseResult result;
}
