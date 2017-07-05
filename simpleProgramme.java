public class simpleProgramme {

	public int sesli(String word){
		String sesliler = "aeoöuüýi";
		int sum = 0;
		for(int i=0; i<word.length(); i++){
			if(sesliler.indexOf(word.charAt(i)) != -1){
				sum++;
			}
		}
		return sum;
	}

	public int sessiz(String word){
		return word.length() - sesli(word);
	}

}
