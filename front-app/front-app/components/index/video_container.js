import styles from './video_container.module.css'
import Link from 'next/link'

export default function VideoContainer({video}) {

    function showSynopsis(event) {
        let synopsisCard = document.getElementById(`synopsis_card_${video.slug}`)

        synopsisCard.style.display = 'block'
    }

    function hideSynopsis() {
        let synopsisCard = document.getElementById(`synopsis_card_${video.slug}`)

        synopsisCard.style.display = 'none'
    }

    function init() {
        setTimeout(() => {
            let videoCard = document.getElementById(`video_card_${video.slug}`)
            
            videoCard.addEventListener('mouseover', (event) => showSynopsis(event))
            videoCard.addEventListener('mouseleave', (event) => hideSynopsis())
        }, "1000") // Wait until document is loaded
    }

    return <>
        {init()}
        <Link className={styles.video_link} href={video.slug}>
            <div id={`video_card_${video.slug}`} className={styles.video_card}>
                <img className={styles.video_card_image} src={process.env.NEXT_PUBLIC_LOCALHOST_URL + 'video/image/' + video.slug} />
                <div className={styles.video_card_text_container}>
                    <div className={styles.video_card_title}>{video.title}</div>
                    <div id={`synopsis_card_${video.slug}`} className={styles.video_card_synopsis} style={{display: 'none'}}>{video.synopsis}</div>
                </div>
            </div>
        </Link>
    </>
}