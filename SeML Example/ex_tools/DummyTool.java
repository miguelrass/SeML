package ex_tools;

import java.util.Set; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
* Tool
*/
public class DummyTool {
	
	static{
		System.out.println("ReplaceTool was loaded");
	}
	
	public static String ShouldBeCalledFirst(ArrayList<List<String>> cmd){
		return "I am a high priority function: " + cmd;
	}

	public static String ShouldBeCalledSecond(ArrayList<List<String>> cmd){

		return "I am a normal priority function: " + cmd;
	}
	
	public static String ShouldBeCalledThird(ArrayList<List<String>> cmd){

		return "I am a low priority function: " + cmd;
	}
}
