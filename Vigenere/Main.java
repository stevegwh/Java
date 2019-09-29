// ERROR: cipher should count from 0 not 1

public class Main {
	
	private static int[] convertToAscii(String word) {
		int[] convertedWord = new int[word.length()];
		for(int i = 0; i < word.length(); i++) { //convert word to ascii number and store in new array
			char ch = word.charAt(i);
			int letterCode = (int)ch;
			convertedWord[i] = letterCode;
		}
		return convertedWord;
	}

	private static String encryptWord(int[] wordToEncrypt, int[] keyWord) {
		String encryptedWord = "";	

		for (int i = 0; i < wordToEncrypt.length; i++) {
			int j = i % keyWord.length; //repeats keyword if length is too short
			int offset = keyWord[j];
			offset -= offset <= 90 ? 64 : 96; //get the position in the alphabet for the offset
			int beforeOffset = wordToEncrypt[i];
			int afterOffset = beforeOffset + offset;
			if ((beforeOffset >= 65 && beforeOffset <= 90 && afterOffset > 90) || //check if word is upper/lower case and exceeding its bounds
				(beforeOffset >= 97 && beforeOffset <= 122 && afterOffset > 122)) {
				afterOffset -= 26;
			}
			char ch = (char) afterOffset;
			encryptedWord += ch;
		}
		return encryptedWord;
	}

	public static void main(String[] args) {
		if (args.length < 2 || !args[0].matches("^[a-zA-Z]*$") || !args[1].matches("^[a-zA-Z]*$") ) {
			System.out.println("Usage: <text to cipher> <key word>");
			return;
		}
		String wordToEncrypt = args[0];
		String keyWord = args[1];
		int[] wordToEncryptAscii = convertToAscii(wordToEncrypt);
		int[] keyWordAscii = convertToAscii(keyWord);

		String encryptedWord = encryptWord(wordToEncryptAscii, keyWordAscii);
		System.out.println(encryptedWord);
		
	}
}
