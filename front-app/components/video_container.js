import styles from './video_container.module.css'
import Link from 'next/link'
import { getStaticProps } from '../pages'

export default function VideoContainer({video}) {
    return <>
        <Link href="/">
            <div className={styles.video_card}>
                <img className={styles.video_card_image} src={process.env.NEXT_PUBLIC_API_URL + 'video/image/' + video.slug} />
                <div className={styles.video_card_text_container}>
                    <div className={styles.video_card_title}>{video.title}</div>
                    <div className={styles.video_card_synopsis}>{video.synopsis}</div>
                </div>
            </div>
        </Link>
    </>
}