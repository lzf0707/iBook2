package com.mall.common.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.mall.common.util.MyFileUtil;
import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(tags = "文件操作接口")
@RestController
@RequestMapping("/file")
public class FileController {
	private  Logger log = LoggerFactory.getLogger(FileController.class);
	
	@ApiOperation(value = "文件上传",notes = "无")
	//@ApiImplicitParam(name = "file", value = "选择文件")
	@PostMapping
	public String upload(MultipartFile file) throws IOException {
		log.info("文件上传开始");
		return MyFileUtil.saveFile(file);
	}
}
