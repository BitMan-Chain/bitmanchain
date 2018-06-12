package com.shoufubang.model.util;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.Properties;


public class SystemInfo {
	/**系统名*/
	private String os_name;
	/**系统架构*/
	private String os_arch ;
	/**系统版本号*/
	private String os_version ;
	/**系统IP*/
	private String os_ip ;
	/**系统MAC地址*/
	private String os_mac;
	/**系统时间*/
	private Date os_date;
	/**系统CPU个数*/
	private Integer os_cpus ;
	/**系统用户名*/
	private String os_user_name;
	/**用户的当前工作目录*/
	private String os_user_dir;
	/**用户的主目录*/
	private String os_user_home;
	
	/**Java的运行环境版本*/
	private String java_version ;
	/**java默认的临时文件路径*/
	private String java_io_tmpdir;
	
	/**java 平台*/
	private String sun_desktop ;
	
	/**文件分隔符  在 unix 系统中是＂／＂*/
	private String file_separator;
	/**路径分隔符  在 unix 系统中是＂:＂*/
	private String path_separator;
	/**行分隔符   在 unix 系统中是＂/n＂*/
	private String line_separator;
	
	
	public static SystemInfo systeminfo;
	
	public static SystemInfo getInstance(){
		if(systeminfo == null){
			systeminfo = new SystemInfo();
		}
		return systeminfo;
	}
 
	public String getOs_name() {
		return os_name;
	}

	public void setOs_name(String os_name) {
		this.os_name = os_name;
	}

	public String getOs_arch() {
		return os_arch;
	}

	public void setOs_arch(String os_arch) {
		this.os_arch = os_arch;
	}

	public String getOs_version() {
		return os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

	public String getOs_ip() {
		return os_ip;
	}

	public void setOs_ip(String os_ip) {
		this.os_ip = os_ip;
	}

	public String getOs_mac() {
		return os_mac.replace("-", "");
	}

	public void setOs_mac(String os_mac) {
		this.os_mac = os_mac;
	}

	public Date getOs_date() {
		return os_date;
	}

	public void setOs_date(Date os_date) {
		this.os_date = os_date;
	}

	public Integer getOs_cpus() {
		return os_cpus;
	}

	public void setOs_cpus(Integer os_cpus) {
		this.os_cpus = os_cpus;
	}

	public String getOs_user_name() {
		return os_user_name;
	}

	public void setOs_user_name(String os_user_name) {
		this.os_user_name = os_user_name;
	}

	public String getOs_user_dir() {
		return os_user_dir;
	}

	public void setOs_user_dir(String os_user_dir) {
		this.os_user_dir = os_user_dir;
	}

	public String getOs_user_home() {
		return os_user_home;
	}

	public void setOs_user_home(String os_user_home) {
		this.os_user_home = os_user_home;
	}

	public String getJava_version() {
		return java_version;
	}

	public void setJava_version(String java_version) {
		this.java_version = java_version;
	}

	public String getJava_io_tmpdir() {
		return java_io_tmpdir;
	}

	public void setJava_io_tmpdir(String java_io_tmpdir) {
		this.java_io_tmpdir = java_io_tmpdir;
	}

	public String getSun_desktop() {
		return sun_desktop;
	}

	public void setSun_desktop(String sun_desktop) {
		this.sun_desktop = sun_desktop;
	}

	public String getFile_separator() {
		return file_separator;
	}

	public void setFile_separator(String file_separator) {
		this.file_separator = file_separator;
	}

	public String getPath_separator() {
		return path_separator;
	}

	public void setPath_separator(String path_separator) {
		this.path_separator = path_separator;
	}

	public String getLine_separator() {
		return line_separator;
	}

	public void setLine_separator(String line_separator) {
		this.line_separator = line_separator;
	}

	private SystemInfo() {
		super();
		init();
	}
	
 
	/**
	 * 初始化基本属性
	 */
	private void init(){
		Properties props=System.getProperties();
		this.java_version = props.getProperty("java.version");
		this.java_io_tmpdir = props.getProperty("java.io.tmpdir");
		this.os_name = props.getProperty("os.name");
		this.os_arch = props.getProperty("os.arch");
		this.os_version = props.getProperty("os.version");
		this.file_separator = props.getProperty("file.separator");
		this.path_separator = props.getProperty("path.separator");
		this.line_separator = props.getProperty("line.separator");
		this.os_user_name = props.getProperty("user.name");
		this.os_user_home = props.getProperty("user.home");
		this.os_user_dir = props.getProperty("user.dir");
		this.sun_desktop = props.getProperty("sun.desktop");
		this.os_date = new Date();
		this.os_cpus = Runtime.getRuntime().availableProcessors();
		try {
			ipMac();
		} catch (Exception e) {
			this.os_ip = "";
			this.os_mac = "";
		}
	}
	
	/**
	 * 获取ip和mac地址
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	private void ipMac() throws Exception{
		InetAddress address = InetAddress.getLocalHost();
		NetworkInterface ni = NetworkInterface.getByInetAddress(address);
		ni.getInetAddresses().nextElement().getAddress();
		byte[] mac = ni.getHardwareAddress();
		String sIP = address.getHostAddress();
		String sMAC = "";
		Formatter formatter = new Formatter();
		for (int i = 0; i < mac.length; i++) {
			sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i],
					(i < mac.length - 1) ? "-" : "").toString();

		}
		SystemInfo.this.os_ip = sIP;
		SystemInfo.this.os_mac = sMAC.replace("-", "");
	}
 
	
}