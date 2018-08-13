package com.jc.cluz.utils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class PauseUtils {

    /**
     * pause by mils
     * @param timeout
     */
    public static void pause(int timeout){
        try {
            Thread.sleep(timeout);
        }catch (Exception err){
            log.error(err.getMessage());
        }
    }
}
