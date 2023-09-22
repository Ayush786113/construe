package com.construe.apigateway;

import com.mifmif.common.regex.Generex;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.regex.Pattern;

@Component
public class Security {
    final private static String[] regex = new String[]
            {
                    "[a-z][1-3]{3}[a-z]{2}[1|2]{2}0[a-z]{2}[1-5][a-z]{2}[6-9][x-z]",
                    "[a-z]{2}[1-9][1-5]{2}[a-z]{2}[1-5][1-9][1-3][a-z]{2}[1-9][a-z]{2}[1-9]",
                    "[a-z][1-3]{3}[a-z]{2}[1-9]0[1-9][a-z]{2}[1-9][a-z]{2}[1-9]{2}",
                    "[a-z]{2}[1-9]{2}0[a-z]{4}[1-9]{3}[a-z]{3}0",
                    "[1-9]{2}[a-z]{4}[1-9]{2}[a-z]{4}[1-9]{2}[a-z]{2}"
            };
    private static Generex [] generexes;
    Security(){
        generexes = new Generex[regex.length];
        for(int i = 0; i < regex.length; i+=1){
            generexes[i] = new Generex(regex[i]);
        }
    }
    public String generateApiKey() {
        return generexes[new Random().nextInt(generexes.length)].random();
    }
    public boolean validateAPIKey(String apikey){
        for(String pattern : regex){
            if(Pattern.matches(pattern, apikey))
                return true;
        }
        return false;
    }
}

