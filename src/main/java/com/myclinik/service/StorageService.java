package com.myclinik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.myclinik.exception.StorageException;
import com.myclinik.exception.StorageFileNotFoundException;

@Service
public class StorageService implements IStorageService {

	private final Path rootLocation = Paths.get("src/main/resources/static/files");

	@Override
	public void store(MultipartFile file, String folder) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
			}
			Path location = this.rootLocation.resolve(folder);
			Files.createDirectories(location);
			Files.copy(file.getInputStream(), location.resolve(file.getOriginalFilename()));
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1)
					.filter(path -> !path.equals(this.rootLocation))
					.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Stream<Path> loadFolder(String folder) {
		try {
			Path location = this.rootLocation.resolve(folder);
			return Files.walk(location, 1)
					.filter(path -> !path.equals(location))
					.map(path -> location.relativize(path));
		} catch (IOException e) {
			return Stream.empty();
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	public void delete(String filename) {
		try {
			Path file = load(filename);
			Files.deleteIfExists(file);
		} catch (IOException e) {
			throw new StorageException("Could not delete file: " + filename, e);
		}
	}

	public void deleteFolder(String folder) {
		try {
			Path path = load(folder);
			FileSystemUtils.deleteRecursively(path);
		} catch (IOException e) {
			throw new StorageException("Could not delete folder: " + folder, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
}
