import styles from './video_card.module.css'

export default function VideoCard({video}) {
    return <>
        <div className={styles.video_card}>
            <img className={styles.video_card_image} src={process.env.NEXT_PUBLIC_LOCALHOST_URL + 'video/image/' + video.slug} />
            <div className={styles.video_card_text_container}>
                <div className={styles.video_card_title}>{video.title}</div>
            </div>
        </div>
    </>
}