package com.qa.KarateTest;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.junit4.Karate;
//import com.intuit.karate.junit5.Karate; 
import org.junit.runner.RunWith;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Used Karate Feature Tag for the execution.
 * For Execution here we used Karate API + JUnit 4
 * @author Bishnu
 *
 */
@KarateOptions(tags = "@Weather_RHRREAD")
@RunWith(Karate.class)
public class KarateRunner {
	

}