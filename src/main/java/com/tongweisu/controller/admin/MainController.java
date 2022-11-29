package com.tongweisu.controller.admin;

import com.sun.management.OperatingSystemMXBean;
import com.tongweisu.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Controller
@RequestMapping("/admin")
public class MainController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AdminService adminService;



	@RequestMapping("/main")
	public String main(Model model,HttpServletRequest request) throws UnknownHostException {

		//用 getLocalHost() 方法创建的InetAddress的对象
		InetAddress address = InetAddress.getLocalHost();
		model.addAttribute("hostName",address.getHostName());////主机名
		model.addAttribute("CanonicalHostName",address.getCanonicalHostName());//主机别名

		model.addAttribute("hostAddress",address.getHostAddress());//获取IP地址
		model.addAttribute("serverName",request.getServerName());//服务器域名
		model.addAttribute("localPort",request.getLocalPort());//服务器端口

		//jdk信息
		Properties props=System.getProperties(); //系统属性
		model.addAttribute("java_version",props.getProperty("java.version"));
		model.addAttribute("java_vendor",props.getProperty("java.vendor"));
		model.addAttribute("vendor_url",props.getProperty("java.vendor.url"));
		model.addAttribute("java_home",props.getProperty("java.home"));
		model.addAttribute("vm_specification_version",props.getProperty("java.vm.specification.version"));
		model.addAttribute("vm_specification_vendor",props.getProperty("java.vm.specification.vendor"));
		model.addAttribute("vm_specification_name",props.getProperty("java.vm.specification.name"));
		model.addAttribute("vm_version",props.getProperty("java.vm.version"));
		model.addAttribute("vm_vendor",props.getProperty("java.vm.vendor"));
		model.addAttribute("vm_name",props.getProperty("java.vm.name"));
		model.addAttribute("specification_version",props.getProperty("java.specification.version"));
		model.addAttribute("specification_vendor",props.getProperty("java.specification.vendor"));
		model.addAttribute("specification_name",props.getProperty("java.specification.name"));
		model.addAttribute("class_version",props.getProperty("java.class.version"));
		model.addAttribute("class_path",props.getProperty("java.class.path"));
		model.addAttribute("library_path",props.getProperty("java.library.path"));
		model.addAttribute("io_tmpdir",props.getProperty("java.io.tmpdir"));
		model.addAttribute("compiler",props.getProperty("java.compiler"));
		model.addAttribute("ext_dirs",props.getProperty("java.ext.dirs"));
		//系统信息
		model.addAttribute("os_arch",props.getProperty("os.arch"));
		model.addAttribute("os_version",props.getProperty("os.version"));
		model.addAttribute("user_name",props.getProperty("user.name"));
		model.addAttribute("user_home",props.getProperty("user.home"));
		model.addAttribute("user_dir",props.getProperty("user.dir"));
		model.addAttribute("os_name",props.getProperty("os.name"));//操作系统


		model.addAttribute("availableProcessors",Runtime.getRuntime().availableProcessors());//cpu总数
		model.addAttribute("startDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date(ManagementFactory.getRuntimeMXBean().getStartTime())));

		Runtime run = Runtime.getRuntime();
		long max = run.maxMemory();
		long total = run.totalMemory();
		long free = run.freeMemory();
		long usable = max - total + free;
		model.addAttribute("max",max/1024/1024+"M");//最大内存
		model.addAttribute("total",total/1024/1024+"M");//已分配内存
		model.addAttribute("free",free/1024/1024+"M");//剩余空间
		model.addAttribute("usable",usable/1024/1024+"M");//可用最大内存



		// 总的物理内存
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		String totalMemorySize = new DecimalFormat("#.##")
				.format(osmxb.getTotalPhysicalMemorySize() / 1024.0 / 1024 / 1024) + "G";
		model.addAttribute("totalMemorySize",totalMemorySize);//总的物理内存
		// 剩余的物理内存
		String freePhysicalMemorySize = new DecimalFormat("#.##")
				.format(osmxb.getFreePhysicalMemorySize() / 1024.0 / 1024 / 1024) + "G";
		model.addAttribute("freePhysicalMemorySize",freePhysicalMemorySize);// 剩余的物理内存
		// 已使用的物理内存
		String usedMemory = new DecimalFormat("#.##").format(
				(osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / 1024.0 / 1024 / 1024)
				+ "G";
		model.addAttribute("usedMemory",usedMemory);
		// 获得线程总数
		ThreadGroup parentThread;
		for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
				.getParent() != null; parentThread = parentThread.getParent()) {

		}
		int totalThread = parentThread.activeCount();
		model.addAttribute("totalThread",totalThread);//获得线程总数
		return "admin/main";
	}




}
