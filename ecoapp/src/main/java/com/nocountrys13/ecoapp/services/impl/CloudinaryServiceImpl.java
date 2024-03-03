package com.nocountrys13.ecoapp.services.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryServiceImpl {

	Cloudinary cloudinary;

	@Value("${cloudinary.name}")
	 String cloudName;

	@Value("${API_KEY}")
	 String apiKey;

	@Value("${API_SECRET}")
	 String apiSecret;
	
	private Map<String, String> valuesMap = new HashMap<>();

	
	private void initCloudinary() {
		if(cloudinary == null) {
		valuesMap.put("cloud_name", cloudName);
		valuesMap.put("api_key", apiKey);
		valuesMap.put("api_secret", apiSecret);
		cloudinary = new Cloudinary(valuesMap);
		}

	}
	
	

	public Map<String, String> upload(MultipartFile multipartFile) throws IOException {
		initCloudinary();
	
		File file = convert(multipartFile);
		Map<String, String> result = cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", "Fotos/"));
		file.delete();
		
		return result;
	}

	public Map<String, String> delete(String id) throws IOException {
		Map<String, String> result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
		return result;
	}

	private File convert(MultipartFile multipartFile) throws IOException {
		File file = new File(multipartFile.getOriginalFilename());
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(multipartFile.getBytes());
		fo.close();
		return file;
	}
}
