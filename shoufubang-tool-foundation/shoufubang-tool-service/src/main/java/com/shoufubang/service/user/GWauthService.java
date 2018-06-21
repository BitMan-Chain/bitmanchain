package com.shoufubang.service.user;

import java.util.List;
import java.util.Map;

/**
 * @author 
 */
public interface GWauthService {


	boolean carRenewalAuth(Map<String, String> map);

	List<Map<String, Object>> findArea();

	boolean autoBankCard(Map<String, String> map);

	boolean autoAirTravel(Map<String, String> map);

	boolean autoEducation(Map<String, String> map);
}
