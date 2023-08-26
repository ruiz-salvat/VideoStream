import styles from './index_carousel_element.module.css'

export default function IndexCarouselElement({indexCarousel, indexLayout}) {
    return <>
        <div className={styles.image_carousel_container}>
            <img
                className={styles.image_carousel} 
                alt={indexLayout.imageAlt}
                src={process.env.NEXT_PUBLIC_LOCALHOST_URL + 'index-carousel/image/' + indexCarousel.imageFilePath} />
            
            <div className={styles.element_footer}>
                <div className={styles.element_text}>{indexCarousel.imageAlt}</div>
                <div className={styles.element_link}>Watch</div>
            </div>
        </div>
    </>
}