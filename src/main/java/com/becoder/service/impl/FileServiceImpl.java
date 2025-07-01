package com.becoder.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.becoder.model.Material;
import com.becoder.repository.Materialrepository;
import com.becoder.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Autowired
	private Materialrepository matrepository;
	@Override
	public Boolean uploadFile(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		//System.out.println("file Name :: "+fileName);
		File savefile = new File(uploadPath);
		//System.out.println("savefile :: "+savefile);
		if(!savefile.exists()) {
			savefile.mkdir();
		}
		String storePath = uploadPath.concat(fileName);
		//System.out.println("storePath :: "+storePath);
		long upload = Files.copy(file.getInputStream(), Paths.get(storePath));
		//System.out.println("upload :: "+upload);
		if(upload !=0) {
			return true;
		}
		return false;
	}
	@Override
	public byte[] downloadFile(String file) throws Exception {
		String fullpath=uploadPath.concat(file);
		try {
			InputStream ios= new FileInputStream(fullpath);
			return StreamUtils.copyToByteArray(ios);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
			
		}catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	@Override
	public Boolean saveMaterial(Material material) {
		Material save = matrepository.save(material);
		if(!ObjectUtils.isEmpty(save))
		{
			return true;
		}
		return false;
	}
	@Override
	public String uploadFileWithData(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		File savefile = new File(uploadPath);
		String rndstring = UUID.randomUUID().toString();
		//my_photo.jpeg -> my_photo_jhdgdg.jpeg ye logic h random string
		String removeExtension = FilenameUtils.removeExtension(fileName);//my_photo
		String extension = FilenameUtils.getExtension(fileName);
		
		fileName= removeExtension+"_"+rndstring+"."+extension;
		if(!savefile.exists()) {
			savefile.mkdir();
		}
		String storePath = uploadPath.concat(fileName);
		
		long upload = Files.copy(file.getInputStream(), Paths.get(storePath));
		
		if(upload !=0) {
			return fileName;
		}
		return null;
	}
	
	
	

}
