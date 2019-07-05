package zjy.controller;

import zjy.tools.IPUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class UploadPicController {

	private static boolean flag = true;

	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	public String uploadFile(MultipartFile file, HttpServletRequest request){
		if (file == null){
			return "{success:0,mess:'未选择文件'}";
		}
		String fileName = IPUtils.getIpAddr(request).replaceAll("[:\\.]","_") + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".jpeg";
		try {
			file.transferTo(new File("C:/pic/"+fileName));
		} catch (IOException e) {
			e.printStackTrace();
			return "{success:0}";
		}
		return "{success:1}";
	}

	@RequestMapping("/pic")
	@ResponseBody
	public List<String> getAllPic(){
		File file = new File("c:/pic");
		List<String> tmp = Arrays.asList(file.list());
		List<String> res = new ArrayList<>();
		tmp.forEach((item) -> {
			res.add("localhost:8080/static/" + item);
		});
		return res;
	}

	@RequestMapping("/flag/{value}")
	@ResponseBody
	public String transFlag(@PathVariable("value") int flagNum){
		flag = flagNum == 1;
		return "{success:1}";
	}

}
