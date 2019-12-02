

public class AllignText {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] paragraphs = FileUtil.readFile(args[0]); //Read file into String
		int length = Integer.parseInt(args[1]);
		System.out.println(length);
	}

}
