//package com.shoufubang.model.eth.web3j;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.Arrays;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.web3j.abi.TypeReference;
//import org.web3j.abi.datatypes.Address;
//import org.web3j.abi.datatypes.Bool;
//import org.web3j.abi.datatypes.Int;
//import org.web3j.abi.datatypes.Type;
//
//import com.shoufubang.model.exception.EthContractException;
//
///**
// * Created by shizhiguo on 2018/2/8
// */
//@Component
//public class BaseContract {
//
//    private static Logger log = LoggerFactory.getLogger(BaseContract.class);
//
// 
//    protected String defaultAccount;
//
//    /**
//     * 获取合约运行状态（是否暂停）
//     *
//     * @param contractAddress
//     * @return
//     * @throws EthContractException
//     */
//    public boolean paused(String contractAddress) throws EthContractException {
//        Type result = call(contractAddress, "paused",Arrays.asList(new TypeReference<Bool>() {}));
//        return typeToBoolean(result);
//    }
//
//    /**
//     * 获取合约所有人 owner
//     *
//     * @return
//     * @throws EthContractException
//     */
//    public String owner(String contractAddress) throws EthContractException {
//        try {
//            Type result = call(contractAddress, "owner",Arrays.asList(new TypeReference<Address>() {
//                    }));
//            return typeToAddress(result);
//        } catch (Exception e) {
//            throw new EthContractException(e);
//        }
//    }
//
//    public String call(String contractAddress, String functionName, Type... inputParams) throws EthContractException {
//        return call(defaultAccount, contractAddress, functionName, inputParams);
//    }
//
//    public String call(String from, String contractAddress, String functionName, Type... inputParams) throws EthContractException {
//        try {
//            String result = EthUtil.call(from, contractAddress, functionName,
//                    Arrays.asList(inputParams));
//            return result;
//        } catch (Exception e) {
//            throw new EthContractException(e);
//        }
//    }
//
//    public Type call(String contractAddress, String functionName, List<TypeReference<?>> outParams, Type... inputParams) throws EthContractException {
//        return call(defaultAccount, contractAddress, functionName, outParams, inputParams);
//    }
//
//    public Type call(String from, String contractAddress, String functionName, List<TypeReference<?>> outParams, Type... inputParams) throws EthContractException {
//        try {
//            List<Type> result = EthUtil.call(from, contractAddress, functionName,
//                    Arrays.asList(inputParams), outParams);
//
//            return result.get(0);
//        } catch (Exception e) {
//            throw new EthContractException(e);
//        }
//    }
//
//    public String sendTransaction(String from, String password, String contractAddress, String functionName, Type... inputParams) throws EthContractException {
//        try {
//            String result = EthUtil.sendTransaction(from, contractAddress, password, functionName,
//                    Arrays.asList(inputParams), Arrays.asList(new TypeReference<Type>() {
//                    }));
//            return result;
//        } catch (Exception e) {
//            throw new EthContractException(e);
//        }
//    }
//
//    protected boolean typeToBoolean(Type result) {
//        return ((Bool) result).getValue();
//    }
//
//    protected String typeToAddress(Type result) {
//        return ((Address) result).getValue();
//    }
//
//    protected int typeToInt(Type result) {
//        return ((Int) result).getValue().intValue();
//    }
//
//    protected BigDecimal typeToBigDecimal(Type result,int decimals){
//        BigInteger balance = ((Int) result).getValue();
//        return BigDecimal.valueOf(balance.longValue()).divide(BigDecimal.valueOf(10).pow(decimals));
//    }
//
//}
