//package com.shoufubang.model.eth.web3j;
//
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.web3j.abi.TypeReference;
//import org.web3j.abi.datatypes.Address;
//import org.web3j.abi.datatypes.Bool;
//import org.web3j.abi.datatypes.Type;
//import org.web3j.abi.datatypes.generated.Uint256;
//import org.web3j.protocol.admin.Admin;
//import org.web3j.protocol.core.Request;
//import org.web3j.protocol.core.methods.request.EthFilter;
//import org.web3j.protocol.core.methods.response.EthMining;
//import org.web3j.protocol.core.methods.response.Log;
//import org.web3j.protocol.core.methods.response.TransactionReceipt;
//import org.web3j.utils.Numeric;
//import rx.Subscription;
//
//import java.io.IOException;
//import java.math.BigInteger;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by pengju on 2018/1/17
// */
//public class EthUtilTest2 {
//
//    private static Logger log = LoggerFactory.getLogger(EthUtilTest2.class);
//    private static String mainAccount = "0x5a62f07785a8255e6c12db988c0361e237d49364";
//    private static String password = "ibeesaas";
//
//    @Test
//    public void testNewAccount() throws Exception {
//        boolean newAccount = EthUtil.newAccount("ibeesaas");
//    }
//
//    @Test
//    public void testGetAccountBalance() throws Exception {
//        EthUtil.getBalance(mainAccount);
//    }
//
//    @Test
//    public void testAccounts() throws Exception {
//        EthUtil.getAccounts();
//    }
//
//    @Test
//    public void testEthCall() throws Exception {
//        String functionName = "owner";
//        String toAccount = "0xD2d5c85B4cdd8D0aDF88B6972Dd08e2659F0635F";
//        List<Type> inputParams = Arrays.asList();
//        List<TypeReference<?>> outParams = Arrays.asList((TypeReference<?>)new TypeReference<Type>() {
//        });
//        List<Type> result = EthUtil.call(mainAccount, toAccount, functionName, inputParams, outParams);
//        log.info("call result==>{}", result);
////        if (result.startsWith("0x")) {
////            BigInteger bigInteger = Numeric.decodeQuantity(result);
////            log.info("convert result to uint ==> {}", bigInteger);
////        }
//    }
//
//
//    @Test
//    public void testEthSendTransaction() throws Exception {
//        String functionName = "setValue";
//        String toAccount = "0xa05281e58cf07b51b1c4d6cc5f2677c70e201c95";
//        List<Type> inputParams = Arrays.asList(new Uint256(123));
//        List<TypeReference<?>> outParams = Arrays.asList(new TypeReference<Bool>() {
//        });
//        String transactionHash = EthUtil.sendTransaction(mainAccount, toAccount, password, functionName, inputParams, outParams);
//    }
//
//    @Test
//    public void testEthSendTransaction_SimpleStorage_Get() throws Exception {
//        String functionName = "get";
//        String toAccount = "0xdD25946Ca690CA295055442caf9E98c5461964F1";
//        String password = "987654321";
//        List<Type> inputParams = Arrays.asList();
//        List<TypeReference<?>> outParams = Arrays.asList(new TypeReference<Uint256>() {
//        });
//        String sendTransaction = EthUtil.sendTransaction(mainAccount, toAccount, password, functionName, inputParams, outParams);
//    }
//
//    @Test
//    public void minerStatus() throws IOException {
//        Admin adminClient = EthClient.getAdminClient();
//        Request<?, EthMining> ethMiningRequest = adminClient.ethMining();
//        log.info("minerStatus==>{}", ethMiningRequest.send().isMining());
//    }
//
//    @Test
//    public void testGetTransactionReceipt() throws Exception {
//        Optional<TransactionReceipt> transactionReceipt = EthUtil.getTransactionReceipt("0x4c1828d0cdc1c3428e11fc337587bd7ff56559e6f13fc0fa3070b95602f379cb");
//        Optional<TransactionReceipt> u = transactionReceipt.map(item -> {
//            String status = item.getStatus();
//            log.info("status==>{}", status);
//            List<Log> logs = item.getLogs();
//            log.info("logs==>{}", logs);
//            log.info("from==>{}", item.getFrom());
//            return null;
//        });
//    }
//
//    //查token余额
//    @Test
//    public void testGetTokenBalance() throws Exception {
//        String functionName = "balanceOf";
//        String toAccount = "0x5a62f07785a8255e6c12db988c0361e237d49364";
//        List<Type> inputParams = Arrays.asList((Type)new Address(mainAccount));
//        List<TypeReference<?>> outParams = Arrays.asList((TypeReference<?>)new TypeReference<Uint256>() {
//        });
//        String call = EthUtil.call(mainAccount, toAccount, functionName, inputParams);
//        log.info("call result==>{}", call);
//        BigInteger decimal = Numeric.decodeQuantity(call);
//        log.info("decimal==>{}", decimal);
//    }
// 
//    //转账
//    @Test
//    public void testTokenTransfer() throws Exception {
//        String functionName = "transferFrom";
//        String toAccount = "0x39030F0F4441Ec72228B924D70EE6bA5243dEE23";
//        String fromAccount = mainAccount;
//        List<Type> inputParams = Arrays.asList(new Address(mainAccount), new Address(mainAccount), new Uint256(100000));
//        List<TypeReference<?>> outParams = Arrays.asList(new TypeReference<Bool>() {
//        });
////        String call = EthUtil.sendTransaction(fromAccount, toAccount, "987654321", functionName, inputParams, outParams);
//        List<Type> call = EthUtil.call(fromAccount, toAccount, functionName, inputParams, outParams);
//        log.info("call result==>{}", call);
//    }
//
//
//    @Test
//    public void getStorageAt() throws Exception {
//        String storage = EthUtil.getStorageAt("0x5dfe02a50a6c0fcdba65a77f0bbc492224ae62d5", 0);
//        log.info("storage:{}", storage);
//    }
//
//    @Test
//    public void testTransfer() throws Exception {
//        String transfer = EthUtil.transfer(mainAccount, password, "0xa77b82ec4fff0ab19f9f7672b20ed5788faa6647", 100);
//    }
//
//    @Test
//    public void testObservable() throws Exception {
//        Admin web3j = EthClient.getAdminClient();
//        log.info(String.valueOf(web3j.ethAccounts()));
//        CountDownLatch countDownLatch = new CountDownLatch(100);
//        Subscription pendSubScription = web3j.pendingTransactionObservable().subscribe(tx -> {
//            log.info("tx==>block_number:{},transaction_hash:{}",tx.getBlockNumber(),tx.getHash());
//            countDownLatch.countDown();
//        });
//
//        Subscription blockSubScription = web3j.blockObservable(false).subscribe(block -> {
//            log.info("block=>number:{},transaction_number", block.getBlock().getNumber(),block.getBlock().getTransactions().size());
//        });
//
//        Subscription transactionSubscription = web3j.transactionObservable().subscribe(tx -> {
//            log.info("tx==>block_number:{},transaction_hash:{}",tx.getBlockNumber(),tx.getHash());
//        });
//
//        Subscription logSubScription = web3j.ethLogObservable(new EthFilter()).subscribe(eventLog -> {
//            log.info("eventLog==>log_data:{}", eventLog.getData());
//        });
//
//        countDownLatch.await(60, TimeUnit.MINUTES);
//        pendSubScription.unsubscribe();
//        blockSubScription.unsubscribe();
//        logSubScription.unsubscribe();
//        transactionSubscription.unsubscribe();
//    }
//}
