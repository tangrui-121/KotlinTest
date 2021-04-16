package com.person.network.interfaces;

import java.util.Map;

public interface ParamsInterceptor {

    Map<String, String> checkParams(Map<String, String> params);
}
