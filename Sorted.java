package task2;
import java.util.*;


public class Sorted {
	 public static void sort(List<Integer> list, boolean ascending) {
	        if (ascending) {
	            Collections.sort(list);
	        } else {
	            Collections.sort(list, Collections.reverseOrder());
	        }
	    }
}
