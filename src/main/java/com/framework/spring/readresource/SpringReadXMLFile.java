package com.framework.spring.readresource;

/**
 * Created by jiamingku on 16/10/26.
*/
public class SpringReadXMLFile {
	static String s = "{\"gatewayEntryPoint\":\"WEB_SERVICES_API\",\"merchant\":\"110075121856\",\"order\":{\"amount\":0.00,\"chargeback\":{\"amount\":0,\"currency\":\"CNY\"},\"creationTime\":\"2022-01-04T05:55:37.795Z\",\"currency\":\"CNY\",\"id\":\"order-QNA7uPku7l\",\"lastUpdatedTime\":\"2022-01-04T05:55:37.810Z\",\"merchantAmount\":0.00,\"merchantCategoryCode\":\"7512\",\"merchantCurrency\":\"CNY\",\"status\":\"FAILED\",\"totalAuthorizedAmount\":0.00,\"totalCapturedAmount\":0.00,\"totalDisbursedAmount\":0.00,\"totalRefundedAmount\":0.00},\"response\":{\"gatewayCode\":\"BLOCKED\"},\"result\":\"FAILURE\",\"risk\":{\"response\":{\"gatewayCode\":\"REJECTED\",\"review\":{\"decision\":\"NOT_REQUIRED\",\"note\":\"\"},\"rule\":[{\"data\":\"439226\",\"name\":\"MSO_BIN_RANGE\",\"recommendation\":\"REJECT\",\"type\":\"MSO_RULE\"}]}},\"sourceOfFunds\":{\"provided\":{\"card\":{\"brand\":\"VISA\",\"expiry\":{\"month\":\"8\",\"year\":\"21\"},\"fundingMethod\":\"CREDIT\",\"issuer\":\"CHINA MERCHANTS BANK\",\"number\":\"439226xxxxxx7025\",\"scheme\":\"VISA\",\"storedOnFile\":\"NOT_STORED\"}},\"type\":\"CARD\"},\"timeOfLastUpdate\":\"2022-01-04T05:55:37.810Z\",\"timeOfRecord\":\"2022-01-04T05:55:37.810Z\",\"transaction\":{\"acquirer\":{\"id\":\"BOCCHINA_S2I\",\"merchantId\":\"01427B1559\"},\"amount\":0.00,\"currency\":\"CNY\",\"id\":\"trans-fktdGaStfM\",\"source\":\"INTERNET\",\"stan\":\"0\",\"type\":\"VERIFICATION\"},\"version\":\"61\"}";

	public static void main(String[] args) {
		System.out.println(s);
		System.out.println("dd");
		System.err.println("dddd");
	}

}
