package club.ihere.wechat.main;

import club.ihere.wechat.common.config.ConstantConfig;
import org.junit.Test;

import java.util.LinkedHashMap;

public class DoTest {

    @Test
    public void test() {
        LinkedHashMap<String, String> filterChainDefinitionMapTest = ConstantConfig.getFilterChainDefinitionMap();
        for (String key : filterChainDefinitionMapTest.keySet()) {
            System.out.println(key + ":" + filterChainDefinitionMapTest.get(key));
        }

    }
}
