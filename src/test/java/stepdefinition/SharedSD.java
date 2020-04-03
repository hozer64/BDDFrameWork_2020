package stepdefinition;

import RunnerTest.WebPages.ElementUtil;
import Utils.BasePage;
import Utils.ConfigReader;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class SharedSD {

            ElementUtil elementUtil= new ElementUtil();
            private static Logger logger= Logger.getLogger(SharedSD.class);

            // Hook Class

    @Before
    public void setUp(){
            logger.info("******************");
            logger.info("Tet Setup method is starting ..!");
            ConfigReader.getProperty("browser");
            BasePage.get().get(ConfigReader.getProperty("url"));
            BasePage.get().manage().window().fullscreen();

        }

     @After

    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            logger.error("Test is failed...!");
            byte[] screenshoot=((TakesScreenshot) BasePage.get()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshoot,"image/png");
            elementUtil.getScreenShot("pic");

        }else{
            logger.info("Clear");
            logger.info("Test completed");
        }
        logger.info("*************************");
        BasePage.closeBrowser();

     }


}
