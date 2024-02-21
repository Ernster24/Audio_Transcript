import ffmpeg

video = "test.mp4"
audio = "test_out.wav"

def convert_audio(video_file, audio_file):
    try:
        ffmpeg.input(video_file).output(audio_file).run(overwrite_output=True)
    except ffmpeg.Error as e:
        print(f"Error: {e.stderr}")


convert_audio(video, audio)