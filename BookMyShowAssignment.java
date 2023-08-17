		package Test;
		
		import java.time.Duration;
		
		import org.openqa.selenium.By;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.WebElement;
		import org.openqa.selenium.WindowType;
		import org.openqa.selenium.chrome.ChromeDriver;
		import org.openqa.selenium.chrome.ChromeOptions;
		import org.testng.annotations.BeforeTest;
		import org.testng.annotations.Test;
		
		public class BookMyShowAssignment
		{
			static WebDriver driver;
		
			@BeforeTest
			public void setUp() 
			{
			System.setProperty("webdriver.chrome.driver",
			"E:\\Drivers\\ChromeNew\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			
			//Open Bookmyshow Website
			driver.get("https://in.bookmyshow.com/explore/home/");
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
			driver.manage().window().maximize();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
		
			}
		
			@Test
			public void loginToBookMyShow() 
			{
				//Select City
				driver.findElement(By.xpath("//span[normalize-space()='Bengaluru']")).click();
				
				//Click on signIn
				WebElement signInBtn = driver.findElement(By.xpath("//div[normalize-space()='Sign in']"));
				signInBtn.click();
				
				//Continue With Email 
				WebElement continueWithEmailLink = driver.findElement(By.xpath("//div[contains(text(),'Continue with Email')]"));
				continueWithEmailLink.click();
				
				//Enter Email
				WebElement emailField = driver.findElement(By.xpath("//input[@id='emailId']"));
				emailField.sendKeys("selauto@yopmail.com");
				WebElement continueBtn = driver.findElement(By.xpath("//button[normalize-space()='Continue']"));
				continueBtn.click();
				String otp = BookMyShowAssignment.getOtp();
				System.out.println(otp);
				WebElement enterotpfield = driver.findElement(By.xpath("//input[3]"));
				enterotpfield.sendKeys(otp);
			
				}
		
			public static String getOtp() 
			{
				//Go to Yopmail.com
				driver.switchTo().newWindow(WindowType.TAB);
				driver.get("https://yopmail.com/wm");
				
				//Type Email And Access inbox
				WebElement yopmailbox = driver.findElement(By.xpath("//input[@id='login']"));
				yopmailbox.sendKeys("selauto@yopmail.com");
				WebElement goicon = driver.findElement(By.cssSelector(".material-icons-outlined.f36"));
				goicon.click();
			
				//Locate Latest OTP
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='ifinbox']")));
				WebElement otpmsgBox = driver
				.findElement(By.cssSelector("div[id='e_ZwZjBQR3ZGpjAGH1ZQNjAGH3ZwZkAD=='] div[class='lms']"));
				otpmsgBox.click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='ifmail']")));
				WebElement otpBox = driver.findElement(By.xpath(
				"//*[@id=\"mail\"]/div/table/tbody/tr[1]/td/div/table/tbody/tr[4]/td/table/tbody/tr[2]/td/table/tbody/tr/td"));
				//String parentwindow = driver.getWindowHandle();
				//driver.switchTo().window(parentwindow);
				//driver.close();
				return otpBox.getText();
				}
			}
		
		
		
		
