import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import groovy.json.JsonSlurper
import com.kms.katalon.core.util.KeywordUtil

//WS.sendRequestAndVerify(findTestObject('Auth/POST/CreateToken'))

TestObject requestObject = findTestObject('Auth/POST/CreateToken')
response = WS.sendRequest(requestObject)

if (response.getStatusCode() == 200) {
    JsonSlurper jsonSlurper = new JsonSlurper()
    def jsonResponse = jsonSlurper.parseText(response.getResponseText())

    // Check if the "token" property is not blank
    String token = jsonResponse.token
    if (token != null && !token.isEmpty()) {
        // Set the "token" as a global variable
        GlobalVariable.Global_AuthToken = token
        println("Global variable set to: ${token}")
    } else {
        KeywordUtil.markFailed("The 'token' property in the API response is blank.")
    }
} else {
    KeywordUtil.markFailed("API request failed with status code ${response.getStatusCode()}")
}

