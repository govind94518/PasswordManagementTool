package com.epam.pmt.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        Administrator verify = new Administrator();
        if (verify.administratorVerification()) {
            OptionMenu.getUserOption();
        } else {
            LOGGER.info(".....Failed......");
            LOGGER.info(".....Again  Try......");
        }
    }
}
