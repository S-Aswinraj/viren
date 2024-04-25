package Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class Report {
    ExtentReports extent;
    ExtentTest test;
    String desc="Registration";
    String author ="ABC";

//    @BeforeSuite
    protected void startReport(){
        // to create blank html
        ExtentSparkReporter reporter = new ExtentSparkReporter("./TestReport.html");
        // generate report and add to blank html
        extent =new ExtentReports();
        extent.attachReporter(reporter);
    }

//    @AfterSuite
    protected void stopReport(){
        extent.flush();
    }
//
//    @BeforeClass
    public void creatingTest(){
       test = extent.createTest(desc);
        test.assignAuthor(author);
    }

    public void stepReport(String status, String description){
        switch (status.toLowerCase()){
            case "pass" :
                test.pass(description);
                break;
            case "fail" :
                test.fail(description);
                break;
            default:
                System.err.println("Status is not defined");
                break;
        }

    }




}
