import styles from './video_container.module.css'
import VideoCard from './video_card'
import VideoCardModal from './video_card_modal'

export default function VideoContainer({video}) {

    function showVideoCardModal() {
        let videoCardModal = document.getElementById(`video_card_modal_${video.slug}`)

        videoCardModal.style.display = 'block'
    }

    function closeModal() {
        let videoCardModal = document.getElementById(`video_card_modal_${video.slug}`)

        videoCardModal.style.display = 'none'
    }

    function init() {
        setTimeout(() => {
            let videoCard = document.getElementById(`video_card_${video.slug}`)
            let videoCardModalCloseButton = document.getElementById(`video_card_modal_close_button_${video.slug}`)

            videoCard.addEventListener('click', () => {showVideoCardModal()})
            videoCardModalCloseButton.addEventListener('click', () => {closeModal()})
        }, "1000") // Wait until document is loaded
    }

    return <>
        {init()}
        <div>
            <div id={`video_card_${video.slug}`}>
                <VideoCard video={video}></VideoCard>
            </div>

            <div id={`video_card_modal_${video.slug}`} className={styles.video_card_modal_background} style={{display: 'none'}}>
                <div className={styles.video_card_modal_absolute_container}> 
                    <div className={styles.video_card_modal_container}>
                        <button id={`video_card_modal_close_button_${video.slug}`} className={styles.video_card_modal_close_button}>X</button>
                        <VideoCardModal video={video}></VideoCardModal>
                    </div>
                </div>
            </div>
        </div>
    </>
}