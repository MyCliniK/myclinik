package com.myclinik.controller;

import com.myclinik.model.Client;
import com.myclinik.repository.ClientRepository;
import com.myclinik.service.IClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import java.util.stream.Collectors;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.myclinik.service.IStorageService;
import com.myclinik.exception.StorageFileNotFoundException;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ClientController {
	@Autowired
	private IClientService clientService;

	@Autowired
	private IStorageService storageService;

	@GetMapping("/clients")
	public String findClients(Model model) {
		var clients = (List<Client>) clientService.findAll();
		model.addAttribute("clients", clients);
		return "showClients";
	}

	@GetMapping("/clients/client")
	public String getClient(Model model, @RequestParam("id") String itemid) {
		var client = clientService.findOne(Long.parseLong(itemid));

		List<File> files = storageService.loadFolder(itemid).map(path -> {
			return new File(path.getFileName().toString(),
					MvcUriComponentsBuilder.fromMethodName(ClientController.class,
							"serveFile", path.getFileName().toString(),
							itemid).build().toUri().toString());
		})
				.collect(Collectors.toList());

		model.addAttribute("files", files);
		model.addAttribute("client", client);
		return "client";
	}

	@RequestMapping("/clients/new")
	public String createClient(Model model) {
		var newclient = clientService.createClient();
		model.addAttribute("client", newclient);
		return "newclient";
	}

	@PostMapping("/clients/new/save")
	public String saveClient(@ModelAttribute("client") Client client) {
		clientService.saveClient(client);
		return "redirect:/clients";
	}

	@RequestMapping("/clients/delete")
	public String deleteClient(@RequestParam("id") Long itemid) {
		clientService.deleteClient(itemid);
		return "redirect:/clients";
	}

	@RequestMapping("/clients/update")
	public String editClient(@RequestParam("id") Long itemId, Client client, @RequestParam("file") MultipartFile file) {
		storageService.store(file, Long.toString(itemId));
		clientService.updateClient(itemId, client);
		return "redirect:/clients/client?id=" + itemId;
	}

	@GetMapping("/files/{clientid:.+}/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename, @PathVariable String clientid) {
		Resource file = storageService.loadAsResource(clientid + "/" + filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	@GetMapping("/files/{clientid:.+}/delete")
	public String deleteFile(@PathVariable String clientid, @RequestParam("filename") String filename) {
		storageService.delete(clientid + "/" + filename);
		return "redirect:/clients/client?id=" + clientid;
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

	private class File {
		private String name;
		private String url;

		public File(String name, String url) {
			this.name = name;
			this.url = url;
		}

		public String getName() {
			return name;
		}

		public String getUrl() {
			return url;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}

}
