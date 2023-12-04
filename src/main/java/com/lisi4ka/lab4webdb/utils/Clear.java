package com.lisi4ka.lab4webdb.utils;

import com.lisi4ka.lab4webdb.db.Dot;
import com.lisi4ka.lab4webdb.db.DotRepository;
import com.lisi4ka.lab4webdb.db.RegUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.lisi4ka.lab4webdb.utils.Check.tokenMap;
@Service
public class Clear {
    @Transactional
    public void clear(String token, DotRepository dotRepository, RegUserRepository regUserRepository){
        long parsedToken = Long.parseLong(token);
        String user = tokenMap.inverse().get(parsedToken);
        if (regUserRepository.findByUsername(user).isPresent()) {
            Long userId = regUserRepository.findByUsername(user).get().getId();
            for (Dot dot:dotRepository.findAllByUserId(userId)) {
                dotRepository.deleteById(dot.getCount());
            }
            //dotRepository.deleteDotByUserId(userId);
        }
    }
}
