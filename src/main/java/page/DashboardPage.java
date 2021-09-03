package page;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class DashboardPage {

	WebDriver driver;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;

	}

	@FindBy(how = How.XPATH, using = "//*[@id=\"todos-content\"]/form/ul/li[1]/input")
	WebElement SELECT_SINGLE_ELEMENT;

	@FindBy(how = How.XPATH, using = "/html/body/div[3]/input[1]")
	WebElement REMOVE_BUTTON_ELEMENT;

	@FindBy(how = How.XPATH, using = "/html/body/div[3]/input[3]")
	WebElement TOGGLE_ALL_CHECKBOX;

	@FindBy(how = How.XPATH, using = "//*[@id=\"extra\"]/input[1]")
	WebElement ADD_CATEGORY_ELEMENT;

	@FindBy(how = How.XPATH, using = "//*[@id=\"extra\"]/input[2]")
	WebElement SUBMIT_ADD_CATEGORY_BUTTON;

	@FindBy(how = How.XPATH, using = "//*[@id=\"extra\"]/select[1]")
	WebElement CATEGORY_SELECTION;

	@FindBy(how = How.XPATH, using = "/html/body")  
	WebElement TOP_ERROR_MESSAGE;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"extra\"]/select[3]")  
	WebElement DUE_DATE_DROPDOWN_OPTION;
	
	
	public void clickToggleAll() {
		TOGGLE_ALL_CHECKBOX.click();

	}

	public String selectSingleElement() {
		SELECT_SINGLE_ELEMENT.click();
		return SELECT_SINGLE_ELEMENT.getText();

	}

	public void clickOnRemoveButton() {
		REMOVE_BUTTON_ELEMENT.click();
	}

	public boolean doesSingleElementExsist() {
		try {
			SELECT_SINGLE_ELEMENT.getTagName();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public String enterNewCategoryText(String name) {
		// Random rand = new Random();

		// String name = "Tania " + rand.nextInt(10000);
		ADD_CATEGORY_ELEMENT.sendKeys(name);
		return name;
	}

	public void submitAddCateogryButton() {
		SUBMIT_ADD_CATEGORY_BUTTON.click();

	}

	public List<String> getCategorySelectionOptions() {

		List<String> list = new ArrayList<String>();

		Select select = new Select(CATEGORY_SELECTION);
		List<WebElement> elements = select.getOptions();

		for (WebElement elem : elements) {
			System.out.println("Current option: " + elem.getText());
			list.add(elem.getText());
		}

		return list;
	}

	public String getTopErrorMessage() {
		try {
			return TOP_ERROR_MESSAGE.getText();
		} catch (NoSuchElementException e) {
			return "";
		}

	}

	
	public List<String> dueDateDropDownOptions() {
		List<String> list = new ArrayList<String>();

		Select select = new Select(DUE_DATE_DROPDOWN_OPTION);
		List<WebElement> elements = select.getOptions();

		for (WebElement elem : elements) {
			System.out.println("Current option: " + elem.getText());
			list.add(elem.getText());
		}

		return list;
		
		
	}
}

