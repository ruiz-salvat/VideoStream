import '../css/global.css'
import Head from 'next/head'

export default function MyApp({ Component, pageProps }) {
    return <>
            <Head>
                {/* Google Analytics 4 */}
                <script async src="https://www.googletagmanager.com/gtag/js?id=G-8F2W27LGV5"></script>
                <script
                    dangerouslySetInnerHTML={{
                    __html: `
                        window.dataLayer = window.dataLayer || [];
                        function gtag(){dataLayer.push(arguments);}
                        gtag('js', new Date());
                    
                        gtag('config', 'G-8F2W27LGV5');`,
                    }}
                />

                <title>VideoStream</title>
                <link rel="icon" href="/favicon.ico" />
            </Head>
            <Component {...pageProps} />
        </>
}