package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.DashboardPage;



public class CategoryTest {
	
WebDriver driver;
String browser ; 
		
		
		@BeforeClass
		public void readConfig() {
			System.out.println("Before Class");
			
			Properties prop = new Properties();
			
			try {
				//inputStream//BufferReader//FileReader//Scanner
				InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			    prop.load(input);	
			    browser = prop.getProperty("browser");
			    System.out.println();
				
			}catch(IOException e) {
				e.printStackTrace();
	
			}
		}
			
		@BeforeMethod
		public void init() {
			
			if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver","drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				
			}else if(browser.equalsIgnoreCase("firefox")){
				System.setProperty("webdriver.gecko.driver","drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
				
				
			}
			
			driver.get("https://techfios.com/test/101/index.php");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
		}
		
		@Test
		public void addCategory() {
			//Step 1: 
			//Enter a new category
			
			//Step 2:
			//click on the add category button
			
			//Step 2: 
			//Verify that the category got added
			
			
			DashboardPage dbPage = PageFactory.initElements(driver, DashboardPage.class);

			//Step 1
			Random rand = new Random();
			String name = "Tania " + rand.nextInt(10000);
			dbPage.enterNewCategoryText(name);
			
			//Step 2
			dbPage.submitAddCateogryButton();
			
			//Step 3
			verifyCategoryGotAdded(dbPage, name);	

		}
		
		
		
		@Test
		public void addDuplicateCategory() {
			//Step 1: 
			//Enter a new category
		
			//Step 2
			//click on the add category button
			
			//Step 3
			//Re enter the same category 
			
			//Step 4
			// click on add category button
						
			//Step 5: 
			//Verify that that an error message is displayed at the top
			
			
			DashboardPage dbPage = PageFactory.initElements(driver, DashboardPage.class);

			//Step 1
			Random rand = new Random();
			String name = "Tania " + rand.nextInt(10000);
			dbPage.enterNewCategoryText(name);
			
			//Step 2
			dbPage.submitAddCateogryButton();
			
			//Step 3
			dbPage.enterNewCategoryText(name);
			
			//Step 4
			dbPage.submitAddCateogryButton();
			
			//Step 5 -- verify
			String actualMsg = dbPage.getTopErrorMessage();
			String expectedMsg = "The category you want to add already exists:" ;
			boolean found = actualMsg.contains(expectedMsg);
			
			System.out.println("actualMsg: " + actualMsg);
			
			Assert.assertTrue(found, "The category was added multiple times");
			
		//		Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "The category was added multiple times!");
			
			
		

		}
		
		
		private void verifyCategoryGotAdded(DashboardPage page, String lookingFor) {

			boolean found = false;
			List<String> options = page.getCategorySelectionOptions();
			for(String curOpt : options) {
				if(curOpt.equals(lookingFor)) {
					found = true;
					break;
				}
			}
			
			Assert.assertEquals(found, true, "The category '" + lookingFor + "' was not found!");
			
		}
		
		
		
	
		//3. Verify all the months exists in the options
		
		@Test
		public void verifyAllMonthsExist() {
			
		DashboardPage dbPage = PageFactory.initElements(driver, DashboardPage.class);

		//2. Read all the due date options
		
		List<String> actual = dbPage.dueDateDropDownOptions();
		
		List<String> expected = new ArrayList<String>();	
		expected.add("None");
		expected.add("Jan");
		expected.add("Feb");
		expected.add("Mar");
		expected.add("Apr");
		expected.add("May");
		expected.add("Jun");
		expected.add("Jul");
		expected.add("Aug");
		expected.add("Sep");
		expected.add("Oct");
		expected.add("Nov");
		expected.add("Dec");

		
		Assert.assertEquals(actual, expected, "All months are not present");
		
			
		}

		@AfterMethod
		public void teardown() {
			driver.close();
			driver.quit();

		}

		@AfterClass
		public static void afterClass() {
			System.out.println("After Class");
			
		}
			
			

	}


