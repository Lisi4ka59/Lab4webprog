package com.lisi4ka.lab4webdb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lisi4ka.lab4webdb.db.Dot;
import com.lisi4ka.lab4webdb.db.DotRepository;
import com.lisi4ka.lab4webdb.exeptions.NoSuchUsernameException;

import java.util.ArrayList;
import java.util.List;

public abstract class TableService {
    public static String jsonTable(String userId, DotRepository dotRepository) throws JsonProcessingException {
        int currentUserId;
        try {
            currentUserId = Integer.parseInt(userId);
        } catch (NoSuchUsernameException ex) {
            return ex.getMessage();
        }
        List<Dot> dotList = new ArrayList<>();
        Iterable<Dot> dots = dotRepository.findAllByUserId(currentUserId);
        while (dots.iterator().hasNext()) {
            dotList.add(dots.iterator().next());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(dotList);
    }
}
