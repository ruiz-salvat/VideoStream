import styles from './video_card_modal.module.css'
import Link from 'next/link'

export default function VideoCardModal({video}) {

    return <>
            <div className={styles.video_card_modal}>
                <img className={styles.video_card_modal_image} src={process.env.NEXT_PUBLIC_LOCALHOST_URL + 'video/image/' + video.slug} />
                
                <div className={styles.video_card_modal_text_container}>
                    <div className={styles.video_card_modal_title}>{video.title}</div>
                    <p className={styles.video_card_modal_synopsis}>{video.synopsis}</p>
                </div>

                <div className={styles.action_buttons_container}>
                    <Link className={styles.video_link} href={video.slug}>
                        <div className={styles.link_div}>Watch</div>
                    </Link>
                </div>
            </div>
    </>
}