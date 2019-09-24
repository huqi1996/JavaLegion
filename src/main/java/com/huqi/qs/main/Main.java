package com.huqi.qs.main;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huqi
 */
public class Main {
    public static void main(String[] args) {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")).build());
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "huqi");
        jsonMap.put("postDate", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        jsonMap.put("message", "trying out Elasticsearch");
        IndexRequest indexRequest = new IndexRequest("index", "type", "1")
                .source(jsonMap);
        try {
            IndexResponse indexResponse = client.index(indexRequest);
            System.out.println(indexResponse.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
