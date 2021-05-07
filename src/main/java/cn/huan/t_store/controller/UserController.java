package cn.huan.t_store.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.huan.t_store.controller.ex.FileIOException;
import cn.huan.t_store.controller.ex.FileIsEmptyException;
import cn.huan.t_store.controller.ex.FileSizeException;
import cn.huan.t_store.controller.ex.FileStateException;
import cn.huan.t_store.controller.ex.FileTypeException;
import cn.huan.t_store.entity.User;
import cn.huan.t_store.service.UserService;
import cn.huan.t_store.util.JsonResult;



@RestController
@RequestMapping("users")  //用于增加一层父级路径
public class UserController extends BaseController{

	@Autowired
	private UserService userService;  //只有一份且不会改变


	/**
	 * 	显示当前用户
	 */
	@RequestMapping("showUser")
	public JsonResult<User> showUser(HttpSession session) {
		User user = new User();
		String username = session.getAttribute("username").toString();
		user.setUsername(username);
		return new JsonResult<>(OK, user);
	}

		
	@RequestMapping("reg")
	public JsonResult<Void> reg(User user) {    //因为不需要返回任何数据，所以写Void
		System.out.println("UserController.reg()");
		System.err.println(user);
		userService.reg(user);
		return new JsonResult<Void>(OK);
	}
	
	@RequestMapping("login")  //登录时还需要保存用户登录成功的信息
	public JsonResult<User> login(String username,String password,HttpSession session){
		JsonResult<User> jsonResult = new JsonResult<User>();
		User data = userService.login(username, password);
		jsonResult.setState(OK);
		jsonResult.setData(data);
		System.err.println("登录成功,user="+ data);
		//只有将主要信息放入session中，下次登录时才能够自动识别到登录的用户
		session.setAttribute("uid", data.getUid());  //后续中会用到 uid 和username
		session.setAttribute("username", username);
		return jsonResult;
	}
	
	
	@RequestMapping("password/change")
	public JsonResult<Void> changePwd(String oldPassword,String newPassword,HttpSession session){
		JsonResult<Void> jsonResult = new JsonResult<>();
		//获取在登录时存入在session中的 username和 uid
		String username = session.getAttribute("username").toString();
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		userService.changePassword(uid, username, oldPassword, newPassword);
		jsonResult.setState(OK);
		return jsonResult;
	}
	
	
	
	@GetMapping("info/show")
	public JsonResult<User> showMessage(HttpSession session){
		JsonResult<User> jsonResult = new JsonResult<User>();
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		User user = userService.showInfo(uid);
		jsonResult.setState(OK);
		jsonResult.setData(user);
		return jsonResult;
	}
	
	
	@RequestMapping("info/change")
	public JsonResult<Void> updateMessage(String phone,String email,Integer gender,HttpSession session){
		JsonResult<Void> jsonResult = new JsonResult<>();
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();
		userService.changeInfo(uid, username, phone, email, gender);
		jsonResult.setState(OK);
		return jsonResult;
	}
	
	
	
	
	/**
	 * 上传头像
	 */
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Value("${project.avatar-max-size}")
	private long maxSize;
	
	//静态方法限制问价类型
//	private static  List<String> types = new ArrayList<String>();
//	static {
//		types.add("image/png");
//		types.add("image/jpeg");
//	}
	
	//配置问价中写限制文件的类型
	@Value("${project.avatar-type}")
	private List<String> types;
	
	
	@PostMapping("avatar/change")
	public JsonResult<String> changeAvatar(MultipartFile file,HttpSession session){
		
		//先判断 用户选择的文件是否文空
		boolean isEmpty = file.isEmpty();
		if (isEmpty) { 
			//如果文件为空，则返回true，则抛出异常,在控制器包下创建ex异常包，
			//并创建一个基类异常且继承RuntimeException
			throw new FileIsEmptyException("上传头像失败，请选择有效头像文件...");
		}
		
		//以字节为单位,用于对上传文件的大小限制
		long size = file.getSize();
		System.err.println("Size:"+size);
		if (size > maxSize) { 
			//如果文件为空，则返回true，则抛出异常,在控制器包下创建ex异常包，
			//并创建一个基类异常且继承RuntimeException
			throw new FileSizeException("上传头像失败，不允许上传"+maxSize/1024+"KB以内的文件...");
		}
		
		//判断客户端上传文件的类型， 允许上传文件：image/png , image/jpeg
		String contendType = file.getContentType();
		System.err.println(contendType);
		//将允许上传的文件类型放入list集合中

		//如果list集合中，不包含用户选择的文件类型，则抛出异常
		if (!types.contains(contendType)) {
			throw new FileTypeException("上传头像失败，请用指定格式的文件！可用类型文件有:"+types);
		}
		
		
		//确定将客户端上传的文件保存在哪个文件夹
		// 用session去获取得到webapp的路径，实现了不同电脑都能够访问统一个路径
		// 下面代码得到的parent路劲为 ： xxxxxx(根路径)/webapp/upload
		String parent = session.getServletContext().getRealPath("upload");
		//将 根目录下webapp下的upload作为路径放入，
		File parentFile = new File(parent);
		//判断一下 是否存在webapp下的upload文件夹,若不存在则创建一个
		if (!parentFile.exists()) {  //如果不存在，则创建
			parentFile.mkdirs();
		}
		
		//确定将客户端上传的文件定义为什么名字
		
		//通过UUID方式，解决文件名被覆盖问题：将String child = "1.jpg"; 查分为两个String，用于解决上传时图片覆盖的问题
		//String filename = UUID.randomUUID().toString();   //可以用当前用户的用户名为图片的名字

		//通过时间秒的方式，防止上传头像覆盖: (推荐)
		Date date = new Date();
		String filename = sdf.format(date)+System.nanoTime();
		
		//确定将客户端上传的文件保存时使用什么扩展名： 使用原扩展名(用户在选择文件时的扩展名)
		//获得用户在选择文件时的文件扩展名
		String originalFilename = file.getOriginalFilename();
		System.err.println("\toriginalFilename:"+originalFilename);
		
		String suffix = "";
		//得到最后一个 . 结尾的坐标
		int beginIndex = originalFilename.lastIndexOf(".");
		// 下标不为 -1时才进行截取，且可能为 .hello(隐藏文件)，所以需要>0才能够截取
		if (beginIndex > 0 ) {  //可能会出现没有扩展名请情况，则会出现个下标错误 
			//根据最后一个 . 的左边，截取获取到选择文件时的后缀名
			suffix = originalFilename.substring(beginIndex);
		}
		
		String child = filename + suffix;
		
		//确定将可客户端上传的文件保存在哪里
		File dest = new File(parent,child);
		try {
			file.transferTo(dest);  //将客户端上传的文件保存并命名为 dest中的属性
		} catch (IllegalStateException e) {
			//捕获到异常就抛出异常 
			throw new FileStateException("上传失败，请检查上传文件是否正常...");
		} catch (IOException e) {
			//
			throw new FileIOException("上传失败，传输过程出现错误，请稍后再试...");
		}
		

		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();
		//child ：是图片的 全名(名称+扩展名)
		//最后，将保存到webapp/upload下的图片的路径存到数据库中
		String avatar = "/upload/"+ child;
		userService.changeAvatar(uid, username, avatar);
		JsonResult<String> jsonResult = new JsonResult<String>();
		jsonResult.setState(OK);
		jsonResult.setData(avatar);
		return jsonResult;
	}
	
	
	
	
}
