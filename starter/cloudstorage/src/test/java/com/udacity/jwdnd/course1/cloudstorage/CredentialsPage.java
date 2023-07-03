package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialsPage {
    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;
    @FindBy(id = "addCredentialButton")
    private WebElement addCredentialButton;
    @FindBy(id = "editCredentialButton")
    private WebElement editCredentialButton;
    @FindBy(id = "deleteCredentialButton")
    private WebElement deleteCredentialButton;
    @FindBy(id = "passwordText")
    private WebElement passwordText;
    @FindBy(id = "urlText")
    private WebElement urlText;
    @FindBy(id = "credential-url")
    private WebElement credentialUrl;
    @FindBy(id = "credential-username")
    private WebElement credentialUsername;
    @FindBy(id = "credential-password")
    private WebElement credentialPassword;
    @FindBy(id = "submitCredential")
    private WebElement submitCredential;
    @FindBy(id = "success")
    private WebElement successMsg;
    @FindBy(id = "successContinue")
    private WebElement successContinue;
    private WebDriver driver;
    private static final int timeOutInSeconds = 2;
    public CredentialsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void clickCredentialTab(){
        WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(credentialsTab));
        credentialsTab.click();
    }

    public boolean createCredential(String url, String username, String password){
        clickCredentialTab();
        WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);

        wait.until(ExpectedConditions.elementToBeClickable(addCredentialButton));
        addCredentialButton.click();

        wait.until(ExpectedConditions.visibilityOf(credentialUrl));
        credentialUrl.clear();
        credentialUrl.sendKeys(url);

        wait.until(ExpectedConditions.visibilityOf(credentialUsername));
        credentialUsername.clear();
        credentialUsername.sendKeys(username);

        wait.until(ExpectedConditions.visibilityOf(credentialPassword));
        credentialPassword.clear();
        credentialPassword.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(submitCredential));
        submitCredential.click();

        wait.until(ExpectedConditions.visibilityOf(successMsg));
        return successMsg.isDisplayed();
    }

    public String getPasswordText(){
        WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);

        wait.until(ExpectedConditions.elementToBeClickable(successContinue));
        successContinue.click();

        clickCredentialTab();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(passwordText));
            return passwordText.getText();
        } catch (TimeoutException toe){
            return "";
        }

    }

    public boolean editCredential(String url, String username, String password){
        WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);

        wait.until(ExpectedConditions.visibilityOf(credentialUrl));
        credentialUrl.clear();
        credentialUrl.sendKeys(url);

        wait.until(ExpectedConditions.visibilityOf(credentialUsername));
        credentialUsername.clear();
        credentialUsername.sendKeys(username);

        wait.until(ExpectedConditions.visibilityOf(credentialPassword));
        credentialPassword.clear();
        credentialPassword.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(submitCredential));
        submitCredential.click();

        wait.until(ExpectedConditions.visibilityOf(successMsg));
        return successMsg.isDisplayed();
    }

    public boolean deleteCredential(){
        WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);

        wait.until(ExpectedConditions.elementToBeClickable(deleteCredentialButton));
        deleteCredentialButton.click();

        wait.until(ExpectedConditions.visibilityOf(successMsg));
        return successMsg.isDisplayed();
    }

    public String getPasswordInput(){
        WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);

        wait.until(ExpectedConditions.elementToBeClickable(editCredentialButton));
        editCredentialButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(credentialPassword));
        return credentialPassword.getAttribute("value");
    }

    public String getUrlText(){
        WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);

        wait.until(ExpectedConditions.elementToBeClickable(successContinue));
        successContinue.click();

        clickCredentialTab();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(urlText));
            return urlText.getText();
        } catch (TimeoutException toe){
            return "";
        }

    }
}
