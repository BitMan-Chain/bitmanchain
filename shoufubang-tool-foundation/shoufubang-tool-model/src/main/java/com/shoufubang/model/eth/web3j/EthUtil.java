package com.shoufubang.model.eth.web3j;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetStorageAt;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import com.alibaba.fastjson.JSONObject;

import rx.Observable;
 
//@Slf4j
public class EthUtil {

    private static Logger log = LoggerFactory.getLogger(EthUtil.class);

    private static int WEI = 18;
    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(3000000);
    public static final BigInteger GAS_PRICE = BigInteger.valueOf(3_000_000_000L);
   
    public static void main(String[] args) throws IOException {
//        getClientVersion();
//        getAccounts();
//        getBalance("0xcf7a554f74c63d4a7e16ad43310b6d2a6877cab3");
        unlockAccount("0xcf7a554f74c63d4a7e16ad43310b6d2a6877cab3", "987654321");
    }

    /**
     * 客户端版本号
     */
    public static void getClientVersion() {
        Web3j web3j = EthClient.getWeb3jClient();
        Web3ClientVersion web3ClientVersion = null;
        try {
            web3ClientVersion = web3j.web3ClientVersion().send();
        } catch (IOException e) {
            log.error("exception==>:", e);
        }
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        log.info("clientVersion==>{}", clientVersion);
    }

    /**
     * 列出所有账户
     */
    public static List<String> getAccounts() throws IOException {
        Web3j web3j = EthClient.getWeb3jClient();
        Request<?, EthAccounts> ethAccountsRequest = web3j.ethAccounts();
        EthAccounts response = ethAccountsRequest.send();
        if (!response.hasError()) {
            log.info("ethAccounts==>{}", response.getResult());
            return response.getResult();
        }

        logError(response);
        return null;
    }

    /**
     * 账户余额
     */
    public static BigInteger getBalance(String accountAddress) throws Exception {
        Admin admin = EthClient.getAdminClient();
        Request<?, EthGetBalance> ethGetBalanceRequest = admin.ethGetBalance(accountAddress, DefaultBlockParameterName.LATEST);
        EthGetBalance response = ethGetBalanceRequest.sendAsync().get();
        if (!response.hasError()) {
            log.info("balance==>{}", response.getBalance());
            return response.getBalance();
        }

        logError(response);
        return BigInteger.ZERO;
    }

    /**
     * 新建账户
     *
     * @param password
     * @return
     * @throws Exception
     */
    public static boolean newAccount(String password) throws Exception {
        Admin admin = EthClient.getAdminClient();
        NewAccountIdentifier response = admin.personalNewAccount(password).sendAsync().get();
        System.out.println(JSONObject.toJSON(response));
        if (!response.hasError()) {
            log.info("account==>{}", response.getAccountId());
            return true;
        }

        logError(response);
        return false;
    }

    /**
     * 账户解锁
     *
     * @param accountAddress
     * @param password
     * @throws IOException
     */
    public static boolean unlockAccount(String accountAddress, String password) throws IOException {
        Admin admin = EthClient.getAdminClient();
        Request<?, PersonalUnlockAccount> personalUnlockAccountRequest = admin.personalUnlockAccount(accountAddress, password);
        PersonalUnlockAccount response = personalUnlockAccountRequest.send();
        if (!response.hasError()) {
            log.info("result==>{}", response.accountUnlocked());
            return response.accountUnlocked();
        }

        logError(response);
        return false;
    }

    /**
     * 调用合约
     *
     * @param from
     * @param to
     * @param functionName
     * @param inputParams  入参
     * @return
     * @throws IOException
     */
    public static String call(String from, String to, String functionName,
                              List<Type> inputParams) throws IOException {
        Admin admin = EthClient.getAdminClient();
        //List<TypeReference<?>> outParams = Arrays.asList((TypeReference<?>)new TypeReference<Type>(){});
        List<TypeReference<?>> outParams = new ArrayList<TypeReference<?>>();
        Function function = new Function(functionName, inputParams, outParams);
        String encode = FunctionEncoder.encode(function);
        Request<?, EthCall> ethCallRequest = admin.ethCall(Transaction.createEthCallTransaction(from, to, encode), DefaultBlockParameterName.LATEST);
        EthCall response = ethCallRequest.send();
        if (!response.hasError()) {
        	System.out.println(JSONObject.toJSON(response));
            return response.getValue();
        }
        logError(response);
        return null;
    }

    /**
     * 调用合约
     *
     * @param from
     * @param to
     * @param functionName
     * @param inputParams  入参
     * @param outParams    出参
     * @return
     * @throws IOException
     */
    public static List<Type> call(String from, String to, String functionName,
                               List<Type> inputParams, List<TypeReference<?>> outParams) throws IOException {
        Admin admin = EthClient.getAdminClient();
        Function function = new Function(functionName, inputParams, outParams);
        String encode = FunctionEncoder.encode(function);
        Request<?, EthCall> ethCallRequest = admin.ethCall(Transaction.createEthCallTransaction(from, to, encode), DefaultBlockParameterName.LATEST);
        EthCall response = ethCallRequest.send();
        if (!response.hasError()) {
        	System.out.println(FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters()));
            return FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());
        }
        logError(response);
        return null;
    }

    /**
     * 转账
     *
     * @param from
     * @param password
     * @param to
     * @param amount   unit ether
     * @return
     * @throws IOException
     * @throws CipherException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static String transfer(String from, String password, String to, Integer amount) throws IOException, CipherException, ExecutionException, InterruptedException {
        Admin admin = EthClient.getAdminClient();
        if (unlockAccount(from, password)) {
            BigInteger value = toWei(amount);
            Request<?, EthSendTransaction> ethSendTransactionRequest = admin.ethSendTransaction(Transaction.createEtherTransaction(from, null, null, null, to, value));
            EthSendTransaction response = ethSendTransactionRequest.sendAsync().get();
            if (!response.hasError()) {
                log.info("TransactionHash==>{}", response.getTransactionHash());
                return response.getTransactionHash();
            }
            logError(response);
        }
        return null;
    }


    /**
     * 发送事务，返回transactionHash
     *
     * @param from
     * @param to
     * @param password
     * @param functionName
     * @param inputParams
     * @param outParams
     * @return
     * @throws IOException
     */
    public static String sendTransaction(String from, String to, String password, String functionName,
                                         List<Type> inputParams, List<TypeReference<?>> outParams) throws IOException {
        Admin admin = EthClient.getAdminClient();
        Function function = new Function(functionName, inputParams, outParams);
        String encode = FunctionEncoder.encode(function);
        Request<?, EthSendTransaction> ethSendTransactionRequest = admin.personalSendTransaction(Transaction.createEthCallTransaction(from, to,encode), password);
       System.out.println(JSONObject.toJSON(ethSendTransactionRequest));
        EthSendTransaction response = ethSendTransactionRequest.send();
       System.out.println(JSONObject.toJSON(response));
        if (!response.hasError()) {
            log.info("TransactionHash==>{}", response.getTransactionHash());
            return response.getTransactionHash();
        }
        logError(response);
        return null;
    }

    
    public static String sendRawTransaction(String from, String to, String password, String functionName,
            List<Type> inputParams, List<TypeReference<?>> outParams) throws IOException, CipherException {
			Admin admin = EthClient.getAdminClient();
			Credentials credentials = WalletUtils.loadCredentials(password, "D:/workspace3/ethereum_wallet_backup.json");
		  	System.out.println("通过json文件获取凭证-----账户:"+credentials.getAddress());
			Function function = new Function(functionName, inputParams, outParams);
		
			String encode = FunctionEncoder.encode(function);
			     //getNonce（这里的Nonce我也不是很明白，大概是交易的笔数吧）
			     EthGetTransactionCount ethGetTransactionCount;
				try {
					ethGetTransactionCount = admin.ethGetTransactionCount(
							 credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
			
		         BigInteger nonce = ethGetTransactionCount.getTransactionCount();
			
		         System.out.println("nonce:"+nonce);
			     RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, GAS_PRICE, 
			    		 GAS_LIMIT, to, encode);
			System.out.println("请求："+JSONObject.toJSON(rawTransaction));
			      //签名Transaction，这里要对交易做签名
			        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
			        String hexValue = Numeric.toHexString(signedMessage);
			        
			       //发送交易
			        EthSendTransaction ethSendTransaction =
			        		admin.ethSendRawTransaction(hexValue).sendAsync().get();
			       System.out.println("发送json串："+JSONObject.toJSON(ethSendTransaction));
			       String transactionHash = ethSendTransaction.getTransactionHash();
			       return transactionHash;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			
			
			//Request<?, EthSendTransaction> ethSendTransactionRequest = admin.ethSendTransaction(Transaction.createEthCallTransaction(credentials.getAddress(), to,encode));
			
			//Request<?, EthSendTransaction> ethSendTransactionRequest = admin.personalSendTransaction(Transaction.createEthCallTransaction(from, to,encode), password);
			//System.out.println(JSONObject.toJSON(ethSendTransactionRequest));
			
			//TransactionManager transactionManager = new RawTransactionManager(web3j, credentials);
			//TransactionManager transactionManager = new RawTransactionManager(admin, credentials);
			//Request<?, EthCall> ethSendTransactionRequest =admin.ethCall(Transaction.createEthCallTransaction(credentials.getAddress(), to,encode), DefaultBlockParameterName.LATEST);
			//admin.ethSendRawTransaction(encode);
			//EthSendTransaction response = ethSendTransactionRequest.send();
//			System.out.println(JSONObject.toJSON(response));
//			if (!response.hasError()) {
//			log.info("TransactionHash==>{}", response.getRawResponse());
//			}
//			logError(response);
			
	}
    

    public static String sendTransactionOnCredentials (String from,String to, String password, String functionName,List<Type> inputParams, List<TypeReference<?>> outParams) throws IOException {
			Admin admin = EthClient.getAdminClient();
			try {
				 //获取凭证
				 Credentials credentials = WalletUtils.loadCredentials(password, "D:/workspace3/ethereum_wallet_backup.json");
			  	 System.out.println("账户:"+credentials.getAddress());
			     //转账
			  	
	    	    	TransactionReceipt transactionReceipt = Transfer.sendFunds(
	    	    			admin, credentials, "0x482b3b4c191f31f76e736f0784dffde2b1759b63",
	    	        BigDecimal.valueOf(0.01), Convert.Unit.ETHER)
	    	        .send();
	    	    	
	    	    	log.info("TransactionHash==>{}", transactionReceipt.getTransactionHash());
	    	    	
			  	 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return functionName;
			
	
}
    
    
    /**
     * 根据交易hash获取交易结果
     *
     * @param transactionHash
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static Optional<TransactionReceipt> getTransactionReceipt(String transactionHash) throws ExecutionException, InterruptedException {
        Admin admin = EthClient.getAdminClient();
        Request<?, EthGetTransactionReceipt> ethGetTransactionReceiptRequest = admin.ethGetTransactionReceipt(transactionHash);
        EthGetTransactionReceipt response = ethGetTransactionReceiptRequest.sendAsync().get();
        if (!response.hasError()) {
            log.info("TransactionReceipt==>{}", response.getTransactionReceipt());
            return response.getTransactionReceipt();
        }

        logError(response);
        return null;
    }

    public static String getStorageAt(String address, int positionIndex) throws IOException {
        Admin admin = EthClient.getAdminClient();
        Request<?, EthGetStorageAt> ethGetStorageAtRequest = admin.ethGetStorageAt(address, BigInteger.valueOf(positionIndex), DefaultBlockParameterName.LATEST);
        EthGetStorageAt response = ethGetStorageAtRequest.send();
        return response.getData();
    }

    private static void logError(Response response) {
        Response.Error error = response.getError();
        log.info("response_error==>code:{},message:{}", error.getCode(), error.getMessage());
    }

    private static BigInteger toWei(Integer value) {
        return Convert.toWei(BigDecimal.valueOf(value), Convert.Unit.ETHER).toBigInteger();
    }

    public static void blockObservable() {
        Admin admin = EthClient.getAdminClient();
        Observable<EthBlock> blockObservable = admin.blockObservable(true);
    }

}
