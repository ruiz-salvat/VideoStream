import IndexCarouselElement from './index_carousel_element'
import styles from './index_carousel.module.css'

export default function IndexCarousel({indexLayout}) {

    function onLeftMove() {
        let carouselContainer = document.getElementById('carousel-container')
        carouselContainer.scrollBy(200, 0)
    }

    function onRightMove() {
        let carouselContainer = document.getElementById('carousel-container')
        carouselContainer.scrollBy(-200, 0)
    }

    function init() {
        setTimeout(() => {
            let buttonLeft = document.getElementById('button-left')
            let buttonRight = document.getElementById('button-right')
    
            buttonLeft.addEventListener('click', () => onRightMove())
            buttonRight.addEventListener('click', () => onLeftMove())
        }, "1000") // Wait until document is loaded
    }

    return <>
        {init()}
        <div className={styles.buttons_container}>
            <div className={styles.action_button_left}>
                <button id='button-left' className={styles.scroll_element}>{'<'}</button>
            </div>

            <div id='carousel-container' className={styles.carousel_container}>
                {indexLayout.indexCarousels.map((indexCarousel) => (
                    <IndexCarouselElement indexCarousel={indexCarousel} indexLayout={indexLayout }></IndexCarouselElement>
                ))}
            </div>

            <div className={styles.action_button_right}>
                <button id='button-right' className={styles.scroll_element}>{'>'}</button>
            </div>
        </div>
    </>
}