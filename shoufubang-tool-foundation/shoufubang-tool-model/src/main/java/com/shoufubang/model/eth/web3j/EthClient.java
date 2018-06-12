package com.shoufubang.model.eth.web3j;
import java.math.BigDecimal;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.JsonRpc2_0Admin;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.http.HttpService;


public class EthClient {
    private volatile static Web3j web3j;
    private volatile static Admin admin = null;
    private static String RPC_URL = "https://mainnet.infura.io/0x76C6Bd07fD3c78D439fE32B790e4213F30ab39DB";
    //private static String RPC_URL = "https://ropsten.infura.io/0x65623DaF4c2D623982e19Da7d9552bda5E1655c9";
    
    public static Web3j getWeb3jClient() {
        if (web3j == null) {
            synchronized (EthUtil.class) {
                if (web3j == null) {
                    web3j = new JsonRpc2_0Web3j(new HttpService(RPC_URL));
                }
            }
        }
        return web3j;
    }
    
    
  
    public static Admin getAdminClient() {
        if (admin == null) {
            synchronized (EthUtil.class) {
                if (admin == null) {
                    admin =  new JsonRpc2_0Admin(new HttpService(RPC_URL));
                }
            }
        }
        return admin;
    }

    public static void main(String[] args) throws Exception{
    	
    	      // System.out.println((EthUtil.getBalance("0x87237b3573bc0c1135a4f85c3954854bd195765c")));
    	        
    	    	EthUtilTest ee = new EthUtilTest();
    	    	//System.out.println("balance:"+ee.testGetTokenBalance("0x5a62f07785a8255e6c12db988c0361e237d49364"));
    	     	//String reString=   ee.tokenTransferFrom("0x5a62f07785a8255e6c12db988c0361e237d49364", "0x33efd42ca971e76843188e7dc12c709d14a9a2ac", "12345678A", new BigDecimal("2"));  
    	    	//主网
    	    	//String reString=   ee.tokenTransferFrom("0x87237b3573bc0c1135a4f85c3954854bd195765c", "0x482b3b4c191f31f76e736f0784dffde2b1759b63", "12345678A", new BigDecimal("100"));  	
    	    	//String ethTansfer=  EthUtil.transfer("0x87237b3573bc0c1135a4f85c3954854bd195765c", "12345678A", "0x33efd42ca971e76843188e7dc12c709d14a9a2ac", 0.01);
    	    	//System.out.println("查询返回hash串："+reString);
//    	    	Credentials credentials = WalletUtils.loadCredentials("password", "/path/to/walletfile");
//    	    	TransactionReceipt transactionReceipt = Transfer.sendFunds(
//    	    	        web3, credentials, "0x<address>|<ensName>",
//    	    	        BigDecimal.valueOf(1.0), Convert.Unit.ETHER)
//    	    	        .send();
    	    	//通过hash 值查询 转账结果
    	  
    	  // String address = "0xc1aD7FE67D08a93Ac5f13278086ba72E90EfCD48,0x02b54d0d5e6297Bda8CcDcF4B8018Ed65fF19b06,0xDfbaa7659139977AdD8894533c6465bdcE38a0fC,0x860EBA2A25b322933c8B1EB605E373a26031E36A,0xFC9C4C0e17c3A3139a77d86282eCf18687C14780,0xc9017f1F18dC2abD372128599E7C961bd20F7772,0xFe6BBeE1c9401f5740da0020ce7761169308a9c4,0xf3B63d225f1B721FdC52e19f89088A97Dc13208b,0x9A58acaCFd4C04A6c0E9aFA6Aba946Fb610443E9,0x87a187137FA3F3a066d32f8724a9f1Ba81849EC3,0x9c86b21d474072ED1a6AF821024E90eA07819680,0x76faCd29F6fF7b79e6d63d39B8a8E3D96BF14654,0x76c6bd07fd3c78d439fe32b790e4213f30ab39db";
    	   //String money="8888,8888,8888,8888,8888,8888,8888,8888,8888,8888,8888,8888,8888";
    	    	
    	    	String address = "0x482b3B4C191f31F76E736F0784dfFde2b1759b63";
    	    	String money="123";
    	    	String xx =EthUtilTest.batchTransferToken("0x87237b3573bc0c1135a4f85c3954854bd195765c", "12345678A", address, money);
    	    	System.out.println(xx);
    	    	//System.out.println(EthUtil.getTransactionReceipt("0x76abd7e88d7bb3c51c20ce24a3ba586ba01fa15e797d6adb4d934ad3c629bba5"));
    }
    
    
}
