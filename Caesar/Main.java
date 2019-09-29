public class Main {

	public static void main(String[] args) {
		if (args.length < 2 || !args[0].matches("^[a-zA-Z]*$") || !args[1].matches("^[0-9]*$")) {
			System.out.println("Usage: <text to cipher> <number to shift by>");
			return;
		}
		int offset = Integer.parseInt(args[1]);
		offset = offset % 26;
		String word = args[0];
		int[] ascii = new int[word.length()];
		
		for(int i = 0; i < word.length(); i++) { //convert word to ascii number and store in new array
			char ch = word.charAt(i);
			int letterCode = (int)ch;
			ascii[i] = letterCode;
		}

		String encryptedWord = "";	
		for (int beforeOffset : ascii) {
			int afterOffset = beforeOffset + offset;
			if ((beforeOffset >= 65 && beforeOffset <= 90 && afterOffset > 90) || //check if word is upper/lower case and exceeding its bounds
				(beforeOffset >= 97 && beforeOffset <= 122 && afterOffset > 122)) {
				afterOffset -= 26;
			}
			char ch = (char) afterOffset;
			encryptedWord += ch;
		}

		System.out.println(encryptedWord);
		
	}

}
