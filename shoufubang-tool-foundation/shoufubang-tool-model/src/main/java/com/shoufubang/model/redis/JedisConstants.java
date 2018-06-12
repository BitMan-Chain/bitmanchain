package com.shoufubang.model.redis;

import java.util.*;

/**
 * redis 存储数据常量工具类
 * Created by SunQi on 17/4/10.
 */
public class JedisConstants {


    /**
     * redis缓存的存储key定义
     */

    //wap端标的详情redis.key头 +borrow.uuid
    public static final String WAP_BORROW_DETAIL = "wap.borrowdetail.";

    //app端标的详情redis.key头 +borrow.id
    public static final String APP_BORROW_DETAIL = "app.borrowdetail.";

    //pc端的体验标
    public static final String PC_EXPERIENCE_BORROWS = "pc.experienceborrows";

    //wap端的体验标
    public static final String WAP_EXPERIENCE_BORROWS = "wap.experienceborrows";

    //app端的体验标
    public static final String APP_EXPERIENCE_BORROWS = "app.experienceborrows";

    //app端的首页展示标
    public static final String APP_BORROWS = "app.borrows";

    //app端的首页展示标
    public static final String APP_BORROWS_NEW = "app.borrows.new";

    //移动端(app、wap)直投项目投资列表
    public static final String MOBILE_INVESTLIST_DIRECT = "mobile.investlist.direct";

    //移动端(app、wap)直投项目投资列表(定期)
    public static final String MOBILE_INVESTLIST_DIRECT_DQ = "mobile.investlist.direct.dq";

    //移动端(app、wap)直投项目投资列表(网贷)
    public static final String MOBILE_INVESTLIST_DIRECT_WD = "mobile.investlist.direct.wd";

    //移动端(app、wap)体验标投资列表
    public static final String MOBILE_INVESTLIST_EXPERIENCE = "mobile.investlist.experience";

    //移动端(app、wap)业主宝投资列表
    public static final String MOBILE_INVESTLIST_OWNER = "mobile.investlist.owner";

    //app端的首页轮播图
    public static final String APP_INDEX_PICS = "app.index.pictures";

    //会员
    public static final String MEMBER_ = "member";

    //会员等级列表
    public static final String MEMBER_GRADE_LIST = "member:grade.list";

    //会员权益信息 redis.key头 +privilege.uuid
    public static final String MEMBER_PRIVILEGE_INFO = "member:privilege:info:";

    //会员等级权益列表 redis.key头 +grade.uuid
    public static final String MEMBER_PRIVILEGE_LIST = "member:privilege:list:";

    //移动端(app、wap)惠盈宝申购编号
    public static final String MOBILE_HYB_APPLYNO = "mobile.hyb.applyno";

    //物业宝凤凰会的收款单位【物业公司信息】
    public static final String WYB_FHH_PROPERTY_COMPANY = "wyb:fhh:procom:map";

    public static final String MOBILE_HOMEPAGE_ICON = "mobile.homepage.icon";
    public static final String MOBILE_HOMEPAGE_SPECIAL = "mobile.homepage.special";
    public static final String MOBILE_HOMEPAGE_ANNOUNCEMENT = "mobile.homepage.announcement";
    public static final String MOBILE_HOMEPAGE_BANNER = "mobile.homepage.banner";
    public static final String MOBILE_HOMEPAGE_AD = "mobile.homepage.ad";

    /**
     * redis缓存的存储期限定义  单位:秒
     */

    //标详情 redis.expire期限
    public static final int BORROW_DETAIL_SECONDS = 5 * 60;

    //体验标 redis.expire期限
    public static final int EXPERIENCE_BORROWS_SECONDS = 24 * 60 * 60;

    //app首页标标 redis.expire期限
    public static final int APP_BORROWS_SECONDS = 5 * 60;

    //移动端(app、wap)直投项目投资列表
    public static final int MOBILE_INVESTLIST_DIRECT_SECONDS = 5 * 60;

    //移动端(app、wap)体验标投资列表
    public static final int MOBILE_INVESTLIST_EXPERIENCE_SECONDS = 5 * 60;

    //移动端(app、wap)业主宝投资列表
    public static final int MOBILE_INVESTLIST_OWNER_SECONDS = 5 * 60;

    //首页-
    public static final int MOBILE_HOMEPAGE_SECONDS = 24*60*60;

    /**
     * 所有redis缓存的各业务模块配置集合
     */
    public static List<Map> redisKeyList = new ArrayList<Map>();

    static {
        addRedisKey(WAP_BORROW_DETAIL, "wap端标的详情", BORROW_DETAIL_SECONDS, "borrow.uuid");
        addRedisKey(APP_BORROW_DETAIL, "app端标的详情", BORROW_DETAIL_SECONDS, "borrow.id");
        addRedisKey(PC_EXPERIENCE_BORROWS, "pc端体验标列表", EXPERIENCE_BORROWS_SECONDS, "");
        addRedisKey(WAP_EXPERIENCE_BORROWS, "wap端体验标列表", EXPERIENCE_BORROWS_SECONDS, "");
        addRedisKey(APP_EXPERIENCE_BORROWS, "app端体验标列表", EXPERIENCE_BORROWS_SECONDS, "");
        addRedisKey(APP_BORROWS, "app首页展示标", APP_BORROWS_SECONDS, "");
        addRedisKey(MOBILE_INVESTLIST_DIRECT, "移动端(app、wap)直投项目投资列表", MOBILE_INVESTLIST_DIRECT_SECONDS, "");
        addRedisKey(MOBILE_INVESTLIST_DIRECT_DQ, "移动端(app、wap)直投项目定期投资列表", MOBILE_INVESTLIST_DIRECT_SECONDS, "");
        addRedisKey(MOBILE_INVESTLIST_DIRECT_WD, "移动端(app、wap)直投项目网贷投资列表", MOBILE_INVESTLIST_DIRECT_SECONDS, "");
        addRedisKey(MOBILE_INVESTLIST_EXPERIENCE, "移动端(app、wap)体验标投资列表", MOBILE_INVESTLIST_EXPERIENCE_SECONDS, "");
        addRedisKey(MOBILE_INVESTLIST_OWNER, "移动端(app、wap)业主宝投资列表", MOBILE_INVESTLIST_OWNER_SECONDS, "");
        addRedisKey(APP_INDEX_PICS, "APP首页轮播图", "");
        addRedisKey(MEMBER_, "会员中心", "");
        addRedisKey(MOBILE_HYB_APPLYNO, "移动端(app、wap)惠盈宝申购编号", "hyborderinfo.hybproductid");
        addRedisKey(WYB_FHH_PROPERTY_COMPANY,"物业宝凤凰会收款单位","");

        addRedisKey(MOBILE_HOMEPAGE_ICON ,"移动端首页icon",MOBILE_HOMEPAGE_SECONDS,"");
        addRedisKey(MOBILE_HOMEPAGE_SPECIAL,"移动端首页特别推荐",MOBILE_HOMEPAGE_SECONDS,"");
        addRedisKey(MOBILE_HOMEPAGE_ANNOUNCEMENT,"移动端首页公告",MOBILE_HOMEPAGE_SECONDS,"");
        addRedisKey(MOBILE_HOMEPAGE_BANNER,"移动端首页banner",MOBILE_HOMEPAGE_SECONDS,"");
        addRedisKey(MOBILE_HOMEPAGE_AD ,"移动端首页广告",MOBILE_HOMEPAGE_SECONDS,"");
        addRedisKey("isSign." ,"用户是否签到","yyyy-MM-dd.userId");
    }

    /**
     * 封装redisKey(所有redis缓存的各业务模块配置集合)
     *
     * @param keyTop         redis缓存key头
     * @param seconds        redis缓存期限
     * @param name           缓存业务模块名
     * @param keyTailPattern redis缓存key头后跟属性名
     */
    private static void addRedisKey(String keyTop, String name, int seconds, String keyTailPattern) {
        Map redisMap = new HashMap();
        redisMap.put("keyTop", keyTop);
        redisMap.put("name", name);
        redisMap.put("seconds", seconds);
        redisMap.put("keyTailPattern", keyTailPattern);
        redisKeyList.add(redisMap);
    }

    /**
     * 封装redisKey(所有redis缓存的各业务模块配置集合)
     *
     * @param keyTop         redis缓存key头
     * @param name           缓存业务模块名
     * @param keyTailPattern redis缓存key头后跟属性名
     */
    private static void addRedisKey(String keyTop, String name, String keyTailPattern) {
        Map redisMap = new HashMap();
        redisMap.put("keyTop", keyTop);
        redisMap.put("name", name);
        redisMap.put("keyTailPattern", keyTailPattern);
        redisKeyList.add(redisMap);
    }


}
