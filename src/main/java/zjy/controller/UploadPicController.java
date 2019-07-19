package zjy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import zjy.tools.IPUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UploadPicController extends BaseController {

	private static boolean flag = true;

	@Value("${file.path}")
	private String filePath;

	@Value("${file.host}")
	private String host;

	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(MultipartFile file, HttpServletRequest request){
		if (!flag) throw new RuntimeException("错误");
		if (file == null){
			return "{success:0,mess:'未选择文件'}";
		}
		String fileName = IPUtils.getIpAddr(request).replaceAll("[:\\.]","_") + "I_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".jpeg";
		try {
			file.transferTo(new File(filePath+fileName));
		} catch (IOException e) {
			e.printStackTrace();
			return "{success:0}";
		}
		return "{success:1}";
	}

/*	@RequestMapping("/pic")
	@ResponseBody
	public List<String> getAllPic(){
		File file = new File(filePath);
		List<String> tmp = Arrays.asList(file.list());
		List<String> res = new ArrayList<>();
		tmp.forEach((item) -> {
			res.add(filePath + item);
		});
		return res;
	}*/

	@RequestMapping(value = "/pic/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImage(@PathVariable("fileName") String fileName){
		try {
			return exportBytes(new File(filePath + fileName+".jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return exportBytes(ResourceUtils.getFile("classpath:404.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	@RequestMapping("/flag/{value}")
	@ResponseBody
	public String transFlag(@PathVariable("value") int flagNum){
		flag = flagNum == 1;
		return "{success:1}";
	}

	@RequestMapping("/picList")
	public String getFileList(ModelMap modelMap){
		File file = new File(filePath);
		List<String> tmp = Arrays.asList(file.list());
		List<Map<String,Object>> res = new ArrayList<>();
		tmp.forEach((item) -> {
			Map m = new HashMap();
			m.put("url",host + item);
			m.put("ip",item.substring(0,item.indexOf("I")));
			res.add(m);
		});
		modelMap.addAttribute("picList",res);
		return "picList";
	}
}
