package com.becoder.controller;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.becoder.model.Material;
import com.becoder.service.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;




@RestController
public class FileController {
	private static final Logger log = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileService fileService;
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file)
	{
		try {
			Boolean uploadFile = fileService.uploadFile(file);
			//System.out.println("uploadFile contro :: "+uploadFile);
			if(uploadFile)
			{
				return new ResponseEntity<>("File uploaded Successfully ", HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>("File is not uploaded ", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/* json data start here function*/
	@PostMapping("/upload-data")
	public ResponseEntity<?> uploadFileWithData(@RequestParam String product,@RequestParam MultipartFile file)
	{
	
		log.info("product:{}",product);
		log.info("file:{}", file);
		//String [] extension= {"jpeg","png","jpg"};
		List<String> extension = Arrays.asList("jpeg","png","jpg");
		if(file.isEmpty())
		{
			return new ResponseEntity<>("Plz file upload ", HttpStatus.BAD_REQUEST);	
		}else {
			String originalFilename = file.getOriginalFilename();
			String fileExtension = FilenameUtils.getExtension(originalFilename);
			boolean contains = extension.contains(fileExtension);
			if(!contains)
			{
				return new ResponseEntity<>("Plz file upload valid formate jpeg/png/jpg ", HttpStatus.BAD_REQUEST);	
			}
		}
		
		try {
			String fileName = fileService.uploadFileWithData(file);
			//System.out.println("uploadFile contro :: "+uploadFile);
			if(StringUtils.hasText(fileName))
			{
				ObjectMapper objectMaper = new ObjectMapper();
				Material productObj = objectMaper.readValue(product, Material.class);
				
				productObj.setImageName(fileName);
				Boolean saveMaterial = fileService.saveMaterial(productObj);
				if(saveMaterial)
				{
				return new ResponseEntity<>("Material & Image uploaded Successfully ", HttpStatus.CREATED);
				}else {
					return new ResponseEntity<>("File uploaded but Material is not saved ", HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
				}else {
				return new ResponseEntity<>("File upload failed ", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/* json data function end here */ 
	
	@GetMapping("/download")
	public ResponseEntity<?> downloadFile(@RequestParam String file)
	{
		try {
			byte[] downloadFile = fileService.downloadFile(file);
			String contentType = getContentType(file);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType(contentType));
			//headers.setContentLength(file.length());
			headers.setContentDispositionFormData("attachment", file);
			//headers.setContentDispositionFormData("inline", file);
			return ResponseEntity.ok().headers(headers).body(downloadFile);
		}catch (FileNotFoundException e) {
			return new ResponseEntity<>("File is not found in folder ", HttpStatus.NOT_FOUND);
		}
		catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public String getContentType(String fileName)
	{
		String Extension= FilenameUtils.getExtension(fileName);
		
		switch (Extension) {
		case "pdf":
			return "application/pdf";
		case"xlsx":
			return "application/vnd.openxmlformats-officedocument.spreadsheettml.sheet";
		case"txt":
			return "txt/plan";
		case"png":
			return "image/png";
		case "jpeg":
			return "image/jpeg";
		default:
			return "application/octet-stream";
		}
	}

}
