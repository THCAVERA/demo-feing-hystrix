package br.com.demo.hystrix.service;

import br.com.demo.hystrix.payload.Context;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadFromJsonService {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public List<Context> readFromFile() {
        JSONParser parser = new JSONParser();
        List<Context> list = new ArrayList<>();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader("C:\\Users\\felip\\workspace\\demo-feing-hystrix\\src\\main\\java\\schema.json"));

            for (Object o : a.toArray()) {
                String s = objectMapper.writeValueAsString(o);
                objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                Context context = objectMapper.readValue(s, Context.class);
                list.add(context);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}

