package opendata;

import java.io.IOException;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;

public class ReconnaissanceVocale 
{

	private static final String ACOUSTIC_MODEL = "langages/dictionnaire_fr";
	private static final String DICTIONARY_PATH ="langages/dictionnaire_fr/fr.dict";
	private static final String GRAMMAR_PATH = "langages/dictionnaire_fr/dialog/";
	private static final String LANGUAGE_MODEL ="langages/dictionnaire_fr/fr-small.lm";
	private LiveSpeechRecognizer jsgfRecognizer;
	private boolean run = true;
	private boolean isStart = false;

	
	public void stop()
	{
		if (run)
		{
			run = false;
			if (null != jsgfRecognizer)
				jsgfRecognizer.stopRecognition();
			isStart = false;
		}
	}


	public String getCategorie() throws Exception 
	{
		if (!isStart)
		{
			Configuration configuration = new Configuration();
			configuration.setAcousticModelPath(ACOUSTIC_MODEL);
			configuration.setDictionaryPath(DICTIONARY_PATH);
			configuration.setGrammarPath(GRAMMAR_PATH);
			configuration.setUseGrammar(true);
			configuration.setGrammarName("dialog");
			LiveSpeechRecognizer jsgfRecognizer = new LiveSpeechRecognizer(configuration);
			configuration.setUseGrammar(false);
			configuration.setLanguageModelPath(LANGUAGE_MODEL);
			this.jsgfRecognizer = jsgfRecognizer;
		}

		jsgfRecognizer.startRecognition(true);
		run = true;
		isStart = true;
		String utterance = "";
		while (run) {
			System.out.println("Parlez ...");
			utterance = jsgfRecognizer.getResult().getHypothesis();

			if(!utterance.equals("<unk>") && !utterance.equals(""))
			{
				System.out.println(utterance);
				break;
			}
		}
		jsgfRecognizer.stopRecognition();
		run = false;
		if (utterance.contains(" ")) return utterance.substring(0,utterance.indexOf(" "));
		System.out.println("Dit : "+utterance);
		return utterance;
	}
}