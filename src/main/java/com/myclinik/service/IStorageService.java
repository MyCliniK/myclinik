package com.myclinik.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IStorageService {

	void init();

	void store(MultipartFile file, String folder);

	Stream<Path> loadAll();

	Stream<Path> loadFolder(String folder);

	Path load(String filename);

	Resource loadAsResource(String filename);

	void delete(String path);

	void deleteFolder(String folder);

	void deleteAll();

}
