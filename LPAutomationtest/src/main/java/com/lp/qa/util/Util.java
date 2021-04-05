package com.lp.qa.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Util {
	public static String currentdate;
	public static String recStartDate;
	public static String recStartMonth;
	public static String recStartYear;
	public static String recEndDate;
	public static String startDate;
	public static void getCurrentDate() {
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		    Date date = new Date();  
		    System.out.println(formatter.format(date));
		    String date1=formatter.format(date).substring(0,2);
		    currentdate=date1;
		    if(date1.contains("01")||date1.contains("02")||date1.contains("03")||date1.contains("04")||date1.contains("05")||date1.contains("06")||date1.contains("07")||date1.contains("08")||date1.contains("09")){
		    	String date2=date1.substring(1);
		    	  currentdate=date2;
		    }
		   	    
	}
	public static void getRecDate(String RecDate) {	// 2021-04-05
		    String date1=RecDate.substring(8,10);
		    String month=RecDate.substring(5,7);
		    String yr=RecDate.substring(0,4);
		    
		    Integer month1=Integer.parseInt(month);		    
		    String month2=Month.of(month1).name();
		    recStartMonth=month2;
		    recStartYear=yr;
		    if(date1.contains("01")||date1.contains("02")||date1.contains("03")||date1.contains("04")||date1.contains("05")||date1.contains("06")||date1.contains("07")||date1.contains("08")||date1.contains("09")){
		    	String date2=date1.substring(1);
		    	recStartDate=date2;
		    }
		    System.out.println("Date:"+recStartDate);
		    System.out.println("Month:"+recStartMonth);
		    System.out.println("Year:"+recStartYear);
	}
	public static void getRecDate(String RecDate,String RecType,String frequencyRange) {
		Integer ran=Integer.parseInt(frequencyRange);
		 int range;
		 String date1=RecDate.substring(8,10);
		 String month=RecDate.substring(5,7);
		 String yr=RecDate.substring(0,4);
		
		 System.out.println("new date: "+date1);
		 System.out.println("new month: "+month);
		 System.out.println("new year: "+yr);

		  recStartYear = yr;
		    		    
		    if(RecDate.contains("01")||RecDate.contains("02")||RecDate.contains("03")||RecDate.contains("04")||RecDate.contains("05")||RecDate.contains("06")||RecDate.contains("07")||RecDate.contains("08")||RecDate.contains("09")){
		    	String date2=date1.substring(1);
		    	recStartDate=date2;
		    }
		    recStartMonth=month;
	//	    startDate=month+" "+recStartDate+" "+recStartYear; 
		  
		    System.out.println("Date:"+recStartDate);
		    Integer date=Integer.parseInt(recStartDate);
		    System.out.println("Month:"+recStartMonth);
		    System.out.println("Year:"+recStartYear);
		    Integer year=Integer.parseInt(recStartYear);
		    Integer month1=Integer.parseInt(recStartMonth);
		   
			
		    switch(RecType) {
		    
			    case "Weekly": 		
			    	range=ran-1;
			    	LocalDate week= LocalDate.of(year,month1,date);
			    	LocalDate EndDate = week.plus(range, ChronoUnit.WEEKS);
			    	String mothtest=Month.of(month1).name();
			      	System.out.println("Month from Weekly :"+mothtest);
			      	String shortMonth=mothtest.substring(0,3);
			      	startDate=shortMonth+" "+recStartDate+" "+recStartYear;       	
			    	
			    	recEndDate=EndDate.toString();
			    	String date2=recEndDate.substring(8,10);
					String month2=recEndDate.substring(5,7);
					Integer lastmonth=Integer.parseInt(month2);
					String yr2=recEndDate.substring(0,4);
					String mothtest2=Month.of(lastmonth).name();
					String shortMonth2=mothtest2.substring(0,3);
					 if(date2.contains("01")||date2.contains("02")||date2.contains("03")||date2.contains("04")||date2.contains("05")||date2.contains("06")||date2.contains("07")||date2.contains("08")||date2.contains("09")){
					    	String date3=date2.substring(1);
					    	date2=date3;
					    }
					 
					recEndDate=shortMonth2+" "+date2+" "+yr2;
					System.out.println("Rec startDate: " +startDate );
			    	System.out.println("Rec EndDate: " + recEndDate);
			    	break;
			    	
			    case "Monthly":
			    	range=ran-1;
			    	LocalDate months= LocalDate.of(year,month1,date);
			    	EndDate = months.plus(range, ChronoUnit.MONTHS);
			    	String mothtest3=Month.of(month1).name();
			      	System.out.println("Month :"+mothtest3);
			      	String shortMonth3=mothtest3.substring(0,3);
			      	startDate=shortMonth3+" "+recStartDate+" "+recStartYear;       	
			    	
			    	recEndDate=EndDate.toString();
			    	String date3=recEndDate.substring(8,10);
					String month3=recEndDate.substring(5,7);
					Integer lastmonth3=Integer.parseInt(month3);
					String yr3=recEndDate.substring(0,4);
					String mothtest4=Month.of(lastmonth3).name();
					String shortMonth4=mothtest4.substring(0,3);
					if(date3.contains("01")||date3.contains("02")||date3.contains("03")||date3.contains("04")||date3.contains("05")||date3.contains("06")||date3.contains("07")||date3.contains("08")||date3.contains("09")){
				    	String date4=date3.substring(1);
				    	date3=date4;
				    }
					recEndDate=shortMonth4+" "+date3+" "+yr3;
					System.out.println("Rec startDate: " +startDate );
			    	System.out.println("Rec EndDate: " + recEndDate);
			    	break;
			    				    	
			    case "Bi-Weekly":
			    	range=ran-1;
			    	LocalDate biWeekly= LocalDate.of(year,month1,date);			    	
			    	LocalDate biweekly2= biWeekly.plus(2, ChronoUnit.WEEKS);
			    	EndDate=biweekly2.plus(range, ChronoUnit.WEEKS);
			    	String mothtest5=Month.of(month1).name();
			      	System.out.println("Month from Weekly :"+mothtest5);
			      	String shortMonth5=mothtest5.substring(0,3);
			      	startDate=shortMonth5+" "+recStartDate+" "+recStartYear;       	
			    	
			    	recEndDate=EndDate.toString();
			    	String date5=recEndDate.substring(8,10);
					String month5=recEndDate.substring(5,7);
					Integer lastmonth5=Integer.parseInt(month5);
					String yr5=recEndDate.substring(0,4);
					String mothtest6=Month.of(lastmonth5).name();
					String shortMonth6=mothtest6.substring(0,3);
					if(date5.contains("01")||date5.contains("02")||date5.contains("03")||date5.contains("04")||date5.contains("05")||date5.contains("06")||date5.contains("07")||date5.contains("08")||date5.contains("09")){
				    	String date4=date5.substring(1);
				    	date5=date4;
				    }
					recEndDate=shortMonth6+" "+date5+" "+yr5;
					System.out.println("Rec startDate: " +startDate );
			    	System.out.println("Rec EndDate: " + recEndDate);
			    	break;
			    			    	
			    case "Semi-Monthly":
			    	range=ran-1;
			    	int range1=ran-1;
			    	LocalDate semiMonthly = LocalDate.of(year,month1,date);	
			    	LocalDate semiMonthly2 = semiMonthly.plus(2, ChronoUnit.WEEKS);
			    	LocalDate semimonthly3 = semiMonthly2.plus(range1, ChronoUnit.DAYS);
			    	EndDate=semimonthly3.plus(range, ChronoUnit.WEEKS);
			    	String mothtest7=Month.of(month1).name();
			      	System.out.println("Month from Weekly :"+mothtest7);
			      	String shortMonth7=mothtest7.substring(0,3);
			      	startDate=shortMonth7+" "+recStartDate+" "+recStartYear;       	
			    	
			    	recEndDate=EndDate.toString();
			    	String date7=recEndDate.substring(8,10);
					String month7=recEndDate.substring(5,7);
					Integer lastmonth7=Integer.parseInt(month7);
					String yr7=recEndDate.substring(0,4);
					String mothtest8=Month.of(lastmonth7).name();
					String shortMonth8=mothtest8.substring(0,3);
					if(date7.contains("01")||date7.contains("02")||date7.contains("03")||date7.contains("04")||date7.contains("05")||date7.contains("06")||date7.contains("07")||date7.contains("08")||date7.contains("09")){
				    	String date4=date7.substring(1);
				    	date7=date4;
				    }
					recEndDate=shortMonth8+" "+date7+" "+yr7;
					System.out.println("Rec startDate: " +startDate );
			    	System.out.println("Rec EndDate: " + recEndDate);
			    	
			    	
			    	break;
			    	
			    case "Quaterly":
			    	/*
			    	LocalDate quaterly= LocalDate.of(year,month1,date);			    	
			    	LocalDate quaterly2= quaterly.plus(3, ChronoUnit.MONTHS);
			    	recEndDate=quaterly2.plus(range, ChronoUnit.MONTHS);
			    	System.out.println("Rec StartDate : " +startDate );
			    	System.out.println("Rec EndDate: " + recEndDate);
			    	*/			    	
			    	break;
			    
			    case "Sem-Annually":
			    	/*
			    	LocalDate semiAnnually= LocalDate.of(year,month1,date);		
			    	LocalDate SemiAnnually2 = semiAnnually.plus(6, ChronoUnit.MONTHS);
			    	System.out.println("Rec StartDate : " + startDate);
			    	System.out.println("Semi-Annually Recurring : " + SemiAnnually2);
			    		*/		    	
			    	break;
			    
			    case "Annually":
			    	range=ran-1;
			    	LocalDate months8= LocalDate.of(year,month1,date);
			    	EndDate = months8.plus(range, ChronoUnit.MONTHS);
			    	String mothtest9=Month.of(month1).name();
			      	System.out.println("Month :"+mothtest9);
			      	String shortMonth9=mothtest9.substring(0,3);
			      	startDate=shortMonth9+" "+recStartDate+" "+recStartYear;       	
			    	
			    	recEndDate=EndDate.toString();
			    	String date9=recEndDate.substring(8,10);
					String month9=recEndDate.substring(5,7);
					Integer lastmonth9=Integer.parseInt(month9);
					String yr9=recEndDate.substring(0,4);
					String mothtest10=Month.of(lastmonth9).name();
					String shortMonth10=mothtest10.substring(0,3);
					if(date9.contains("01")||date9.contains("02")||date9.contains("03")||date9.contains("04")||date9.contains("05")||date9.contains("06")||date9.contains("07")||date9.contains("08")||date9.contains("09")){
				    	String date4=date9.substring(1);
				    	date9=date4;
				    }
					recEndDate=shortMonth10+" "+date9+" "+yr9;
					System.out.println("Rec startDate: " +startDate );
			    	System.out.println("Rec EndDate: " + recEndDate);
			       	break;
			  
		    		
		    }	
		
	}

}
