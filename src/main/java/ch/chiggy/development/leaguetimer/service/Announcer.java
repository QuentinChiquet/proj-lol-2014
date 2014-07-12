package ch.chiggy.development.leaguetimer.service;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class Announcer {

  private static final String VOICENAME = "kevin16";

  private VoiceManager voiceManager;
  private Voice voice;

  public Announcer() {
    voiceManager = VoiceManager.getInstance();
    voice = voiceManager.getVoice(VOICENAME);
    voice.setRate(80);
    voice.allocate();
  }

  public void speak(String speech) {
    voice.speak(speech);
  }
}
