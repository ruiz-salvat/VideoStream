import styles from '../ui/loading_spinner.module.css'

export default function VideoPlayer() {
    return <>
        <div className={styles.spinner_container}>
            <div>Loading...</div>
        </div>
    </>
}