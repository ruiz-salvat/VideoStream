import styles from './video_page.module.css'
import React from 'react'
import VideoPlayer from './video_player'

export default function VideoPage({videoDetails}) {


    return <>
    
        <div>
            
            <VideoPlayer videoDetails={videoDetails}></VideoPlayer>
            
            <hr></hr>

            <h3 className={styles.video_title}>{ videoDetails.title }</h3>
            <div dangerouslySetInnerHTML={{__html: videoDetails.description}} className={styles.video_description} />
        </div>
    </>
}