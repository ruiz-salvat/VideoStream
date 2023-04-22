import '../css/global.css'
import Head from 'next/head'

export default function MyApp({ Component, pageProps }) {
    return <>
            <Head>
                <title>VideoStream</title>
                <link rel="icon" href="/favicon.ico" />
            </Head>
            <Component {...pageProps} />
        </>
}