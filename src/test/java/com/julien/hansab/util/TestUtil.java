package com.julien.hansab.util;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class TestUtil {

    public static MultiValueMap<String, String> constructHttpParams(int pageNumber, int pageSize, String sort) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", String.valueOf(pageNumber));
        params.add("size", String.valueOf(pageSize));
        params.add("sort", sort);
        return params;
    }

    public static MultiValueMap<String, String> constructHttpParams(String searchStr, int pageNumber, int pageSize, String sort) {
        MultiValueMap<String, String> params = constructHttpParams(pageNumber, pageSize, sort);
        params.add("find", searchStr);
        return params;
    }
}
