/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package audio_transcript;

import java.io.IOException;
import org.python.util.PythonInterpreter;
import org.python.core.*;

/**
 *
 * @author ernie
 */
public class Audio_transcript{

    public static void main(String[] args){
        String video_file = "C:/Users/ernie/Documents/ffmpeg_test/cameo.mp4";
        String audio_file = "C:/Users/ernie/Documents/ffmpeg_test/output.wav";

        extract_audio(video_file, audio_file);
    }

    public static void extract_audio(String video_file, String audio_file){
        try{
            // Use an ffmpeg library command to extract the audio from a video file and create a new audio file
            ProcessBuilder processBuilder = new ProcessBuilder("ffmpeg", "-i", video_file, "-vn", audio_file);           
            Process process = processBuilder.start();
            
            // Wait until process is complete
            int exit_code = process.waitFor();
            
            // If exit code 0 is returned, command was successful
            if (exit_code == 0){
                System.out.println("Audio file extracted successfully!");
                transcribe();
                System.exit(exit_code);
            } else{
                System.out.println("Error during audio extraction");
                System.exit(exit_code);
            }

        } catch (IOException | InterruptedException e){
            System.out.print("Error");
        }
    }
    
    public static void transcribe() throws PyException{
        
        PythonInterpreter interpreter = new PythonInterpreter();
        
        try{
            
            interpreter.execfile("src/audio_transcript/transcript.py");
        }
        
        finally{
            
            interpreter.close();
            
        }
    }
    
}
