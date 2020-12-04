package util;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Formatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import rccookie.util.Console;

public class DayGenerator {

    public static final boolean generateFilesForDay(int day) {
        try {
            File dir = new File("day" + day + "");
            if(!dir.exists()) dir.mkdir();

            boolean createdInput;

            File inputFile = new File("input/day" + day + ".input");
            if((createdInput = !inputFile.exists())) {
                inputFile.createNewFile();
                Formatter f = new Formatter(inputFile);
                f.format(getInput(day));
                f.close();
            }

            File file = new File("day" + day + "/Day.java");
            if(file.exists()) return createdInput;
            file.createNewFile();

            String fileText = Files.readString(Path.of("data/Day.template"));

            Formatter f = new Formatter(file);

            f.format(fileText, day);

            f.close();
            return true;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final String getInput(int day) {
        Console.log("Downloading input for day " + day + "...");

        // Disable CSS error logging
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);

        WebDriver driver = new HtmlUnitDriver();
        try{
            // Advent of code login page
            driver.get("https://adventofcode.com/auth/login");
            driver.findElements(By.tagName("a")).get(13).click(); // Github login link

            Console.log("Logging in...");

            // Github login page
            driver.findElement(By.id("login_field")).sendKeys("RcCookie0");
            WebElement e = driver.findElement(By.id("password"));
            e.sendKeys(getPassword());
            e.submit();

            Console.log("Logged in");

            // Go to input page
            driver.get("https://adventofcode.com/2020/day/4/input");
            String out = driver.getPageSource(); // Page source is for this page identical with the displayed text

            Console.log("Successfully downloaded input for day " + day);
            return out;
        } catch(Exception e) {
            e.printStackTrace();
            Console.log("Failed to download input");
            return null;
        } finally {
            driver.close();
        }
    }

    private static final String getPassword() throws Exception {
        return Files.readString(Path.of("data/password.password"), StandardCharsets.US_ASCII);
    }
}
