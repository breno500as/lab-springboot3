package com.br.labspringboot3.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.br.labspringboot3.config.FileStorageConfig;
import com.br.labspringboot3.exception.FileStorageException;
import com.br.labspringboot3.exception.MyFileNotFoundException;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;

	public FileStorageService(FileStorageConfig fileStorageConfig) {
		Path path = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();

		this.fileStorageLocation = path;

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored!",
					e);
		}
	}

	public String storeFile(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			// Filename..txt
			if (filename.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + filename);
			}
			Files.copy(file.getInputStream(), this.fileStorageLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
			return filename;
		} catch (Exception e) {
			throw new FileStorageException("Could not store file " + filename + ". Please try again!", e);
		}
	}

	public Resource loadFileAsResource(String filename) {
		try {
			Resource resource = new UrlResource(this.fileStorageLocation.resolve(filename).normalize().toUri());
			if (resource.exists())
				return resource;
			else
				throw new MyFileNotFoundException("File not found");
		} catch (Exception e) {
			throw new MyFileNotFoundException("File not found" + filename, e);
		}
	}

}
