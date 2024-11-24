package com.pipa.PipaAPI.utils.data;

import io.github.cdimascio.dotenv.Dotenv;

public class GetEnvData {
    public static Dotenv dotenv = Dotenv.configure().load();
    public static String getSecretKey(){
        return dotenv.get("PIPA_SECRET_KEY");
    }
}
