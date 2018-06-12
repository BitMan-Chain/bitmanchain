package com.shoufubang.model.eth.web3j;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.TypeDecoder;
import org.web3j.abi.TypeEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.TypeReference.StaticArrayTypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.CipherException;
import org.web3j.utils.Convert;

import com.alibaba.fastjson.JSONObject;
import com.shoufubang.model.exception.EthContractException;
import com.shoufubang.model.util.JSONUtils;
import com.shoufubang.model.util.Result;
import com.sun.mail.imap.protocol.BODY;

import javafx.print.JobSettings;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author lishuai 
 * @qq  314397644
 */
public class EthUtilTest {

    private static Logger log = LoggerFactory.getLogger(EthUtilTest.class);
    //测试网络
    //private static final  String contractAddress = "0x65623daf4c2d623982e19da7d9552bda5e1655c9";
    //主网络
     private static final  String contractAddress = "0x76c6bd07fd3c78d439fe32b790e4213f30ab39db";
   
    
    
    /** 
     * @param mainAccount 被查询账户
     * @return BigDecimal 账户余额
     * @throws Exception
     */
    public static BigDecimal GetTokenBalance(String mainAccount) throws Exception {
        String functionName = "balanceOf";
        List<Type> inputParams = Arrays.asList((Type)new Address(mainAccount));
        List<TypeReference<?>> outParams = new ArrayList<>(1);
        outParams.add((TypeReference) new TypeReference<Uint256>() {});
         //查询者地址  合约地址 方法名称 查询地址  输出格式
        List<Type> call = EthUtil.call(mainAccount, contractAddress, functionName, inputParams,outParams);
        log.info("call result==>{}", JsonUtils.objectToJson(call));
        return Convert.fromWei(call.get(0).getValue().toString(), Convert.Unit.ETHER);
        
    }        
    
    /**  
     * @param from  转账人地址
     * @param to    到账人地址
     * @param password 转账人密码
     * @param amount 转账数量
     * @return   hash串---------->EthUtil.getTransactionReceipt(hashString)
     * @throws EthContractException
     * @throws CipherException
     */
    public static String tokenTransferFrom(String from,String to, String password, BigDecimal amount) throws EthContractException, CipherException {
	            BigInteger transferAmount = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger();
	            String result = null;
				try {
					 List<TypeReference<?>> outParams = new ArrayList<>(1);
				     outParams.add((TypeReference) new TypeReference<Bool>() {});
	                 List<Type> inputParams = Arrays.asList((Type)new Address(to),new Uint256(transferAmount));
					 result = EthUtil.sendRawTransaction(from,contractAddress, password, "transfer",inputParams,outParams);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	             return result;
    }
    /** batchTransferToken 批量转账
     * @param from   转账人地址
     * @param password  密码
     * @param toList   字符串 地址数组
     * @param tokensList 币数组
     * @return
     * @throws CipherException 
     */
    public static String batchTransferToken(String from,String password,String toAddress, String tokens) throws CipherException{
    	  List<Uint256> transferAmount = new ArrayList<Uint256>();
    	  List<String> listAddressString = Arrays.asList(toAddress.split(","));
    	  List<Address> addressList = new ArrayList<Address>();
    	  List<String> listTokens =Arrays.asList(tokens.split(","));
    	  String result = null;
    	  System.out.println("listAddressString.size()"+listAddressString.size()+"-----------listTokens.size()"+listTokens.size());
    	  if(listAddressString.size() != listTokens.size()){
  			return new Result().fail("请检查数组内容");
  		  }
    	  for (int i= 0;i<listTokens.size();i++){
    		  addressList.add(new Address(listAddressString.get(i)));
    		  transferAmount.add(new Uint256(Convert.toWei(listTokens.get(i), Convert.Unit.ETHER).toBigInteger()));
    	  }
    	  List<Type> inputParams = new ArrayList<Type>();
    	  DynamicArray<Type> Dir=new DynamicArray(addressList);
    	  DynamicArray<Type> tfa=new DynamicArray(transferAmount);
    	  inputParams.add(Dir);
    	  inputParams.add(tfa);
    	  System.out.println(inputParams.toString());
		try {
			 List<TypeReference<?>> outParams = new ArrayList<>(1);
		     outParams.add((TypeReference) new TypeReference<Bool>() {});
			// List<TypeReference<?>> outParams = Arrays.asList((TypeReference<?>)new TypeReference<Bool>() {});
			 result = EthUtil.sendRawTransaction(from,contractAddress, password, "batchTransferToken",inputParams,outParams);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
    	
    }
    
 
    /**
     * @param from
     * @param to
     * @param password
     * @param amount
     * @return
     * @throws EthContractException
     * @throws CipherException
     */
    public static String freezeAccount(String from,String to, String password, Bool bool) throws EthContractException, CipherException {
        String result = null;
		try {
			 List<TypeReference<?>> outParams = new ArrayList<>(1);
		     outParams.add((TypeReference) new TypeReference<Bool>() {});
             List<Type> inputParams = Arrays.asList((Type)new Address(to),bool);
			 result = EthUtil.sendRawTransaction(from,contractAddress, password, "freezeAccount",inputParams,outParams);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return result;
}
    
}
