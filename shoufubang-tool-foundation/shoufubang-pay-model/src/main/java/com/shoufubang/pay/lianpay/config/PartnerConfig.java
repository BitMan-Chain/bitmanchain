package com.shoufubang.pay.lianpay.config;

/**
* 商户配置信息
* @author guoyx e-mail:guoyx@lianlian.com
* @date:2013-6-25 下午01:45:40
* @version :1.0
*
*/
public class PartnerConfig{
	
    // 银通公钥
	public static   String YT_PUB_KEY     = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSS/DiwdCf/aZsxxcacDnooGph3d2JOj5GXWi+q3gznZauZjkNP8SKl3J2liP0O6rU/Y/29+IUe+GTMhMOFJuZm1htAtKiu5ekW0GlBMWxf4FPkYlQkPE0FtaoMP3gYfh+OwI+fIRrpW3ySn3mScnc6Z700nU/VYrRkfcSCbSnRwIDAQAB";
    // 商户私钥
	public static  String TRADER_PRI_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOilN4tR7HpNYvSBra/DzebemoAiGtGeaxa+qebx/O2YAdUFPI+xTKTX2ETyqSzGfbxXpmSax7tXOdoa3uyaFnhKRGRvLdq1kTSTu7q5s6gTryxVH2m62Py8Pw0sKcuuV0CxtxkrxUzGQN+QSxf+TyNAv5rYi/ayvsDgWdB3cRqbAgMBAAECgYEAj02d/jqTcO6UQspSY484GLsL7luTq4Vqr5L4cyKiSvQ0RLQ6DsUG0g+Gz0muPb9ymf5fp17UIyjioN+ma5WquncHGm6ElIuRv2jYbGOnl9q2cMyNsAZCiSWfR++op+6UZbzpoNDiYzeKbNUz6L1fJjzCt52w/RbkDncJd2mVDRkCQQD/Uz3QnrWfCeWmBbsAZVoM57n01k7hyLWmDMYoKh8vnzKjrWScDkaQ6qGTbPVL3x0EBoxgb/smnT6/A5XyB9bvAkEA6UKhP1KLi/ImaLFUgLvEvmbUrpzY2I1+jgdsoj9Bm4a8K+KROsnNAIvRsKNgJPWd64uuQntUFPKkcyfBV1MXFQJBAJGs3Mf6xYVIEE75VgiTyx0x2VdoLvmDmqBzCVxBLCnvmuToOU8QlhJ4zFdhA1OWqOdzFQSw34rYjMRPN24wKuECQEqpYhVzpWkA9BxUjli6QUo0feT6HUqLV7O8WqBAIQ7X/IkLdzLa/vwqxM6GLLMHzylixz9OXGZsGAkn83GxDdUCQA9+pQOitY0WranUHeZFKWAHZszSjtbe6wDAdiKdXCfig0/rOdxAODCbQrQs7PYy1ed8DuVQlHPwRGtokVGHATU=";
    // MD5 KEY
	public static  String MD5_KEY        = "201408071000001546_test_20140815";
    // 接收异步通知地址
	public static  String NOTIFY_URL     = "http://ip:port/wepdemo/notify.htm";
    // 支付结束后返回地址
	public static   String URL_RETURN     = "http://ip:port/wepdemo/urlReturn.jsp";
    // 商户编号
	public static  String OID_PARTNER    = "201408071000001546";
    // 签名方式 RSA或MD5
	public static  String SIGN_TYPE      = "RSA";
    // 接口版本号，固定1.0
	public static   String VERSION        = "1.0";

    // 业务类型，连连支付根据商户业务为商户开设的业务类型； （101001：虚拟商品销售、109001：实物商品销售、108001：外部账户充值）

	public static   String BUSI_PARTNER   = "101001";
    
	public static  String VALID_ORDER ="30";

	public  String getYT_PUB_KEY() {
		return YT_PUB_KEY;
	}

	public  void setYT_PUB_KEY(String yT_PUB_KEY) {
		YT_PUB_KEY = yT_PUB_KEY;
	}

	public  String getTRADER_PRI_KEY() {
		return TRADER_PRI_KEY;
	}

	public  void setTRADER_PRI_KEY(String tRADER_PRI_KEY) {
		TRADER_PRI_KEY = tRADER_PRI_KEY;
	}

	public  String getMD5_KEY() {
		return MD5_KEY;
	}

	public  void setMD5_KEY(String mD5_KEY) {
		MD5_KEY = mD5_KEY;
	}

	public  String getNOTIFY_URL() {
		return NOTIFY_URL;
	}

	public  void setNOTIFY_URL(String nOTIFY_URL) {
		NOTIFY_URL = nOTIFY_URL;
	}

	public  String getURL_RETURN() {
		return URL_RETURN;
	}

	public  void setURL_RETURN(String uRL_RETURN) {
		URL_RETURN = uRL_RETURN;
	}

	public  String getOID_PARTNER() {
		return OID_PARTNER;
	}

	public  void setOID_PARTNER(String oID_PARTNER) {
		OID_PARTNER = oID_PARTNER;
	}

	public  String getSIGN_TYPE() {
		return SIGN_TYPE;
	}

	public  void setSIGN_TYPE(String sIGN_TYPE) {
		SIGN_TYPE = sIGN_TYPE;
	}

	public  String getVERSION() {
		return VERSION;
	}

	public  void setVERSION(String vERSION) {
		VERSION = vERSION;
	}

	public  String getBUSI_PARTNER() {
		return BUSI_PARTNER;
	}

	public  void setBUSI_PARTNER(String bUSI_PARTNER) {
		BUSI_PARTNER = bUSI_PARTNER;
	}

	public  String getVALID_ORDER() {
		return VALID_ORDER;
	}

	public  void setVALID_ORDER(String vALID_ORDER) {
		VALID_ORDER = vALID_ORDER;
	}

}
