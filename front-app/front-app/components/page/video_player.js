import styles from './video_player.module.css'
import LoadingSpinner from '../ui/loading_spinner'

export default function VideoPlayer({videoDetails}) {
    
    let currentTime = 0
    let videoDuration = -1
    let videoPercentage = 0
    let contextTime = 0

    function playButtonOnClick() {
        let videoPlayer = document.getElementById('video_player')
        let playButton = document.getElementById('play_button')
        let pauseButton = document.getElementById('pause_button')
        let timeBarInputFake = document.getElementById('time_bar_fake')
        let timeBarInput = document.getElementById('time_bar')

        playButton.style.display = 'none'
        pauseButton.style.display = 'block'
        
        timeBarInputFake.style.display = 'none'
        timeBarInput.style.display = 'block'

        videoPlayer.play()
    }

    function pauseButtonOnClick() {
        let videoPlayer = document.getElementById('video_player')
        let playButton = document.getElementById('play_button')
        let pauseButton = document.getElementById('pause_button')

        pauseButton.style.display = 'none'
        playButton.style.display = 'block'
        videoPlayer.pause()
    }

    function onCanPlay() {
        let videoPlayer = document.getElementById('video_player')
        let loadingSpinner = document.getElementById('loading_spinner')

        loadingSpinner.style.display = 'none'
        videoPlayer.style.display = 'block'
    }

    function secondsToTimeString(seconds) {
        let minutes = Math.floor(seconds / 60)
        let secondsLeft = Math.floor(seconds - minutes * 60)
        return `${minutes}:${secondsLeft}`
    }

    function timeBarChange () {
        let videoPlayer = document.getElementById('video_player')
        let timeBarInput = document.getElementById('time_bar')
        let currentTimeLabel = document.getElementById('current_time_label')
        let videoPercentageLabel = document.getElementById('video_percentage_label')
        let loadingSpinner = document.getElementById('loading_spinner')

        if (timeBarInput.value) {
            videoPercentage = timeBarInput.value
            currentTime = videoDuration * (videoPercentage / 100)
            contextTime = currentTime
            console.log('current time', currentTime)
        }
        
        // console.log("video percentage", videoPercentage)
        videoPlayer.style.display = 'none'
        loadingSpinner.style.display = 'block'
        
        videoPlayer.src = `video/${videoDetails.slug}/${videoPercentage}`
        currentTimeLabel.innerHTML = ' ' + secondsToTimeString(currentTime) + ' '
        videoPercentageLabel.innerHTML = ' ' + parseInt(videoPercentage) + '% '

        console.log("time bar change")
    }

    function onTimeUpdate(event) {
        console.log('context time', contextTime)

        let currentTimeLabel = document.getElementById('current_time_label')
        let videoPercentageLabel = document.getElementById('video_percentage_label')
        let timeBarInput = document.getElementById('time_bar')

        currentTime = contextTime + event.target.currentTime
        videoPercentage = (currentTime / videoDuration) * 100

        currentTimeLabel.innerHTML = ' ' + secondsToTimeString(currentTime) + ' '
        videoPercentageLabel.innerHTML = ' ' + parseInt(videoPercentage) + '% '
        timeBarInput.value = videoPercentage
    }

    function init() { // TODO show hide video player
        setTimeout(() => {
            let videoPlayer = document.getElementById('video_player')
            let currentTimeLabel = document.getElementById('current_time_label')
            let videoDurationLabel = document.getElementById('video_duration_label')
            let videoPercentageLabel = document.getElementById('video_percentage_label')
            
            videoDuration = videoPlayer.duration
            
            currentTimeLabel.innerHTML = ' ' + secondsToTimeString(currentTime) + ' '
            videoDurationLabel.innerHTML = ' ' + secondsToTimeString(videoDuration) + ' '
            videoPercentageLabel.innerHTML = ' ' + videoPercentage + '% '

            videoPlayer.addEventListener('timeupdate', (event) => onTimeUpdate(event))
            videoPlayer.addEventListener('canplay', (event) => onCanPlay())
          }, "1000") // Wait until document is loaded
    }

    return <>
        {init()}
        <div className={styles.video_player}>

            <div>
                <video 
                    id='video_player'
                    className={styles.video_screen}>
                    <source src={`video/${videoDetails.slug}`} type="video/mp4"></source>
                </video>

                <div id='loading_spinner' style={{display: 'none'}}>
                    <LoadingSpinner></LoadingSpinner>
                </div>
            </div>

            <div>
                <div className={styles.video_score_container}>
                    <div className={styles.video_score_element} id='current_time_label'></div>
                    <div className={styles.video_score_element}>/</div>
                    <div className={styles.video_score_element} id='video_duration_label'></div>
                    <div className={styles.video_score_element}>
                        
                        <button id='play_button' onClick={() => playButtonOnClick()}>
                            <svg width="20px" height="20px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M16.6582 9.28638C18.098 10.1862 18.8178 10.6361 19.0647 11.2122C19.2803 11.7152 19.2803 12.2847 19.0647 12.7878C18.8178 13.3638 18.098 13.8137 16.6582 14.7136L9.896 18.94C8.29805 19.9387 7.49907 20.4381 6.83973 20.385C6.26501 20.3388 5.73818 20.0469 5.3944 19.584C5 19.053 5 18.1108 5 16.2264V7.77357C5 5.88919 5 4.94701 5.3944 4.41598C5.73818 3.9531 6.26501 3.66111 6.83973 3.6149C7.49907 3.5619 8.29805 4.06126 9.896 5.05998L16.6582 9.28638Z" stroke="#000000" strokeWidth="2" strokeLinejoin="round"/>
                            </svg>
                        </button>

                        <button id='pause_button' onClick={() => pauseButtonOnClick()} style={{display: 'none'}}>
                            <svg width="20px" height="20px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M8 5V19M16 5V19" stroke="#000000" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                            </svg>
                        </button>
                    
                    </div>
                    <div className={styles.video_score_element}>
                        <input type="range" id="time_bar_fake" min={0} max={100} value={0} onChange={() => {}} />
                        <input type="range" id="time_bar" min={0} max={100} onChange={() => timeBarChange()} style={{display: 'none'}} />
                    </div>
                    <div className={styles.video_score_element} id='video_percentage_label'></div>
                </div>
            </div>

        </div>
    </>
}