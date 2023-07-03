package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NotesPage {
    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;
    @FindBy(id = "addNoteButton")
    private WebElement addNoteButton;

    @FindBy(id = "editNoteButton")
    private WebElement editNoteButton;

    @FindBy(id = "deleteNoteButton")
    private WebElement deleteNoteButton;

    @FindBy(id = "success")
    private WebElement successMsg;

    @FindBy(id = "successContinue")
    private WebElement successContinue;

    @FindBy(id = "noteTitleText")
    private WebElement noteTitleText;

    @FindBy(id = "note-title")
    private WebElement noteTitleInput;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionInput;

    @FindBy(id = "submitNote")
    private WebElement submitNote;

    private WebDriver driver;
    private static final int timeOutInSeconds = 2;

    public NotesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void clickNoteTab(){
        WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(notesTab));
        notesTab.click();
    }
   public boolean createNote(String title, String description){
        clickNoteTab();
       WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);

       wait.until(ExpectedConditions.elementToBeClickable(addNoteButton));
       addNoteButton.click();

       wait.until(ExpectedConditions.visibilityOf(noteTitleInput));
       noteTitleInput.clear();
       noteTitleInput.sendKeys(title);

       wait.until(ExpectedConditions.visibilityOf(noteDescriptionInput));
       noteDescriptionInput.clear();
       noteDescriptionInput.sendKeys(description);

       wait.until(ExpectedConditions.elementToBeClickable(submitNote));
       submitNote.click();

       wait.until(ExpectedConditions.visibilityOf(successMsg));
       return successMsg.isDisplayed();
   }

   public String getNoteTitle(){
       WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);

       wait.until(ExpectedConditions.elementToBeClickable(successContinue));
       successContinue.click();

       clickNoteTab();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(noteTitleText));
            return noteTitleText.getText();
        } catch (TimeoutException toe){
            return "";
        }

   }

   public boolean editNote(String title, String description){
       WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);

       wait.until(ExpectedConditions.elementToBeClickable(editNoteButton));
       editNoteButton.click();

       wait.until(ExpectedConditions.visibilityOf(noteTitleInput));
       noteTitleInput.clear();
       noteTitleInput.sendKeys(title);

       wait.until(ExpectedConditions.visibilityOf(noteDescriptionInput));
       noteDescriptionInput.clear();
       noteDescriptionInput.sendKeys(description);

       wait.until(ExpectedConditions.elementToBeClickable(submitNote));
       submitNote.click();

       wait.until(ExpectedConditions.visibilityOf(successMsg));
       return successMsg.isDisplayed();
   }

   public boolean deleteNote(){
       WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);

       wait.until(ExpectedConditions.elementToBeClickable(deleteNoteButton));
       deleteNoteButton.click();

       wait.until(ExpectedConditions.visibilityOf(successMsg));
       return successMsg.isDisplayed();
   }


}

