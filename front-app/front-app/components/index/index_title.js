import styles from './index_title.module.css'

export default function IndexTitle({indexLayout}) {
    return <>
        <div className={styles.title_container}>
            <div dangerouslySetInnerHTML={{__html: indexLayout.text1}} className={styles.title_text} />
            <div dangerouslySetInnerHTML={{__html: indexLayout.text2}} className={styles.title_text} />
        </div>
    </>
}