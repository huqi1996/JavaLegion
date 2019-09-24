package com.huqi.qs.main;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huqi
 */
public class Main {

    public static RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(new HttpHost("localhost", 9200, "http")).build());

    public static void indexByMap() {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "huqi");
        jsonMap.put("postDate", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        jsonMap.put("message", "trying out Elasticsearch 001");
        IndexRequest indexRequest = new IndexRequest("index", "type", "1")
                .source(jsonMap);
        processIndexRequest(indexRequest);
    }

    public static void indexByJson() {
        String jsonString = "{" +
                "\"user\":\"huqi\"," +
                "\"postDate\":\"2019-09-24 17:44:01\"," +
                "\"message\":\"trying out Elasticsearch 002\"" +
                "}";
        IndexRequest indexRequest = new IndexRequest("index", "type", "2")
                .source(jsonString, XContentType.JSON);
        processIndexRequest(indexRequest);
    }

    public static void processIndexRequest(IndexRequest indexRequest) {
        try {
            IndexResponse indexResponse = client.index(indexRequest);
            System.out.println(indexResponse.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        indexByMap();
        indexByJson();
    }
}
