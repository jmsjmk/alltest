package com.framework.springmvc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
@Api("文档搜索器API")
public class UrlCatcherController {

    @ApiOperation("测试1")
    @RequestMapping(value = "/spider/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map spider(
            @ApiParam(required = true,name = "id",value = "测试id")
            @PathVariable("id") Integer id) {
        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("id", id);
        return result;
    }
}