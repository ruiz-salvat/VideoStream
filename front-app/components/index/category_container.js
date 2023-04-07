import styles from './category_container.module.css'
import VideoContainer from './video_container'

export default function CategoryContainer({category, videos}) {
    return <>
        <div>
            <h2>{category.name}</h2>
            <div className={styles.video_card_container}>
                {videos.map((video) => (
                    <VideoContainer key={video.slug} video={video} />
                ))}
            </div>
        </div>
    </>
}