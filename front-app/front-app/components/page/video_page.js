import styles from './video_page.module.css'

export default function VideoPage({videoDetails}) {
    return <>
        <div>
            <div className={styles.video_player}>
                <video 
                    src={`video/${videoDetails.slug}`}
                    controls
                    className={styles.video_screen}/>
            </div>
            <h3 className={styles.video_title}>{ videoDetails.title }</h3>
            <div dangerouslySetInnerHTML={{__html: videoDetails.description}} className={styles.video_description} />
        </div>
    </>
}