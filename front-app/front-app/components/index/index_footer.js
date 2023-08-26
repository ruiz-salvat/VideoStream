import styles from './index_footer.module.css'

export default function IndexFooter({indexLayout}) {
    return <>
        <div className={styles.footer_container}>
            <div dangerouslySetInnerHTML={{__html: indexLayout.text3}} className={styles.footer_text} />
            <div dangerouslySetInnerHTML={{__html: indexLayout.text4}} className={styles.footer_text} />
        </div>
    </>
}