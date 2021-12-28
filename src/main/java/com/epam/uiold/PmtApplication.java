package com.epam.uiold;
import com.epam.exception.AccountAlreadyExistsException;
import com.epam.exception.NoGroupFoundForAccount;
import com.epam.exception.NoRecordFoundForGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= "com.epam")
public class PmtApplication
{
    final static Logger LOGGER=LogManager.getLogger(PmtApplication.class);
    public  static  void main(String []args) throws AccountAlreadyExistsException, NoGroupFoundForAccount, NoRecordFoundForGroup {

       ApplicationContext context= SpringApplication.run(PmtApplication.class, args);
        Administrator verify=context.getBean(Administrator.class);
        if(verify.administratorVerification())
        {
            OptionMenu optionMenu=context.getBean(OptionMenu.class);
            optionMenu.getUserOption();
        }
        else
        {
            LOGGER.info(".....Failed......");
            LOGGER.info(".....Again  Try......");
        }
    }
}
