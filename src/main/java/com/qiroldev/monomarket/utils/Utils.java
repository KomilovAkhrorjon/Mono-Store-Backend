package com.qiroldev.monomarket.utils;

import com.qiroldev.monomarket.product.system.enums.Lang;
import com.qiroldev.monomarket.utils.message.model.MessageModel;
import org.springframework.stereotype.Service;

@Service
public interface Utils {

  String getMessage(MessageModel model);

  String generateRandomNumber(int i);

  Lang getLang();
}
