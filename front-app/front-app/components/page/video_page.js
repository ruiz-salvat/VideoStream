import styles from './video_page.module.css'
import React from 'react'

export default function VideoPage({videoDetails}) {

    function playButtonOnClick() {
        let videoPlayer = document.getElementById("video_player");
        videoPlayer.play();
    }

    function pauseButtonOnClick() {
        let videoPlayer = document.getElementById("video_player");
        videoPlayer.pause();
    }

    return <>
        <div>
            <div className={styles.video_player}>
                <video 
                    id='video_player'
                    className={styles.video_screen}>
                      <source src={`video/${videoDetails.slug}/20`} type="video/mp4"></source>
                </video>

                <button id='play_button' onClick={() => playButtonOnClick()}>Play</button>
                <button id='pause_button' onClick={() => pauseButtonOnClick()}>Pause</button>
                <progress id="bar" value="32" max="100"> 32% </progress>

                    {/* <iframe src={`video/${videoDetails.slug}`} width="540" height="310"></iframe> */}
                    
                {/* <ReactPlayer url={`${process.env.NEXT_PUBLIC_LOCALHOST_URL}video/${videoDetails.slug}`} /> */}
            </div>
            <h3 className={styles.video_title}>{ videoDetails.title }</h3>
            <div dangerouslySetInnerHTML={{__html: videoDetails.description}} className={styles.video_description} />
        </div>
    </>
}