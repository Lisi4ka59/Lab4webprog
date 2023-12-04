package com.lisi4ka.lab4webdb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lisi4ka.lab4webdb.db.Dot;
import com.lisi4ka.lab4webdb.db.DotRepository;
import com.lisi4ka.lab4webdb.db.RegUserRepository;
import com.lisi4ka.lab4webdb.exeptions.NoSuchUsernameException;

import java.util.ArrayList;
import java.util.List;

import static com.lisi4ka.lab4webdb.utils.Check.tokenMap;

public abstract class TableService {
    public static String jsonTable(String token, DotRepository dotRepository, RegUserRepository regUserRepository) throws JsonProcessingException {
        long parsedToken = Long.parseLong(token);
        String user = tokenMap.inverse().get(parsedToken);
        Long userId = 0L;
        if (regUserRepository.findByUsername(user).isPresent()) {
            userId = regUserRepository.findByUsername(user).get().getId();
        }

        List<Dot> dotList = new ArrayList<>();
        Iterable<Dot> dots = dotRepository.findAllByUserId(userId);
        for (Dot dot:dots) {
            dotList.add(dot);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(dotList);
    }
}
