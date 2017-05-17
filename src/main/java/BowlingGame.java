public class BowlingGame {

    public int getBowlingScore(String bowlingCode) { 	
    	int count=0; // statistical score 
    		
    	String extraString; // extra bat 
    	String tenString; // ten bat 
    	
    	// data handling
    	String[] strArray=bowlingCode.split("\\|\\|");
    	if(strArray.length==1){
    		extraString=null;
    		tenString=bowlingCode;
    	}else{
    		extraString=strArray[1]; 
        	tenString=strArray[0];  
    	}
    	String[] tenArray=tenString.split("\\|"); // get ten notes
    	
    	
    	// initialize ten counts
    	int[] countArray=new int[10];  
    	
    	// compute front 8 counts
    	for(int i=0;i<8;i++){
    		countArray[i]=calcCurrentCount(tenArray[i], tenArray[i+1], tenArray[i+2]);
    	}
    	
    	// compute last 2 counts 	
    	countArray[8]=calcCurrentCount(tenArray[8], tenArray[9], extraString);
    	countArray[9]=calcCurrentCount(tenArray[9], extraString, null);
    	
    	// compute total count
    	for(int i=0;i<10;i++){
    		count+=countArray[i];
    	}
    	  	  	
        return count;
    }
    
    /**
     * compute current count 
     * @param currentStr current note
     * @param nextStr1 next note
     * @param nextStr2 next next note
     * @return
     */
    public int calcCurrentCount(String currentStr, String nextStr1, String nextStr2){
    	int count=0; // current count;
    	
    	// condition of strike
    	if(currentStr.equals("X")){
    		count+=10;
    		
    		int remindNum=2; // remind bat number
    		while(remindNum>0){ 			
    			// next condition is strike
    			if(nextStr1.equals("X")){
    				count+=10;
    				remindNum--;
    				nextStr1=nextStr2;
    			}
    			
    			// next condition is spare
    			else if(nextStr1.contains("/")){
    				count+=10;
    				break;
    			}
    			
    			// next condition is other condition or extra condition
    			else{
    				char[] charArray=nextStr1.toCharArray();
    	    		for(int i=0;i<charArray.length-2+remindNum;i++){
    	    			char cTemp=charArray[i];
    	    			
    	    			if(cTemp=='X'){
    	    				count+=10;
    	    			}else if(cTemp=='-'){
    	    				count+=0;
    	    			}else{
    	    				count+=cTemp-48;
    	    			}
    	    		} 
    	    		break;
    			}
    		}
    	}
    	
    	// condition of spare
    	else if(currentStr.contains("/")){
    		count+=10;
    		
    		int remindNum=1;
    		while(remindNum>0){ 
    			// next condition is strike
    			if(nextStr1.equals("X")){
    				count+=10;
    				remindNum--;
    			}
    			
    			// next condition is other condition or extra condition
    			else{
    				char cTemp=nextStr1.charAt(0);
    				
	    			if(cTemp=='X'){
	    				count+=10;
	    			}else if(cTemp=='/'){
    					count+=10;
    				}			
    				else if(cTemp=='-'){
	    				count+=0;
	    			}else{
	    				count+=cTemp-48;
	    			}
    	    		break;
    			}
    		}
    	}
    	
    	//other condition
    	else{
    		char[] charArray=currentStr.toCharArray();
    		for(int i=0;i<charArray.length;i++){
    			char cTemp=charArray[i];
    			
    			if(cTemp=='-'){
    				count+=0;
    			}else{
    				count+=cTemp-48;
    			}
    		} 
    	}
    	
    	return count;
    }
}
