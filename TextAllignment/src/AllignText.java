import java.util.StringTokenizer;

/**
 * The AlignText class is the application.
 * class of the file - contains the main-
 * and is responsible for printing text
 * on the console in the four classic
 * types of alignment, left, center,
 * right, and justified
 * 
 * @author  Dimitrios Malonas
 * @version 7.0
 *
 */

/**
 * cmd line args: 
 * C:\Users\Dimitrios\Desktop\St-Andrews-CW\CS5001\PR1\170011408-Practical_1\CS5001-p1-aligntext\test_poppins.txt 
 * 50
 */

public class AllignText {
	
	public static void main(String[] args) {	
		if (args.length != 2) {
			System.out.println("java AlignText file_name line_length ");
		}
		AllignText at = new AllignText();
		String[] paragraphs = FileUtil.readFile(args[0]);
		int length = Integer.parseInt(args[1]);			
		at.alignParagraph(paragraphs[0]);
	}
	
	
	public void alignParagraph(String paragraph) {
		String[] words = tokenizeParagraph(paragraph);
		int[] wordsLength = calculateWordLengths(words);
//		int len = strArr.length();
//		int cnt = 0;
//		for (int i = 0; i < len; i++) {
//			if ((i % 80) == 0) {
//				cnt++;
//			}
//		}
//		
//		int last_line_chars = 0;
//		int other_chars = 0;
//		int cnt2 = 0;
//
//		for (int i = 0; i < len; i++) {
//			other_chars++;
//			if (i % 80 == 0) {
//				cnt2++;
//				if (cnt2 == cnt) {
//					break;
//				}
//			}
//		}
//		last_line_chars = len - other_chars;
//		System.out.println(last_line_chars);
//		int diff = 80 - last_line_chars;
//		System.out.println(diff);
//		cnt2 = 0;
//		for (int i = 0; i < len; i++) {
//			if ((i % 80) == 0) {
//				cnt2++;
//				System.out.println();
//				if (cnt2 == cnt) {
//					for (int j = 0; j < diff; j++) {
//						System.out.print(" ");
//					}
//				}
//			}
//			System.out.print(strArr.charAt(i));
//		}
	}


	private int[] calculateWordLengths(String[] words) {
		// TODO Auto-generated method stub
		return null;
	}


	private String[] tokenizeParagraph(String paragraph) {
		// TODO Auto-generated method stub
	   StringTokenizer st = new StringTokenizer(paragraph," ");
	   String[] words = new String[st.countTokens()];
	   
	   int i = 0;
	   while(st.hasMoreTokens()) {
		   words[i] = st.nextToken();
		   i++;
	   }
	   return words;
	}

}


