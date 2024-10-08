import '../css/global.css'
import Head from 'next/head'
import React from 'react'

export default function MyApp({ Component, pageProps}) {

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

                    {/* Google AdSense */}
                    <script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js?client=ca-pub-3183251056216665" crossorigin="anonymous"></script>

                    <title>VideoPulse</title>
                    <link rel="icon" href="/favicon.ico" />
                </Head>
                <Component {...pageProps} />
            </>
}