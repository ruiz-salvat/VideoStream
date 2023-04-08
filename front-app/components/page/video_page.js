import styles from './video_page.module.css'

export default function VideoPage({videoDetails}) {
    return <>
        <div>
            <video autoPlay loop style={{ width: '500px', height: '500px' }}>
                <source src={process.env.NEXT_PUBLIC_API_URL + 'video/' + videoDetails.slug} />
            </video>
            <h3 className={styles.video_title}>{ videoDetails.title }</h3>
            <div className={styles.video_description}>{ videoDetails.description }</div>
        </div>
    </>
}