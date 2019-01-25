package com.packtpublishing.tddjava.ch02friendships;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.StringStartsWith;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(MockitoJUnitRunner.class)
public class FriendshipsMongoAssertJTest {

	static WebDriver driver;
	
    @InjectMocks
    FriendshipsMongo friendships = new FriendshipsMongo();

    @Mock
    FriendsCollection friends;
    
	@BeforeClass
    public static void SeleniumInitializer(){
    	//if you didn't update the Path system variable to add the full directory path to the executable as above mentioned then doing this directly through code
    	System.setProperty("webdriver.gecko.driver", "C://SeleniumExec//geckodriver.exe");
    	driver = new FirefoxDriver();
    	//Now you can Initialize marionette driver to launch firefox
/*    	DesiredCapabilities capabilities = DesiredCapabilities.firefox();
    	capabilities.setCapability("marionette", true);    	
    	driver = new RemoteWebDriver(capabilities);*/
    }

    @Test
    public void mockingWorksAsExpected() {
        Person joe = new Person("Joe");
        doReturn(joe).when(friends).findByName("Joe");
        assertThat(friends.findByName("Joe")).isEqualTo(joe);
    }

    @Test
    public void alexDoesNotHaveFriends() {
        assertThat(friendships.getFriendsList("Alex")).isEmpty();
    }

    @Test
    public void joeHas5Friends() {
        List<String> expected = Arrays.asList(
                new String[] {"Audrey", "Peter", "Michael", "Britney", "Paul"}
        );
        Person joe = spy(new Person("Joe"));
        doReturn(joe).when(friends).findByName("Joe");
        doReturn(expected).when(joe).getFriends();
        assertThat(friendships.getFriendsList("Joe"))
                .hasSize(5)
                .containsOnly("Audrey", "Peter", "Michael", "Britney", "Paul");
    }

    @Test
    public void adminToolTest(){
    	//driver.get("http://en.wikipedia.org/wiki/Main_Page");
    	driver.get("http://localhost:8080/AdminTool2");
    	WebElement username = driver.findElement(By.name("j_username"));
    	username.sendKeys("pekka");
    	WebElement password = driver.findElement(By.name("j_password"));
    	password.sendKeys("test");    	
    	
    	WebElement goButton = driver.findElement(By.name("login.button"));
    	goButton.click();

    	assertThat((driver.getTitle()).contains(StringStartsWith.startsWith("Test-driven development").toString())); 
    }
    
    @AfterClass
    public static void connectionClose(){
    	driver.quit();
    }
}
