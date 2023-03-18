import styles from './layout.module.css'
import Link from 'next/link'

export default function Layout({ children }) {
    return <>
            <div className={styles.container}>
                <div>
                    <Link href="/">
                        <img src="/media/title_image.png" alt="Video Stream" />
                    </Link>
                </div>
                <div>{children}</div>
            </div>
        </>
}