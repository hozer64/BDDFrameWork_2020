package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BasePage {

    private  static ThreadLocal<WebDriver> driverpool = new ThreadLocal<>();

    public BasePage(){

    }

    public static WebDriver get(){
        if(driverpool.get()==null){
            String browserParamFromEnv=System.getProperty("browser");
            String browser=browserParamFromEnv==null ? ConfigReader.getProperty("browser"): browserParamFromEnv;


            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverpool.set(new ChromeDriver());
                    break;

                case "chrome_headless":
                    WebDriverManager.chromedriver().setup();
                    driverpool.set(new ChromeDriver(new ChromeOptions().setHeadless(true)));


                case "Firefox" :

                    WebDriverManager.firefoxdriver().setup();
                    driverpool.set(new FirefoxDriver());
                    break;

                case "firefox_headless":
                    WebDriverManager.firefoxdriver().setup();
                    driverpool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    break;
                default:
                    throw new RuntimeException("Invalid browser name!");


            }

        }
        return driverpool.get();

    }

    public static void closeBrowser(){
        driverpool.get().quit();
        driverpool.remove();

    }

}
