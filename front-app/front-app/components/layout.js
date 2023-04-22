import styles from './layout.module.css'
import Link from 'next/link'

export default function Layout({ children }) {
    return <>
            <div className={styles.container}>
                <div className={styles.image_container}>
                    <Link href="/">
                        <img src="/media/title_image.png" className={styles.image} alt="Video Stream" />
                    </Link>
                </div>
                <div>{children}</div>
            </div>
        </>
}