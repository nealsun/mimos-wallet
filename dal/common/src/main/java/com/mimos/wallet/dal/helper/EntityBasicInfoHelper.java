//package com.mimos.wallet.dal.helper;
//
//import com.mimos.wallet.dal.common.generated.tables.pojos.EntityBasicInfo;
//import com.mimos.wallet.ext.DateTimeWithZone;
//
//public class EntityBasicInfoHelper {
//
//    public static void create(EntityBasicInfo basicInfo) {
//        create(basicInfo, DateTimeWithZone.now());
//    }
//
//    public static void create(EntityBasicInfo basicInfo, DateTimeWithZone time) {
//        basicInfo.setCreateTime(time.getDatetime());
//        basicInfo.setCreateZone(time.getZone());
//    }
//
//    public static void update(EntityBasicInfo basicInfo) {
//        update(basicInfo, DateTimeWithZone.now());
//    }
//
//    public static void update(EntityBasicInfo basicInfo, DateTimeWithZone time) {
//        basicInfo.setUpdateTime(time.getDatetime());
//        basicInfo.setUpdateZone(time.getZone());
//    }
//
//    public static void createUpdate(EntityBasicInfo basicInfo) {
//        createUpdate(basicInfo, DateTimeWithZone.now());
//    }
//
//    public static void createUpdate(EntityBasicInfo basicInfo, DateTimeWithZone time) {
//        basicInfo.setCreateTime(time.getDatetime());
//        basicInfo.setCreateZone(time.getZone());
//        basicInfo.setUpdateTime(time.getDatetime());
//        basicInfo.setUpdateZone(time.getZone());
//    }
//}
