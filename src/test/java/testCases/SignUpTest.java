package testCases;

import pageclasses.HomePage;
import pageclasses.PersonalDetails;
import pageclasses.SignupOptionsPage;
import setup.NewSetUp;
import setup.TestCaseExecutor;
import setup.TestCaseScenario;
import utilities.ClientUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.HashMap;
//All the test methods are written in this class
public class SignUpTest extends NewSetUp {
    public static int index=0;
    String currentMethodName=null;
    TestCaseExecutor testCaseExecutor;
    TestCaseScenario testCaseScenario;
    HashMap<Object,Object>hashMap;
    HomePage homePage;
     PersonalDetails personalDetails;
     SignupOptionsPage signupOptionsPage;
    ClientUtils clientUtils;

    //Test method names of all these test methods are unique and same as test name mentioned in the test cases json file.
    //Test data for these methods are taken based on the test data attribute of test cases mentioned in the Json file

    //This is test method for Discovery category signup for France country
    @Test
    public void discoveryCategoryFranceSignUp() throws Exception {
        try{
            testCaseExecutor = new TestCaseExecutor();
            currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
            hashMap= testCaseExecutor.createHashMapForSpecificRow(currentMethodName);
            signUp(hashMap,currentMethodName);
            creditCardPaymentMethodFrance(hashMap,currentMethodName);
        }
        finally {
            index=0;
        }



    }

    //This test method is for Silver category signup for Germany country
    @Test
    public void silverCategoryGermanSignUp() throws Exception {
        try{
            testCaseExecutor = new TestCaseExecutor();
            currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
            hashMap= testCaseExecutor.createHashMapForSpecificRow(currentMethodName);
            signUp(hashMap,currentMethodName);
        }
        finally {
            index=0;
        }

        ;
    }
    //This test method is to check the password validations for personal details fields
    @Test
    public void passwordValidations() throws Exception {
        try{
            testCaseExecutor = new TestCaseExecutor();
            currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
            hashMap= testCaseExecutor.createHashMapForSpecificRow(currentMethodName);
            homePage = PageFactory.initElements(driver,HomePage.class);
            personalDetails = PageFactory.initElements(driver,PersonalDetails.class);
            signupOptionsPage = PageFactory.initElements(driver,SignupOptionsPage.class);
            clientUtils = new ClientUtils(driver);
            clientUtils.click(driver,homePage.signUpButton);
            logger.info("Signup button is clicked");
            logger.info("Checking the Cookies text visibility");
            clientUtils.checkElementVisibility(driver,signupOptionsPage.cookiesText);
            clientUtils.click(driver,signupOptionsPage.cookiesAllowButton);
            logger.info("Clicked the Cookies text allow button");
            clientUtils.click(driver,signupOptionsPage.countryButton);
            logger.info("Clicked the Country button in Signup options page");
            logger.info("Taking the screenshot of Signup page");
            ++index;
            NewSetUp.getScreenshot(driver,index+"SignUpPage",currentMethodName);
            WebElement countryWebElement = signupOptionsPage.selectCountryFromList(driver,hashMap.get("Country").toString());
            logger.info("Selecting the country");
            clientUtils.click(driver,countryWebElement);
            WebElement chooseButtonWebElement = signupOptionsPage.selectChooseButton(driver,hashMap.get("Package").toString());
            logger.info("Clicking on the Choose button");
            clientUtils.click(driver,chooseButtonWebElement);
            logger.info("Waiting for personal details screen to load");
            clientUtils.waitForElement(driver,personalDetails.personalDetailsTitle);
            logger.debug("Taking screenshot for personal details screen");
            logger.debug("Added new line");
            ++index;
            NewSetUp.getScreenshot(driver,index+"PersonalDetails",currentMethodName);
            WebElement typeOfParkingWebElement = personalDetails.selectTypeOfParking(driver,hashMap.get("Type of Parking").toString());
            logger.info("Clicking on the type of parking web element either Personal or Company type");
            clientUtils.click(driver,typeOfParkingWebElement);
            clientUtils.sendText(driver,personalDetails.firstName,hashMap.get("First name").toString());
            clientUtils.sendText(driver,personalDetails.lastName,hashMap.get("Last name").toString());
            clientUtils.sendText(driver,personalDetails.email,hashMap.get("Email address").toString());
            clientUtils.sendText(driver,personalDetails.password,hashMap.get("Password").toString());
            clientUtils.click(driver,personalDetails.confirmPassword);
            Assert.assertTrue(clientUtils.checkElementVisibility(driver,personalDetails.passwordValidationErrorMessage));
            clientUtils.sendText(driver,personalDetails.confirmPassword,hashMap.get("Confirm Password").toString());
            clientUtils.click(driver,personalDetails.mobileNumber);
            Assert.assertTrue(clientUtils.checkElementVisibility(driver,personalDetails.confirmPasswordValidationErrorMessage));
            logger.info("First line in new branch");
        }
        finally {
            index=0;
        }


    }
    //This test method is for email validation in the personal details screen
    @Test
    public void emailValidation() throws Exception {
        try {
            homePage = PageFactory.initElements(driver,HomePage.class);
            personalDetails = PageFactory.initElements(driver,PersonalDetails.class);
            signupOptionsPage = PageFactory.initElements(driver,SignupOptionsPage.class);
            clientUtils = new ClientUtils(driver);
            testCaseExecutor = new TestCaseExecutor();
            currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
            hashMap= testCaseExecutor.createHashMapForSpecificRow(currentMethodName);
            System.out.println("Hashmap is"+hashMap);
            signUp(hashMap,currentMethodName);
            clientUtils.scrollTillElementIsVisible(driver,personalDetails.email);
            Assert.assertTrue(clientUtils.checkElementVisibility(driver,personalDetails.emailValidationErrorMessage));
        }
        finally {
            index=0;
        }

    }
    //This test method is for validating licensePlate number
    @Test
    public void licensePlateNumberValidation() throws Exception {
        try{
            homePage = PageFactory.initElements(driver,HomePage.class);
            personalDetails = PageFactory.initElements(driver,PersonalDetails.class);
            signupOptionsPage = PageFactory.initElements(driver,SignupOptionsPage.class);
            clientUtils = new ClientUtils(driver);
            testCaseExecutor = new TestCaseExecutor();
            currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
            hashMap= testCaseExecutor.createHashMapForSpecificRow(currentMethodName);
            signUp(hashMap,currentMethodName);
            clientUtils.scrollTillElementIsVisible(driver,personalDetails.licensePlateNumber);
            Assert.assertTrue(clientUtils.checkElementVisibility(driver,personalDetails.licensePlateInvalidNumberErrorMessage));
        }
        finally {
            index=0;
        }


    }
    //This test method is for validating mobile number
    @Test
    public void mobileNumberValidation() throws Exception {
        try{
            homePage = PageFactory.initElements(driver,HomePage.class);
            personalDetails = PageFactory.initElements(driver,PersonalDetails.class);
            signupOptionsPage = PageFactory.initElements(driver,SignupOptionsPage.class);
            clientUtils = new ClientUtils(driver);
            testCaseExecutor = new TestCaseExecutor();
            currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
            hashMap= testCaseExecutor.createHashMapForSpecificRow(currentMethodName);
            clientUtils.click(driver,homePage.signUpButton);
            logger.info("Signup button is clicked");
            logger.info("Checking the Cookies text visibility");
            clientUtils.checkElementVisibility(driver,signupOptionsPage.cookiesText);
            clientUtils.click(driver,signupOptionsPage.cookiesAllowButton);
            logger.info("Clicked the Cookies text allow button");
            clientUtils.click(driver,signupOptionsPage.countryButton);
            logger.info("Clicked the Country button in Signup options page");
            logger.info("Taking the screenshot of Signup page");
            ++index;
            NewSetUp.getScreenshot(driver,index+"SignUpPage",currentMethodName);

            // WebElement countryWebElement = clientUtils.dynamicXpathElements(driver,signupOptionsPage.selectCountry,"France");
            WebElement countryWebElement = signupOptionsPage.selectCountryFromList(driver,hashMap.get("Country").toString());
            logger.info("Selecting the country");
            clientUtils.click(driver,countryWebElement);
            WebElement chooseButtonWebElement = signupOptionsPage.selectChooseButton(driver,hashMap.get("Package").toString());
            logger.info("Clicking on the Choose button");
            clientUtils.click(driver,chooseButtonWebElement);
            logger.info("Waiting for personal details screen to load");
            clientUtils.waitForElement(driver,personalDetails.personalDetailsTitle);
            logger.info("Checking the title text of Personal details screen");
            Assert.assertEquals(personalDetails.personalDetailsTitle.getText().toString(),"PERSONAL DETAILS");
            logger.debug("Taking screenshot for personal details screen");
            ++index;
            NewSetUp.getScreenshot(driver,index+"PersonalDetails",currentMethodName);

            WebElement typeOfParkingWebElement = personalDetails.selectTypeOfParking(driver,hashMap.get("Type of Parking").toString());
            logger.info("Clicking on the type of parking web element either Personal or Company type");
            clientUtils.click(driver,typeOfParkingWebElement);
            logger.info("Entering first name in Personal details screen");
            clientUtils.sendText(driver,personalDetails.firstName,hashMap.get("First name").toString());
            logger.info("Entering first name in last details screen");
            clientUtils.sendText(driver,personalDetails.lastName,hashMap.get("Last name").toString());
            logger.info("Entering Email address in Personal details screen");
            clientUtils.sendText(driver,personalDetails.email,hashMap.get("Email address").toString());
            logger.info("Entering password in Personal details screen");
            clientUtils.sendText(driver,personalDetails.password,hashMap.get("Password").toString());
            logger.info("Entering confirm password in Personal details screen");
            clientUtils.sendText(driver,personalDetails.confirmPassword,hashMap.get("Confirm Password").toString());
            logger.info("Scrolling till the mobile number field is visible");
            clientUtils.scrollTillElementIsVisible(driver,personalDetails.mobileNumber);
            String mobileNumber = hashMap.get("Mobile Number").toString();
            logger.info("Entering mobile number first name in Personal details screen");
            clientUtils.sendText(driver,personalDetails.mobileNumber,getMobileorCreditCardNumber(mobileNumber));
            clientUtils.click(driver,personalDetails.licensePlateNumber);
            Assert.assertTrue(clientUtils.checkElementVisibility(driver,personalDetails.mobileNumberValidationErrorMessage));
            String creditCardNumber = hashMap.get("Credit card details").toString();
            clientUtils.sendText(driver,personalDetails.mobileNumber,getMobileorCreditCardNumber(creditCardNumber));
            Assert.assertTrue(clientUtils.checkElementVisibility(driver,personalDetails.mobileNumberValidationErrorMessage));
        }
        finally {
            index=0;
        }


    }
    //This test case is for validating error messages in all fields, when trying to proceed without entering any details in the fields
    @Test
    public void errorValidationMessagesCheck() throws Exception {
        try{
            testCaseExecutor = new TestCaseExecutor();
            currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
            hashMap= testCaseExecutor.createHashMapForSpecificRow(currentMethodName);
            homePage = PageFactory.initElements(driver,HomePage.class);
            personalDetails = PageFactory.initElements(driver,PersonalDetails.class);
            signupOptionsPage = PageFactory.initElements(driver,SignupOptionsPage.class);
            clientUtils = new ClientUtils(driver);
            clientUtils.click(driver,homePage.signUpButton);
            logger.info("Signup button is clicked");
            logger.info("Checking the Cookies text visibility");
            clientUtils.checkElementVisibility(driver,signupOptionsPage.cookiesText);
            clientUtils.click(driver,signupOptionsPage.cookiesAllowButton);
            logger.info("Clicked the Cookies text allow button");
            clientUtils.click(driver,signupOptionsPage.countryButton);
            logger.info("Clicked the Country button in Signup options page");
            logger.info("Taking the screenshot of Signup page");
            ++index;
            NewSetUp.getScreenshot(driver,index+"SignUpPage",currentMethodName);
            WebElement countryWebElement = signupOptionsPage.selectCountryFromList(driver,hashMap.get("Country").toString());
            logger.info("Selecting the country");
            clientUtils.click(driver,countryWebElement);
            WebElement chooseButtonWebElement = signupOptionsPage.selectChooseButton(driver,hashMap.get("Package").toString());
            logger.info("Clicking on the Choose button");
            clientUtils.click(driver,chooseButtonWebElement);
            logger.info("Waiting for personal details screen to load");
            clientUtils.waitForElement(driver,personalDetails.personalDetailsTitle);
            clientUtils.scrollTillElementIsVisible(driver,personalDetails.creditCardPaymentMethod);
            clientUtils.click(driver,personalDetails.creditCardPaymentMethod);
            clientUtils.click(driver,personalDetails.enterCreditCardDetailsButton);
            clientUtils.scrollTillElementIsVisible(driver,personalDetails.firstName);
            Assert.assertTrue(clientUtils.checkElementVisibility(driver,personalDetails.firstNameValidationErrorMessage));
            Assert.assertTrue(clientUtils.checkElementVisibility(driver,personalDetails.lastNameValidationErrorMessage));
            Assert.assertTrue(clientUtils.checkElementVisibility(driver,personalDetails.emailEmptyFieldErrorValidationMessage));
            clientUtils.scrollTillElementIsVisible(driver,personalDetails.password);
            Assert.assertTrue(clientUtils.checkElementVisibility(driver,personalDetails.passwordValidationErrorMessage));
            Assert.assertTrue(clientUtils.checkElementVisibility(driver,personalDetails.confirmPasswordValidationErrorMessage));
            clientUtils.scrollTillElementIsVisible(driver,personalDetails.mobileNumber);
            Assert.assertTrue(clientUtils.checkElementVisibility(driver,personalDetails.mobileNumberValidationErrorMessage));
            clientUtils.scrollTillElementIsVisible(driver,personalDetails.licensePlateNumber);
            Assert.assertTrue(clientUtils.checkElementVisibility(driver,personalDetails.licensePlateValidationErrorMessage));
            ++index;
            NewSetUp.getScreenshot(driver,index+"ErrorValidationMessages",currentMethodName);
        }
        finally {
            index=0;
        }

    }
    //This is a reusable method which covers entire flow. This method is invoked by test methods, with different test data/scenarios

    public void signUp(HashMap<Object, Object> hashMap,String currentMethodName) throws Exception {
        try{
            homePage = PageFactory.initElements(driver,HomePage.class);
            personalDetails = PageFactory.initElements(driver,PersonalDetails.class);
            signupOptionsPage = PageFactory.initElements(driver,SignupOptionsPage.class);
            clientUtils = new ClientUtils(driver);

            clientUtils.click(driver,homePage.signUpButton);
            logger.info("Signup button is clicked");
            logger.info("Checking the Cookies text visibility");
            clientUtils.checkElementVisibility(driver,signupOptionsPage.cookiesText);
            clientUtils.click(driver,signupOptionsPage.cookiesAllowButton);
            logger.info("Clicked the Cookies text allow button");
            clientUtils.click(driver,signupOptionsPage.countryButton);
            logger.info("Clicked the Country button in Signup options page");
            logger.info("Taking the screenshot of Signup page");
            ++index;
            NewSetUp.getScreenshot(driver,index+"SignUpPage",currentMethodName);

            // WebElement countryWebElement = clientUtils.dynamicXpathElements(driver,signupOptionsPage.selectCountry,"France");
            WebElement countryWebElement = signupOptionsPage.selectCountryFromList(driver,hashMap.get("Country").toString());
            logger.info("Selecting the country");
            clientUtils.click(driver,countryWebElement);
            WebElement chooseButtonWebElement = signupOptionsPage.selectChooseButton(driver,hashMap.get("Package").toString());
            logger.info("Clicking on the Choose button");
            clientUtils.click(driver,chooseButtonWebElement);
            logger.info("Waiting for personal details screen to load");
            clientUtils.waitForElement(driver,personalDetails.personalDetailsTitle);
            logger.info("Checking the title text of Personal details screen");
            Assert.assertEquals(personalDetails.personalDetailsTitle.getText().toString(),"PERSONAL DETAILS");
            logger.debug("Taking screenshot for personal details screen");
            ++index;
            NewSetUp.getScreenshot(driver,index+"PersonalDetails",currentMethodName);

            WebElement typeOfParkingWebElement = personalDetails.selectTypeOfParking(driver,hashMap.get("Type of Parking").toString());
            logger.info("Clicking on the type of parking web element either Personal or Company type");
            clientUtils.click(driver,typeOfParkingWebElement);
            logger.info("Entering first name in Personal details screen");
            clientUtils.sendText(driver,personalDetails.firstName,hashMap.get("First name").toString());
            logger.info("Entering first name in last details screen");
            clientUtils.sendText(driver,personalDetails.lastName,hashMap.get("Last name").toString());
            logger.info("Entering Email address in Personal details screen");
            clientUtils.sendText(driver,personalDetails.email,hashMap.get("Email address").toString());
            logger.info("Entering password in Personal details screen");
            clientUtils.sendText(driver,personalDetails.password,hashMap.get("Password").toString());
            logger.info("Entering confirm password in Personal details screen");
            clientUtils.sendText(driver,personalDetails.confirmPassword,hashMap.get("Confirm Password").toString());
            logger.info("Scrolling till the mobile number field is visible");
            clientUtils.scrollTillElementIsVisible(driver,personalDetails.mobileNumber);
            String mobileNumber = hashMap.get("Mobile Number").toString();
            logger.info("Entering mobile number first name in Personal details screen");
            clientUtils.sendText(driver,personalDetails.mobileNumber,getMobileorCreditCardNumber(mobileNumber));
            logger.info("Scrolling till license plate number is visible");
            clientUtils.scrollTillElementIsVisible(driver,personalDetails.licensePlateNumber);
            logger.info("Entering text in license plate field of Personal details screen");
            clientUtils.sendText(driver,personalDetails.licensePlateNumber,hashMap.get("License plate number").toString());
            ++index;
            logger.info("Taking screenshot after filling the details in passenger details screen");
            NewSetUp.getScreenshot(driver,index+"AfterFillingPersonalDetails",currentMethodName);
            if((hashMap.get("Payment method").toString()).equalsIgnoreCase("Credit card")){
                creditCardPaymentMethod(hashMap,currentMethodName);
            }
            else if((hashMap.get("Payment method").toString()).equalsIgnoreCase("Direct debit")){
                directDebitPaymentMethod(hashMap,currentMethodName);
            }
            else if((hashMap.get("Payment method").toString()).equalsIgnoreCase("PayPal")){
                paypalPaymentMethod(hashMap,currentMethodName);
            }
        }
        finally {
            index=0;
        }

    }
    //This method is used for converting scientific notation numbers to normal numeric numbers. It used with Mobile number and Credit card numbers
    public String getMobileorCreditCardNumber(String number){
        BigDecimal bigDecimal = new BigDecimal(number);
        long value = bigDecimal.longValue();
        return value+"";

    }
    //This method is related to credit card payment for France country
    public void creditCardPaymentMethodFrance(HashMap<Object, Object> hashMap,String currentMethodName) throws Exception {
        try{
            homePage = PageFactory.initElements(driver,HomePage.class);
            personalDetails = PageFactory.initElements(driver,PersonalDetails.class);
            signupOptionsPage = PageFactory.initElements(driver,SignupOptionsPage.class);
            clientUtils = new ClientUtils(driver);
            logger.info("Scrolling till the credit card payment method is visible");
            clientUtils.scrollTillElementIsVisible(driver,personalDetails.creditCardPaymentMethod);
            logger.info("Clicking the credit card payment method button");
            clientUtils.click(driver,personalDetails.creditCardPaymentMethod);

            logger.info("Checking the visibility status of Credit card number field");
            clientUtils.checkElementVisibility(driver,personalDetails.creditCardNumberFieldFrance);
            String creditCardNumber = hashMap.get("Credit card details").toString();
            logger.info("Entering text in credit card number field");
            clientUtils.sendText(driver,personalDetails.creditCardNumberFieldFrance,getMobileorCreditCardNumber(creditCardNumber));
            logger.info("Entering credit card holder name");
            clientUtils.sendText(driver,personalDetails.creditCardHolderNameFrance,hashMap.get("Credit card holder name").toString());
            logger.info("Entering credit card expiry month");
            String expiryMonth = hashMap.get("Expiry month").toString();
            clientUtils.sendText(driver,personalDetails.creditCardExpiryMonthFrance,getMobileorCreditCardNumber(expiryMonth));
            logger.info("Entering credit card expiry year");
            String expiryYear = hashMap.get("Expiry Year").toString();
            clientUtils.sendText(driver,personalDetails.creditCardExpiryYearFrance,getMobileorCreditCardNumber(expiryYear));
            logger.info("Entering credit card CVV code");
            String cvv = hashMap.get("CVV").toString();
            clientUtils.sendText(driver,personalDetails.creditCardCVVFrance,getMobileorCreditCardNumber(cvv));
            index=index+4;
            //This method is for taking the screenshot. Index is followed to differentiate each test case. Index count is increased by 1 for each screenshot.
            NewSetUp.getScreenshot(driver,index+"AfterfillingCreditcardetails",currentMethodName);
            logger.info("Checking element visibility of Sign up button");
            clientUtils.checkElementVisibility(driver,personalDetails.signupButton);
            logger.info("Clicking on Sign up button");
            clientUtils.click(driver,personalDetails.signupButton);
            ++index;
            NewSetUp.getScreenshot(driver,index+"Creditcardtermsandconditions",currentMethodName);
            logger.info("Scrolling till credit card agree button is visible");
            clientUtils.scrollTillElementIsVisible(driver,personalDetails.creditCardAgreeButton);
            logger.info("Clicking on Credit card Agree button");
            clientUtils.click(driver,personalDetails.creditCardAgreeButton);
            logger.info("Clicking on parking reminder preferences save button");
            ++index;
            NewSetUp.getScreenshot(driver,index+"ParkingreminderPreferencesscreen",currentMethodName);
            clientUtils.checkElementVisibility(driver,personalDetails.parkingReminderPreferencesSave);
            clientUtils.click(driver,personalDetails.parkingReminderPreferencesSave);
            logger.info("Checking element visibility of Go to your account button");
            ++index;
            NewSetUp.getScreenshot(driver,index+"Gotoyouraccountbuttonscreen",currentMethodName);
            clientUtils.checkElementVisibility(driver,personalDetails.goToYourAccountButton);
            Assert.assertTrue(clientUtils.checkElementVisibility(driver,personalDetails.welcomeTextParkMobile));
        }
        finally {
            index=0;
        }

    }
    //This method is for credit card payment method for other countries  Germany, Austria, Switzerland

    public void creditCardPaymentMethod(HashMap<Object, Object> hashMap,String currentMethodName){
        homePage = PageFactory.initElements(driver,HomePage.class);
        personalDetails = PageFactory.initElements(driver,PersonalDetails.class);
        signupOptionsPage = PageFactory.initElements(driver,SignupOptionsPage.class);
        clientUtils = new ClientUtils(driver);
        clientUtils.scrollTillElementIsVisible(driver,personalDetails.creditCardPaymentMethod);
        clientUtils.click(driver,personalDetails.creditCardPaymentMethod);
        clientUtils.click(driver,personalDetails.enterCreditCardDetailsButton);


    }
    //This method is for direct debit payment option
    public void directDebitPaymentMethod(HashMap<Object, Object> hashMap,String currentMethodName){
        homePage = PageFactory.initElements(driver,HomePage.class);
        personalDetails = PageFactory.initElements(driver,PersonalDetails.class);
        signupOptionsPage = PageFactory.initElements(driver,SignupOptionsPage.class);
        clientUtils = new ClientUtils(driver);
        clientUtils.scrollTillElementIsVisible(driver,personalDetails.directDebitPaymentMethod);
        clientUtils.click(driver,personalDetails.directDebitPaymentMethod);
        clientUtils.sendText(driver,personalDetails.directDebitIBAN,hashMap.get("IBAN").toString());
        clientUtils.sendText(driver,personalDetails.directDebitBIC,hashMap.get("BIC").toString());
        clientUtils.click(driver,personalDetails.signupButton);

    }
    //This method is for paypal payment option
    public void paypalPaymentMethod(HashMap<Object, Object> hashMap,String currentMethodName){
        homePage = PageFactory.initElements(driver,HomePage.class);
        personalDetails = PageFactory.initElements(driver,PersonalDetails.class);
        signupOptionsPage = PageFactory.initElements(driver,SignupOptionsPage.class);
        clientUtils = new ClientUtils(driver);
        clientUtils.scrollTillElementIsVisible(driver,personalDetails.paypalPaymentMethod);
        clientUtils.click(driver,personalDetails.paypalPaymentMethod);
    }
}
