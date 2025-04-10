package com.qiroldev.monomarket.utils.enumerated;

public enum ResponseResult {

  /**
   * Muvaffaqyatli tugallangan operatsiya
   */
  SUCCESS,

  /**
   * Kategoryasiz ichki server xatoligi
   */
  INTERNAL_ERROR,

  /**
   * So'rovni kiritish parametrlari noto‘g‘ri
   */
  INVALID_PARAMETERS,

  /**
   * Kerakli parametrlar ko'rsatilmagan
   */
  MISSING_PARAMETERS,

  /**
   * Yaroqsiz sessiya jo'natilgan. Sessiyani yangilang
   */
  INVALID_SESSION,

  /**
   * Foydalanuvchiga ruxsat berilmagan. Kirish huquqlari yetarli emas.
   */
  ACCESS_DENIED,

  /**
   * Avtorizatsiyadan o'tish kerak.
   */
  AUTHORIZATION_REQUIRED,

  /**
   * Obyekt topilmadi
   */
  OBJECT_NOT_FOUND,

  /**
   * Obyekt allaqachon yarilgan
   */
  OBJECT_ALREADY_EXISTS,

  /**
   * Yaroqsiz code
   */
  INVALID_CODE,

  /**
   * Noto'g'ri avtorizatsiya ma'lumotlari
   */
  BAD_CREDENTIALS,

  /**
   * Belgilangan parametrlar bilan yaroqsiz operatsiya
   */
  INVALID_OPERATION,

  /**
   * Integratsiya qilingan server bilan muammo yuzaga kelgan
   */
  BACKEND_ERROR,

  /**
   * Yaroqsiz refresh token
   */
  INVALID_REFRESH_TOKEN,

  /**
   * Yaroqsiz IdToken(Google account auth)
   */
  INVALID_ID_TOKEN,

  /**
   * Foydalanuvchi o'chirilgan
   */
  USER_DELETED,

  /**
   *Operatsiya tugallanish vaqtidan oshib ketdi
   */
  OPERATION_TIMEOUT,

  /**
   * Muddati tugagan sessiya
   */
  SESSION_EXPIRED,

  /**
   * Jo'natishda xatolik
   */
  SENDING_FAILED,

  /**
   * Obyekt o'chirilgan
   */
  OBJECT_DELETED,

  /**
   * Muvaffaqyatsiz avtorizatsiya
   */
  AUTH_FAILED,

  /**
   * Faylni uqish yozishda xatolik
   */
  FILE_IO_EXCEPTION,

  /**
   *
   */
  BAD_REQUEST

}

