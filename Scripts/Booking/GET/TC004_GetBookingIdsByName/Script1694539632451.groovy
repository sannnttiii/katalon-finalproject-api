import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import groovy.json.JsonSlurper
import com.kms.katalon.core.util.KeywordUtil

response = WS.sendRequestAndVerify(findTestObject('Booking/GET/GetBookingIdsByName'))

if (response.getStatusCode() == 200) {
	JsonSlurper jsonSlurper = new JsonSlurper()
	def jsonResponse = jsonSlurper.parseText(response.getResponseText())
	println(jsonResponse.bookingid.toString().replaceAll("\\[|\\]", ""))
	println(GlobalVariable.Global_NewId)
	if (jsonResponse.bookingid.toString().replaceAll("\\[|\\]", "") == GlobalVariable.Global_NewId) {
		KeywordUtil.markPassed("Data Existed")
	} else {
		KeywordUtil.markFailed("The response is blank.")
	}
} else {
	KeywordUtil.markFailed("API request failed with status code ${response.getStatusCode()}")
}
