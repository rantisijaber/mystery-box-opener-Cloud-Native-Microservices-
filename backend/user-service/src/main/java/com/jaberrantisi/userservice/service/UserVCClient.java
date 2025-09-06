package com.jaberrantisi.userservice.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "mystery-box-service", url = "")
public class UserVCClient {

}
