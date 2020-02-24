package com.julien.hansab.rest.util;

import org.springframework.http.HttpHeaders;

public class RestUtils {

    public static HttpHeaders createHttpHeaders(String totalElements) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Total-Elements", totalElements);
        return httpHeaders;
    }
}
