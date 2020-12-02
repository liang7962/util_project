package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.*;


@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) throws IOException {
//		String str="{\"key\":\"120%\"}";
//		ObjectMapper mapper = new ObjectMapper();
//		Map<String,String> readValue = mapper.readValue(str, Map.class);
//		readValue.entrySet().forEach(e->{
//			String substring1 = e.getValue().substring(e.getValue().length() - 1, e.getValue().length());
//			if (substring1.equals("%")) {
//					System.out.println("%");
//				}else {
//					String substring = e.getValue().substring(0, e.getValue().length() - 1);
//					Integer.parseInt(substring);
//				}
//			}
//		);
//		Map<String,String> map1=map;

//        ObjectMapper objMap = new ObjectMapper();
//        JsonNode jsonNode = objMap.readTree(new File("C:\\Users\\EDZ\\Desktop\\工作\\resJson.json"));
//        Map<String, Object> map = objMap.convertValue(jsonNode, Map.class);
//
//        Integer code=(Integer)map.get("code");
//        List<Map<String,Map<String,String>>> list=(List<Map<String,Map<String,String>>>)map.get("data");
//        System.out.println(list.get(0));


	}
}
