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
	
	public static int lineLength = 80;
	public static final int mode = 0;
	char alignType = 'C';
	
	public static void main(String[] args) {	
		if (args.length != 2) {
			System.out.println("java AlignText file_name line_length ");
		}
		AllignText at = new AllignText();
		String[] paragraphs = FileUtil.readFile(args[0]);
		int lineLength = Integer.parseInt(args[1]);			
		at.alignParagraph(paragraphs[0]);
	}
	
	
	public void alignParagraph(String paragraph) {
		String[] words = tokenizeParagraph(paragraph);
		int[] wordsLength = calculateWordLengths(words);
		
		if (0 == mode) {
			int indexStart = 0, indexEnd = 0, countCharacters = 0;
			
			while (indexEnd < words.length - 1) {
				countCharacters = 0;
				indexEnd++;
				countCharacters += wordsLength[indexStart];
				
				while (countCharacters <= lineLength && indexEnd <= words.length - 1) {
					countCharacters += wordsLength[indexEnd++] + 1;
				}
				if (countCharacters > lineLength && indexEnd > indexStart + 1) {
					indexEnd--;
					countCharacters -= wordsLength[indexEnd] + 1;
				}
				indexEnd--;
				int spacesForFilling = lineLength - countCharacters;
				
				if (alignType == 'R') {
					printRightAligned(words, indexStart, indexEnd, spacesForFilling);
				} else if ('L' == alignType) {
					printLeftAligned(words, indexStart, indexEnd, spacesForFilling);
				} else if ('J' == alignType) {
					printJustifiedLine(words, indexStart, indexEnd, spacesForFilling);
				} else if ('C' == alignType) {
					printCenterAlignedLine(words, indexStart, indexEnd, spacesForFilling);
				}
				indexEnd++;
				indexStart = indexEnd;
			}
		} else if (1 == mode) {
			boolean lastWord = false, firstLoop = false;
			int countCharacters = 0, spacesToFill = lineLength, flag = 0;
			String wordsToPrint = "";
			String spacesToPrint = "";
			for (int i = 0; i < words.length; i++) {
				firstLoop = false;
				//System.out.println(words[i] + " cnt " + (countCharacters) + " cntA " + (countCharacters + 1 + wordsLength[i]));
				if (((countCharacters + 1 + wordsLength[i]) <= lineLength)) {
					firstLoop = true;
					//System.out.println(words[i] + " cnt " + (countCharacters));
					wordsToPrint += words[i] + " ";
					//System.out.println(wordsToPrint);
					countCharacters += wordsLength[i] + 1;
					if (i == words.length - 1) {
						lastWord = true;
					}
				} else {
					flag = 1;
				}
				if (((countCharacters + 1 + wordsLength[i]) > lineLength) || true == lastWord || words[i].length() > lineLength){
					if (words[i].length() + 1 > lineLength && countCharacters == 0) {
						countCharacters = lineLength;
					}
					lastWord = false;
					if (words[i].length() + 1 <= lineLength) {
						firstLoop = true;
					}
					spacesToFill = lineLength - countCharacters;
					for (int j = 0; j < spacesToFill; j++) {
						spacesToPrint += " ";
					}
					if (wordsLength[i] > lineLength) {
						wordsToPrint += "\n" + words[i];
						
					}
					if (i != words.length - 1 && firstLoop == true && flag == 1) {
						i--;
					}
					flag = 0;
					
					printRightAlignedMode1(spacesToPrint, wordsToPrint);

					
					wordsToPrint = spacesToPrint = "";
					spacesToFill = lineLength; countCharacters = 0;
				}
			}
		}
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


	private void printRightAligned(String[] words, int indexStart, int indexEnd, int spacesForFilling) {
		for (int i = 0; i < spacesForFilling; i++) {
			System.out.print(" ");
		}
		for (int i = indexStart; i < indexEnd; i++) {
			System.out.print(words[i] + " ");
		}
		System.out.print(words[indexEnd]);
		System.out.println();
	}

	private void printRightAlignedMode1(String spaces, String words) {
		System.out.println(spaces + words);
	}

	
	private void printLeftAligned(String[] words, int indexStart, int indexEnd, int spacesForFilling) {
		for (int i = indexStart; i < indexEnd; i++) {
			System.out.print(words[i] + " ");
		}
		System.out.print(words[indexEnd]);
		
		for (int i = 0; i < spacesForFilling; i++) {
			System.out.print(" ");
		}
		System.out.println();
	}
	
	public void printJustifiedLine(String[] words, int indexStart, int indexEnd, int spacesForFilling) {
		//Count num of gaps between the words
		int gaps = indexEnd - indexStart;
		//Recalculate spaces for filling including gaps between words
		spacesForFilling += gaps;
		int spacesPerGapMinimum = 0, timesExtraSpacesNeeded = 0;
		//Avoid division by zero
		if (gaps > 0) {
			//Check spaces per gap minimum
			spacesPerGapMinimum = spacesForFilling / gaps;
			//Check how many times an extra space needs to be added
			timesExtraSpacesNeeded = spacesForFilling % gaps;
		}
		//Print each word followed by the minimum number of spaces needed, followed by an extra space when needed (extra spaces added on the right of the line)
		for (int i = indexStart; i < indexEnd; i++) {
			System.out.print(words[i]);
			for (int j = 0; j < spacesPerGapMinimum; j++) {
				System.out.print(" ");
			}
			if (indexEnd - 1 - i < timesExtraSpacesNeeded) {
				System.out.print(" ");
			}
		}
		System.out.println(words[indexEnd]);
	}
	
	public void printCenterAlignedLine(String[] words, int indexStart, int indexEnd, int spacesForFilling) {
		for (int i = 0; i < spacesForFilling / 2; i++) {
			System.out.print(" ");
		}
		if (spacesForFilling % 2 == 1) {
			System.out.print(" ");
		}
		for (int i = indexStart; i < indexEnd; i++) {
			System.out.print(words[i] + " ");
		}
		System.out.print(words[indexEnd]);
		for (int i = 0; i < spacesForFilling / 2; i++) {
			System.out.print(" ");
		}
		System.out.println();
	}
	
	private int[] calculateWordLengths(String[] words) {
		int[] wordLengths = new int[words.length];
		
		for (int i = 0; i < wordLengths.length; i++) {
			wordLengths[i] = words[i].length();
		}
		return wordLengths;
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


