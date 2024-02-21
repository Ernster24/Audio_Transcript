import speech_recognition as speech
import glob

r = speech.Recognizer()

audio_set = glob.glob("Test_Audio_2/*")

for audio_file in audio_set:
    with speech.AudioFile(audio_file) as source:
        audio_data = r.record(source)
        text = r.recognize_google(audio_data)
        print("Writing '" + text + "' to file...")
        file_no = str(audio_set.index(audio_file))
        transcript = open("transcript.txt", "a")
        transcript.write("[" + file_no + "] " + text + "\n")
        transcript.close()  