package com.myclinik.controller;

import com.myclinik.model.Client;
import com.myclinik.model.Treatment;
import com.myclinik.repository.ClientRepository;
import com.myclinik.service.IClientService;
import com.myclinik.service.ITreatmentService;

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

import java.util.List;

import javax.swing.text.Document;

// imports for generating pdf.
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import org.apache.pdfbox.pdmodel.font.PDFont;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class DocumentController {
	@Autowired
	private IClientService clientService;
	@Autowired
	private ITreatmentService treatmentService;

	@RequestMapping("/documents/new")
	public String showNewDocumentForm(Model model, @RequestParam(required = false) Long clientId,
			@RequestParam(required = false) Long treatmentId) {
		var treatments = (List<Treatment>) treatmentService.findAll();
		var clients = (List<Client>) clientService.findAll();

		model.addAttribute("clientId", clientId);
		model.addAttribute("treatmentId", treatmentId);
		model.addAttribute("treatments", treatments);
		model.addAttribute("clients", clients);
		return "ew_document";
	}

	@PostMapping("/documents/generate")
	public String generateClientConsent(@RequestParam("client") Client client,
			@RequestParam("treatment") Treatment treatment) {

		try {
			PDDocument document = new PDDocument();
			PDPage page = new PDPage(PDRectangle.A4);
			document.addPage(page);

			PDPageContentStream contentStream = new PDPageContentStream(document, page);

			// parte del codigo para el título

			String title = "CONSENTIMIENTO DEL CLIENTE";

			contentStream.setFont(PDType1Font.COURIER_BOLD, 30);
			contentStream.beginText();
			contentStream.newLineAtOffset(70, 720);
			contentStream.showText(title);
			contentStream.endText();

			// parte para dirigirse al cliente

			contentStream.beginText();
			contentStream.newLineAtOffset(60, 660);
			contentStream.setFont(PDType1Font.COURIER, 12);
			contentStream.showText("Estimado");
			contentStream.newLineAtOffset(65, 0);
			contentStream.setFont(PDType1Font.COURIER_BOLD, 12);
			contentStream.showText(client.getFirstName() + " " + client.getLastName());
			contentStream.endText();

			// codigo para crear varias lineas
			PDFont pdfFont = PDType1Font.COURIER;
			float fontSize = 12;
			float leading = 1.5f * fontSize;

			PDRectangle mediabox = page.getMediaBox();
			float margin = 60;
			float width = mediabox.getWidth() - 2 * margin;
			float startX = mediabox.getLowerLeftX() + margin;
			float startY = mediabox.getUpperRightY() - 4 * margin;
			String treat = treatment.getName();
			String text = "Firmando este documento, declara que está al tanto del procedimiento que se va a llevar a cabo durante la "
					+ treat.toUpperCase() + " a la que se ve a someter.";
			List<String> lines = new ArrayList<String>();
			int lastSpace = -1;
			while (text.length() > 0) {
				int spaceIndex = text.indexOf(' ', lastSpace + 1);
				if (spaceIndex < 0)
					spaceIndex = text.length();
				String subString = text.substring(0, spaceIndex);
				float size = fontSize * pdfFont.getStringWidth(subString) / 1000;
				if (size > width) {
					if (lastSpace < 0)
						lastSpace = spaceIndex;
					subString = text.substring(0, lastSpace);
					lines.add(subString);
					text = text.substring(lastSpace).trim();
					lastSpace = -1;
				} else if (spaceIndex == text.length()) {
					lines.add(text);
					text = "";
				} else {
					lastSpace = spaceIndex;
				}
			}

			// text 1
			contentStream.beginText();
			contentStream.setFont(pdfFont, fontSize);
			contentStream.newLineAtOffset(startX, startY);
			for (String line : lines) {
				contentStream.showText(line);
				contentStream.newLineAtOffset(0, -leading);
			}

			contentStream.endText();

			// parte para firmar documento
			String firma = "Firma cliente:";

			contentStream.setFont(PDType1Font.COURIER, 12);
			contentStream.beginText();
			contentStream.newLineAtOffset(60, 470);
			contentStream.showText(firma);
			contentStream.endText();

			String linea = "________________";

			contentStream.setFont(PDType1Font.COURIER, 12);
			contentStream.beginText();
			contentStream.newLineAtOffset(60, 405);
			contentStream.showText(linea);
			contentStream.endText();

			// parte del codigo para la fecha

			DateTimeFormatter dtf5 = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
			String strDate = dtf5.format(LocalDateTime.now());

			contentStream.setFont(PDType1Font.COURIER, 12);
			contentStream.beginText();
			contentStream.newLineAtOffset(60, 370);
			contentStream.showText(strDate);
			contentStream.endText();

			// Image

			PDImageXObject logo = PDImageXObject.createFromFile("./src/main/resources/static/assets/logo_circle.png",
					document);
			contentStream.drawImage(logo, 10, 10, 50, 50);
			contentStream.close();

			contentStream.close();

			String filename = "/Consentimiento " + treatment.getName() + ".pdf";
			String path = "./src/main/resources/static/files/" + client.getId();
			Files.createDirectories(Paths.get(path));
			document.save(path + filename);
			document.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return "redirect:/clients/client?id=" + client.getId();

	}
}
