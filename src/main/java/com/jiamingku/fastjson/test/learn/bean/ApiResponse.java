package com.jiamingku.fastjson.test.learn.bean;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * API 响应格式
 * /**
 * 响应为：
 * <pre>
 * {
 * "code": 200,
 * "message": "\u767b\u5f55\u6210\u529f",
 * “alg": "SALT_MD5"
 * "data": {
 * "uid": "10216497",
 * "profile": "18751986615",
 * "session_key": "fa31d3a5d069c6c98cd8c38c3a5f89e6",
 * "vip": 0
 * },
 * "md5": "fa5b07f95a0bf95c26ac50abf0024eed"
 * }
 * Created by chang@yoho.cn on 2015/11/3.
 */
public class ApiResponse implements Serializable {


	/**
	 * @Fields serialVersionUID : 序列化
	 */
	private static final long serialVersionUID = 2984521054427500857L;
	private static String DEFAULT_MSG = "操作成功";
	private static int DEFAULT_CODE = 200;
	private static final String MD5_SALT = "fd4ad5fcsa0de589af23234ks1923ks";

	private int code;
	private String message;
	private String md5;
	//如果客户端判断有这个，则校验MD5
	private String alg = "SALT_MD5";
	private Object data;
//
//    public ApiResponse() {
//        this(200, DEFAULT_MSG, null);
//    }


	public ApiResponse() {
	}

	public ApiResponse(int code, String message, Object data) {
		this.code = code;
		if (StringUtils.isNotEmpty(message)) {
			this.message = message;
		}
		this.data = data;
	}

	public String getAlg() {
		return alg;
	}

	public void setAlg(String alg) {
		this.alg = alg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	/**
	 * 构造响应。 使用方式：
	 * <p/>
	 * <pre>
	 *  ApiResponse.ApiResponseBuilder builder = new ApiResponse.ApiResponseBuilder();
	 *  ApiResponse apiResponse =  builder.code(200).message("coupons total").data(new Total("0")).build();
	 * </pre>
	 */
	public static class ApiResponseBuilder {
		ApiResponse apiResponse;

		public ApiResponseBuilder() {
			apiResponse = new ApiResponse();
		}

		/**
		 * 设置错误码。默认200
		 *
		 * @param code 错误码
		 * @return ApiResponseBuilder
		 */
		public ApiResponseBuilder code(int code) {
			apiResponse.code = code;
			return this;
		}

		/**
		 * 设置消息。默认[操作成功]
		 *
		 * @param message 错误消息
		 * @return ApiResponseBuilder
		 */
		public ApiResponseBuilder message(String message) {
			apiResponse.message = message;
			return this;
		}

		/**
		 * 从Errorcode中设置错误码和错误消息
		 *
		 * @param gatewayError
		 * @return
		 */


		/**
		 * 判断获取是初始的ServiceError异常信息还是自定议ServiceException异常信息
		 *
		 * @param isUserInitError   false:取自定议ServiceException
		 */


		/**
		 * 设置响应的具体内容
		 *
		 * @param data 响应的具体内容
		 * @return 内容
		 */
		public ApiResponseBuilder data(Object data) {
			apiResponse.data = data;
			return this;
		}


	}


}
