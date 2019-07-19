package zjy.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

public class BaseController {

	public ResponseEntity<FileSystemResource> exportFile(File file) {
		if (file == null) {
			return null;
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.add("Last-Modified", new Date().toString());
		headers.add("ETag", String.valueOf(System.currentTimeMillis()));

		return ResponseEntity
						.ok()
						.headers(headers)
						.contentLength(file.length())
						.contentType(MediaType.parseMediaType("application/octet-stream"))
						.body(new FileSystemResource(file));
	}

	public byte[] exportBytes(File file) throws IOException {
		//File file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);
		byte[] bytes = new byte[inputStream.available()];
		inputStream.read(bytes, 0, inputStream.available());
		return bytes;
	}

}
