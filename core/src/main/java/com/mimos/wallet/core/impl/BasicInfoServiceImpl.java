//package com.mimos.wallet.core.impl;
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Component;
//import com.mimos.wallet.ext.MayFail;
//import com.mimos.wallet.core.BasicInfoService;
//import com.mimos.wallet.dal.EntityBasicInfoRepository;
//import com.mimos.wallet.dal.common.generated.tables.pojos.EntityBasicInfo;
//
//@Component
//@AllArgsConstructor
//public class BasicInfoServiceImpl implements BasicInfoService {
//
//    private EntityBasicInfoRepository basicInfoRepository;
//
//    @Override
//    public MayFail<String> fetchNameById(Long id) {
//        MayFail<EntityBasicInfo> info = basicInfoRepository.fetchById(id);
//        return  info.map(EntityBasicInfo::getNickname);
//    }
//
//}
