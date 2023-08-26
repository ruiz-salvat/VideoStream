import styles from './video_player.module.css'
import LoadingSpinner from '../ui/loading_spinner'

export default function VideoPlayer({videoDetails}) {
    
    let currentTime = 0
    let videoDuration = -1
    let videoPercentage = 0
    let contextTime = 0
    let isPlaying = false

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
        isPlaying = true
    }

    function pauseButtonOnClick() {
        let videoPlayer = document.getElementById('video_player')
        let playButton = document.getElementById('play_button')
        let pauseButton = document.getElementById('pause_button')

        pauseButton.style.display = 'none'
        playButton.style.display = 'block'
        videoPlayer.pause()
        isPlaying = false
    }

    function fullScreenButtonOnClick() {
        let videoPlayer = document.getElementById('video_player')

        if (videoPlayer.requestFullscreen) {
            videoPlayer.requestFullscreen();
        } else if (videoPlayer.mozRequestFullScreen) {
            videoPlayer.mozRequestFullScreen();
        } else if (videoPlayer.webkitRequestFullscreen) {
            videoPlayer.webkitRequestFullscreen();
        } else if (videoPlayer.msRequestFullscreen) { 
            videoPlayer.msRequestFullscreen();
        }
    }

    function onCanPlay() {
        let videoPlayer = document.getElementById('video_player')
        let loadingSpinner = document.getElementById('loading_spinner')

        loadingSpinner.style.display = 'none'
        videoPlayer.style.display = 'block'

        if (isPlaying)
            videoPlayer.play()
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
        }
        
        videoPlayer.style.display = 'none'
        loadingSpinner.style.display = 'block'
        
        videoPlayer.src = `video/${videoDetails.slug}/${videoPercentage}`
        currentTimeLabel.innerHTML = secondsToTimeString(currentTime)
        videoPercentageLabel.innerHTML = parseInt(videoPercentage) + '%'
    }

    function onTimeUpdate(event) {
        let currentTimeLabel = document.getElementById('current_time_label')
        let videoPercentageLabel = document.getElementById('video_percentage_label')
        let timeBarInput = document.getElementById('time_bar')

        currentTime = contextTime + event.target.currentTime
        videoPercentage = (currentTime / videoDuration) * 100

        currentTimeLabel.innerHTML = secondsToTimeString(currentTime)
        videoPercentageLabel.innerHTML = parseInt(videoPercentage) + '%'
        timeBarInput.value = videoPercentage
    }

    function setVideoDetails(videoPlayer, currentTimeLabel, videoDurationLabel, videoPercentageLabel, loadingSpinner, videoScoreContainer) {
        videoDuration = videoPlayer.duration
            
        currentTimeLabel.innerHTML = secondsToTimeString(currentTime)
        videoDurationLabel.innerHTML = secondsToTimeString(videoDuration)
        videoPercentageLabel.innerHTML = videoPercentage + '% '
        
        loadingSpinner.style.display = 'none'
        videoPlayer.style.display = 'block'
        videoScoreContainer.style.display = 'block'

        videoPlayer.addEventListener('timeupdate', (event) => onTimeUpdate(event))
        videoPlayer.addEventListener('canplay', () => onCanPlay())
    }

    function displayErrorMessage() {
        alert('Error loading video')
    }

    function checkVideoIsReady(k, videoPlayer, currentTimeLabel, videoDurationLabel, videoPercentageLabel, loadingSpinner, videoScoreContainer) {
        if (videoPlayer.duration)
            setVideoDetails(videoPlayer, currentTimeLabel, videoDurationLabel, videoPercentageLabel, loadingSpinner, videoScoreContainer)
        else {
            setTimeout(() => {
                if (k > 5) { // max nmbr attempts is 5
                    displayErrorMessage()
                    return
                }

                if (!videoPlayer.duration)
                    console.log('Video not ready yet, attempt nmbr: ' + k)
                else
                    console.log('Video is ready!')

                k++

                checkVideoIsReady(k, videoPlayer, currentTimeLabel, videoDurationLabel, videoPercentageLabel, loadingSpinner, videoScoreContainer)
            }, "1000")
        }
    }

    function init() {
        setTimeout(() => {
            let videoPlayer = document.getElementById('video_player')
            let currentTimeLabel = document.getElementById('current_time_label')
            let videoDurationLabel = document.getElementById('video_duration_label')
            let videoPercentageLabel = document.getElementById('video_percentage_label')
            let loadingSpinner = document.getElementById('loading_spinner')
            let videoScoreContainer = document.getElementById('video_score_container')
            
            let k = 1
            checkVideoIsReady(k, videoPlayer, currentTimeLabel, videoDurationLabel, videoPercentageLabel, loadingSpinner, videoScoreContainer)
          }, "1000") // Wait until document is loaded
    }

    return <>
        {init()}
        <div className={styles.video_player}>

            <div className={styles.video_player_container}>
                <video 
                    id='video_player'
                    className={styles.video_screen}
                    style={{display: 'none'}}>
                    <source src={`video/${videoDetails.slug}`} type="video/mp4"></source>
                </video>

                <div id='loading_spinner' style={{width: '100%'}}>
                    <LoadingSpinner></LoadingSpinner>
                </div>
            </div>

            <div id='video_score_container' style={{display: 'none'}}>
                <div className={styles.video_score_container}>
                    <div className={styles.video_score_element} id='current_time_label'></div>
                    <div className={styles.video_score_element}>/</div>
                    <div className={styles.video_score_element} id='video_duration_label'></div>
                    <div className={styles.video_score_element}>
                        
                        <div id='play_button' className={styles.action_button} onClick={() => playButtonOnClick()}>
                            <svg width="20px" height="20px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M16.6582 9.28638C18.098 10.1862 18.8178 10.6361 19.0647 11.2122C19.2803 11.7152 19.2803 12.2847 19.0647 12.7878C18.8178 13.3638 18.098 13.8137 16.6582 14.7136L9.896 18.94C8.29805 19.9387 7.49907 20.4381 6.83973 20.385C6.26501 20.3388 5.73818 20.0469 5.3944 19.584C5 19.053 5 18.1108 5 16.2264V7.77357C5 5.88919 5 4.94701 5.3944 4.41598C5.73818 3.9531 6.26501 3.66111 6.83973 3.6149C7.49907 3.5619 8.29805 4.06126 9.896 5.05998L16.6582 9.28638Z" stroke="#000000" strokeWidth="2" strokeLinejoin="round"/>
                            </svg>
                        </div>

                        <div id='pause_button' className={styles.action_button} onClick={() => pauseButtonOnClick()} style={{display: 'none'}}>
                            <svg width="20px" height="20px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M8 5V19M16 5V19" stroke="#000000" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                            </svg>
                        </div>
                    
                    </div>
                    <div className={`${styles.video_score_element} ${styles.input_range_container}`}>
                        <input type="range" id="time_bar_fake" min={0} max={100} value={0} onChange={() => {}} className={styles.input_range} />
                        <input type="range" id="time_bar" min={0} max={100} onChange={() => timeBarChange()} style={{display: 'none'}} className={styles.input_range} />
                    </div>
                    <div className={styles.video_score_element} id='video_percentage_label'></div>
                    <div className={styles.video_score_element}>
                        
                        <button id='full_screen_button' className={styles.action_button} onClick={() => fullScreenButtonOnClick()}>
                            <svg width="20px" height="20px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M6 9.99739C6.01447 8.29083 6.10921 7.35004 6.72963 6.72963C7.35004 6.10921 8.29083 6.01447 9.99739 6" stroke="#1C274C" strokeWidth="1.5" strokeLinecap="round"/>
                                <path d="M6 14.0007C6.01447 15.7072 6.10921 16.648 6.72963 17.2684C7.35004 17.8888 8.29083 17.9836 9.99739 17.998" stroke="#1C274C" strokeWidth="1.5" strokeLinecap="round"/>
                                <path d="M17.9976 9.99739C17.9831 8.29083 17.8883 7.35004 17.2679 6.72963C16.6475 6.10921 15.7067 6.01447 14.0002 6" stroke="#1C274C" strokeWidth="1.5" strokeLinecap="round"/>
                                <path d="M17.9976 14.0007C17.9831 15.7072 17.8883 16.648 17.2679 17.2684C16.6475 17.8888 15.7067 17.9836 14.0002 17.998" stroke="#1C274C" strokeWidth="1.5" strokeLinecap="round"/>
                                <path d="M22 12C22 16.714 22 19.0711 20.5355 20.5355C19.0711 22 16.714 22 12 22C7.28595 22 4.92893 22 3.46447 20.5355C2 19.0711 2 16.714 2 12C2 7.28595 2 4.92893 3.46447 3.46447C4.92893 2 7.28595 2 12 2C16.714 2 19.0711 2 20.5355 3.46447C21.5093 4.43821 21.8356 5.80655 21.9449 8" stroke="#1C274C" strokeWidth="1.5" strokeLinecap="round"/>
                            </svg>
                        </button>
                    
                    </div>
                </div>
            </div>
        </div>
    </>
}