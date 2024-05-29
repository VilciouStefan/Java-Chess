package main;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
    private Clip clip;
    private String[] playlist;
    private int currentTrackIndex = 0;

    public AudioPlayer(String[] playlist) {
        this.playlist = playlist;
    }

    public void start() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        playTrack(currentTrackIndex);
    }

    private void playTrack(int index) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(playlist[index]);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        
        clip.addLineListener(event -> {
            if (event.getType() == LineEvent.Type.STOP) {
                clip.close();
                currentTrackIndex = (currentTrackIndex + 1) % playlist.length; // Loop back to the first track if at the end
                try {
                    playTrack(currentTrackIndex);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        clip.start();
    }
}