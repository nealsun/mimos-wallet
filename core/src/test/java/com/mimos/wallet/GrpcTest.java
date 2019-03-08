package com.mimos.wallet;

import com.mimos.wallet.core.grpc.EthRpcCliant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/3/7 8:39 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GrpcTest {

    @Resource
    EthRpcCliant cliant;

    @Test
    public void test(){
        cliant.buildTest();
    }
}
